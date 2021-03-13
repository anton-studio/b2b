package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.CmsClientInterestDO;
import io.github.talelin.latticy.mapper.CmsClientInterestMapper;
import io.github.talelin.latticy.service.CmsClientInterestService;
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
 * @since 2021-03-13
 */
@Service
public class CmsClientInterestServiceImpl extends ServiceImpl<CmsClientInterestMapper, CmsClientInterestDO> implements CmsClientInterestService {

    @Autowired
    CmsClientInterestMapper clientInterestMapper;

    @Override
    public boolean create(CmsClientInterestDO validator) {
        CmsClientInterestDO cmsClientInterestDO = new CmsClientInterestDO();
        BeanUtils.copyProperties(validator, cmsClientInterestDO);
        return clientInterestMapper.insert(cmsClientInterestDO) > 0;
    }
}
