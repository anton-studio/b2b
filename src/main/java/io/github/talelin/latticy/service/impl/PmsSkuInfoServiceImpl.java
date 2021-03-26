package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.dto.SkuDTO;
import io.github.talelin.latticy.mapper.PmsProductAttrValueMapper;
import io.github.talelin.latticy.mapper.PmsSkuPriceInfoMapper;
import io.github.talelin.latticy.mapper.PmsSkuSaleAttrValueMapper;
import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.model.PmsSkuInfoDO;
import io.github.talelin.latticy.mapper.PmsSkuInfoMapper;
import io.github.talelin.latticy.model.PmsSkuPriceInfoDO;
import io.github.talelin.latticy.model.PmsSkuSaleAttrValueDO;
import io.github.talelin.latticy.service.PmsSkuInfoService;
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
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfoDO> implements PmsSkuInfoService {

    @Autowired
    PmsSkuInfoMapper skuInfoMapper;

    @Autowired
    PmsSkuPriceInfoMapper skuPriceInfoMapper;

    @Autowired
    PmsProductAttrValueMapper spuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public boolean createSkuIfo(PmsSkuInfoDO validator) {
        PmsSkuInfoDO skuInfoDO = new PmsSkuInfoDO();
        BeanUtils.copyProperties(validator, skuInfoDO);
        return skuInfoMapper.insert(skuInfoDO) > 0;
    }

    @Override
    public void deleteSkuBySpuId(Long spuId) {
        QueryWrapper<PmsSkuInfoDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsSkuInfoDO::getSpuId, spuId);
        skuInfoMapper.delete(wrapper);
    }

    @Override
    public List<SkuDTO> getSkuDtoByIds(List<Long> ids) {
        List<PmsSkuInfoDO> pmsSkuInfoDOS = skuInfoMapper.selectBatchIds(ids);
        List<SkuDTO> skuDTOList = new ArrayList<>();

        for (PmsSkuInfoDO pmsSkuInfoDO : pmsSkuInfoDOS) {
            SkuDTO skuDTO = new SkuDTO();
            BeanUtils.copyProperties(pmsSkuInfoDO, skuDTO);

            // setting price
            QueryWrapper<PmsSkuPriceInfoDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(PmsSkuPriceInfoDO::getSkuId, pmsSkuInfoDO.getId());
            List<PmsSkuPriceInfoDO> pmsSkuPriceInfoDOS = skuPriceInfoMapper.selectList(wrapper);
            skuDTO.setPriceList(pmsSkuPriceInfoDOS);

            // setting sale attr
            QueryWrapper<PmsSkuSaleAttrValueDO> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().eq(PmsSkuSaleAttrValueDO::getSkuId, pmsSkuInfoDO.getId());
            List<PmsSkuSaleAttrValueDO> pmsSkuSaleAttrValueDOS = skuSaleAttrValueMapper.selectList(wrapper2);
            skuDTO.setSaleAttrList(pmsSkuSaleAttrValueDOS);

            skuDTOList.add(skuDTO);
        }

        return skuDTOList;
    }
}
