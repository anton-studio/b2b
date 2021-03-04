package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsSkuSaleAttrValueDO;
import io.github.talelin.latticy.mapper.PmsSkuSaleAttrValueMapper;
import io.github.talelin.latticy.service.PmsSkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku销售属性&值 服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Service
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueMapper, PmsSkuSaleAttrValueDO> implements PmsSkuSaleAttrValueService {

    @Autowired
    PmsSkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public boolean createSkuSaleAttrValue(PmsSkuSaleAttrValueDO validator) {
        PmsSkuSaleAttrValueDO pmsSkuSaleAttrValueDO = new PmsSkuSaleAttrValueDO();
        BeanUtils.copyProperties(validator, pmsSkuSaleAttrValueDO);
        return skuSaleAttrValueMapper.insert(pmsSkuSaleAttrValueDO) > 0;
    }
}
