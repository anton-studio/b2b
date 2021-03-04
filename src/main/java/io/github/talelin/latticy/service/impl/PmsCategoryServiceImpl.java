package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsCategoryDO;
import io.github.talelin.latticy.mapper.PmsCategoryMapper;
import io.github.talelin.latticy.service.PmsCategoryService;
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
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategoryDO> implements PmsCategoryService {

    @Autowired
    PmsCategoryMapper categoryMapper;

    @Override
    public boolean createCategory(PmsCategoryDO validator) {
        PmsCategoryDO categoryDO = new PmsCategoryDO();
        categoryDO.setName(validator.getName());
        categoryDO.setCatLevel(validator.getCatLevel());
        categoryDO.setParentCid(validator.getParentCid());
        categoryDO.setSort(validator.getSort());
        categoryDO.setShowStatus(validator.getShowStatus());
        return categoryMapper.insert(categoryDO) > 0;
    }
}
