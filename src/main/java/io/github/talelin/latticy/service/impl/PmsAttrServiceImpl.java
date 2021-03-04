package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.mapper.PmsAttrMapper;
import io.github.talelin.latticy.service.PmsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttrDO> implements PmsAttrService {

    @Autowired
    PmsAttrMapper pmsAttrMapper;

    @Override
    public boolean createAttr(PmsAttrDO validator) {
        PmsAttrDO pmsAttrDO = new PmsAttrDO();
        BeanUtils.copyProperties(validator, pmsAttrDO);
        return pmsAttrMapper.insert(pmsAttrDO) > 0;
    }
}
