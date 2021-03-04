package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.mapper.PmsCategoryMapper;
import io.github.talelin.latticy.model.PmsAttrGroupDO;
import io.github.talelin.latticy.mapper.PmsAttrGroupMapper;
import io.github.talelin.latticy.model.PmsCategoryDO;
import io.github.talelin.latticy.service.PmsAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroupDO> implements PmsAttrGroupService {

    @Autowired
    PmsAttrGroupMapper pmsAttrGroupMapper;

    @Override
    public boolean createAttrGroup(PmsAttrGroupDO validator) {
        PmsAttrGroupDO attrGroupDO = new PmsAttrGroupDO();
        attrGroupDO.setAttrGroupName(validator.getAttrGroupName());
        return pmsAttrGroupMapper.insert(attrGroupDO) > 0;
    }
}
