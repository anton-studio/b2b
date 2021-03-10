package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.github.talelin.latticy.dto.ProductDTO;
import io.github.talelin.latticy.dto.SkuDTO;
import io.github.talelin.latticy.mapper.*;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.service.PmsProductAttrValueService;
import io.github.talelin.latticy.service.PmsSkuInfoService;
import io.github.talelin.latticy.service.PmsSpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfoDO> implements PmsSpuInfoService {

    @Autowired
    PmsSpuInfoMapper pmsSpuInfoMapper;

    @Autowired
    PmsProductAttrValueMapper productAttrValueMapper;

    @Autowired
    PmsSkuInfoMapper skuInfoMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    PmsSkuPriceInfoMapper skuPriceInfoMapper;

    @Autowired
    PmsSkuInfoService skuInfoService;

    @Autowired
    PmsProductAttrValueService productAttrValueService;

    @Override
    public boolean createSpuIfo(PmsSpuInfoDO validator) {
        PmsSpuInfoDO pmsSpuInfoDO = new PmsSpuInfoDO();
        BeanUtils.copyProperties(validator, pmsSpuInfoDO);
        return pmsSpuInfoMapper.insert(pmsSpuInfoDO) > 0;
    }

    @Override
    public void createFullProduct(ProductDTO validator) {
        // create spu
        PmsSpuInfoDO spu = new PmsSpuInfoDO();
        BeanUtils.copyProperties(validator, spu);
        pmsSpuInfoMapper.insert(spu);
        createSpuRelatedEntity(spu, validator);
    }

    private void createSpuRelatedEntity(PmsSpuInfoDO spu, ProductDTO validator) {
        // add basic attr to spu
        for (PmsProductAttrValueDO productAttrValueDO : validator.getBasicAttrList()) {
            PmsProductAttrValueDO productAttrValueDO1 = new PmsProductAttrValueDO();
            BeanUtils.copyProperties(productAttrValueDO, productAttrValueDO1);
            productAttrValueDO1.setSpuId(spu.getId());
            productAttrValueMapper.insert(productAttrValueDO1);
        }

        // create sku(add to spu)
        for (SkuDTO skuDTO : validator.getSkuList()) {
            PmsSkuInfoDO skuInfoDO = new PmsSkuInfoDO();
            BeanUtils.copyProperties(skuDTO, skuInfoDO);
            skuInfoDO.setSpuId(spu.getId());
            skuInfoMapper.insert(skuInfoDO);

            // create and add sale attr to sku
            for (PmsSkuSaleAttrValueDO pmsSkuSaleAttrValueDO : skuDTO.getSaleAttrList()) {
                PmsSkuSaleAttrValueDO pmsSkuSaleAttrValueDO1 = new PmsSkuSaleAttrValueDO();
                BeanUtils.copyProperties(pmsSkuSaleAttrValueDO, pmsSkuSaleAttrValueDO1);
                pmsSkuSaleAttrValueDO1.setSkuId(skuInfoDO.getId());
                skuSaleAttrValueMapper.insert(pmsSkuSaleAttrValueDO1);
            }

            // create and add price info to sku
            for (PmsSkuPriceInfoDO pmsSkuPriceInfoDO : skuDTO.getPriceList()) {
                PmsSkuPriceInfoDO pmsSkuPriceInfoDO1 = new PmsSkuPriceInfoDO();
                BeanUtils.copyProperties(pmsSkuPriceInfoDO, pmsSkuPriceInfoDO1);
                pmsSkuPriceInfoDO1.setSkuId(skuInfoDO.getId());
                skuPriceInfoMapper.insert(pmsSkuPriceInfoDO1);
            }

        }
    }

    @Override
    public ProductDTO getProductDetailById(Long id) {
        ProductDTO productDTO = new ProductDTO();

        // get spu info
        PmsSpuInfoDO spu = pmsSpuInfoMapper.selectById(id);
        BeanUtils.copyProperties(spu, productDTO);

        // get basic attr
        LambdaQueryWrapper<PmsProductAttrValueDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProductAttrValueDO::getSpuId, id);
        List<PmsProductAttrValueDO> productAttrValueDOList = productAttrValueMapper.selectList(wrapper);
        productDTO.setBasicAttrList(productAttrValueDOList);

        // get sku
        List<PmsSkuInfoDO> skuInfoDOList =
                new LambdaQueryChainWrapper<>(skuInfoMapper)
                        .eq(PmsSkuInfoDO::getSpuId, id)
                        .list();

        List<SkuDTO> skuDTOList = new ArrayList<>();

        for (PmsSkuInfoDO skuInfoDO : skuInfoDOList) {
            SkuDTO skuDTO = new SkuDTO();
            BeanUtils.copyProperties(skuInfoDO, skuDTO);

            // get sku sales attrs
            List<PmsSkuSaleAttrValueDO> saleAttrValueDOList =
                    new LambdaQueryChainWrapper<>(skuSaleAttrValueMapper)
                            .eq(PmsSkuSaleAttrValueDO::getSkuId, skuInfoDO.getId())
                            .list();
            skuDTO.setSaleAttrList(saleAttrValueDOList);

            // get sku price info
            List<PmsSkuPriceInfoDO> skuPriceInfoDOList =
                    new LambdaQueryChainWrapper<>(skuPriceInfoMapper)
                            .eq(PmsSkuPriceInfoDO::getSkuId, skuInfoDO.getId())
                            .list();
            skuDTO.setPriceList(skuPriceInfoDOList);

            skuDTOList.add(skuDTO);
        }

        productDTO.setSkuList(skuDTOList);

        return productDTO;
    }

    @Override
    public void updateProduct(Long spuId, ProductDTO validator) {
        // delete basic attr
        productAttrValueService.deleteAttrBySpuId(spuId);

        // delete sku
        skuInfoService.deleteSkuBySpuId(spuId);

        // update spu
        PmsSpuInfoDO pmsSpuInfoDO = new PmsSpuInfoDO();
        BeanUtils.copyProperties(validator, pmsSpuInfoDO);
        pmsSpuInfoDO.setId(spuId);
        pmsSpuInfoMapper.updateById(pmsSpuInfoDO);

        // recreate other stuff
        createSpuRelatedEntity(pmsSpuInfoDO, validator);
    }

    @Override
    public void deleteProduct(Long id) {
        // delete basic attr
        productAttrValueService.deleteAttrBySpuId(id);

        // delete sku
        skuInfoService.deleteSkuBySpuId(id);

        pmsSpuInfoMapper.deleteById(id);
    }
}
