package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsSkuInfoDO;
import io.github.talelin.latticy.mapper.PmsSkuInfoMapper;
import io.github.talelin.latticy.service.PmsSkuInfoService;
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
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfoDO> implements PmsSkuInfoService {

    @Autowired
    PmsSkuInfoMapper skuInfoMapper;

    @Override
    public boolean createSkuIfo(PmsSkuInfoDO validator) {
        PmsSkuInfoDO skuInfoDO = new PmsSkuInfoDO();
        BeanUtils.copyProperties(validator, skuInfoDO);
        return skuInfoMapper.insert(skuInfoDO) > 0;
    }
}
