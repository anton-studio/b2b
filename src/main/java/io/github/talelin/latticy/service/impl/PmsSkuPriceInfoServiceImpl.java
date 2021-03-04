package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.model.PmsSkuPriceInfoDO;
import io.github.talelin.latticy.mapper.PmsSkuPriceInfoMapper;
import io.github.talelin.latticy.service.PmsSkuPriceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PmsSkuPriceInfoServiceImpl extends ServiceImpl<PmsSkuPriceInfoMapper, PmsSkuPriceInfoDO> implements PmsSkuPriceInfoService {

    @Autowired
    PmsSkuPriceInfoMapper skuPriceInfoMapper;

    @Override
    public boolean createSkuPrice(PmsSkuPriceInfoDO validator) {
        PmsSkuPriceInfoDO pmsSkuPriceInfoDO = new PmsSkuPriceInfoDO();
        BeanUtils.copyProperties(validator, pmsSkuPriceInfoDO);
        return skuPriceInfoMapper.insert(pmsSkuPriceInfoDO) > 0;
    }

    @Override
    public List<PmsSkuPriceInfoDO> findBySkuId(Long skuId) {
        List<PmsSkuPriceInfoDO> pmsSkuPriceInfoDOList =
                new LambdaQueryChainWrapper<>(skuPriceInfoMapper)
                        .eq(PmsSkuPriceInfoDO::getSkuId, skuId)
                        .list();
        return pmsSkuPriceInfoDOList;
    }
}
