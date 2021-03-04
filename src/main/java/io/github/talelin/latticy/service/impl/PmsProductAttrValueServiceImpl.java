package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.mapper.PmsProductAttrValueMapper;
import io.github.talelin.latticy.service.PmsProductAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Service
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueMapper, PmsProductAttrValueDO> implements PmsProductAttrValueService {

    @Autowired
    PmsProductAttrValueMapper productAttrValueMapper;

    @Override
    public boolean createProductAttrValue(PmsProductAttrValueDO validator) {
        PmsProductAttrValueDO productAttrValueDO = new PmsProductAttrValueDO();
        BeanUtils.copyProperties(validator, productAttrValueDO);
        return productAttrValueMapper.insert(productAttrValueDO) > 0;
    }
}
