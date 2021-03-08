package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.ProductDTO;
import io.github.talelin.latticy.dto.SkuDTO;
import io.github.talelin.latticy.mapper.*;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.service.PmsSpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
