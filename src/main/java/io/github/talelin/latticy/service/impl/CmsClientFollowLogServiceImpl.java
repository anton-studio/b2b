package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.model.CmsClientFollowLogDO;
import io.github.talelin.latticy.mapper.CmsClientFollowLogMapper;
import io.github.talelin.latticy.service.CmsClientFollowLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class CmsClientFollowLogServiceImpl extends ServiceImpl<CmsClientFollowLogMapper, CmsClientFollowLogDO> implements CmsClientFollowLogService {

    @Autowired
    CmsClientFollowLogMapper clientFollowLogMapper;

    @Override
    public boolean create(CmsClientFollowLogDO validator) {
        CmsClientFollowLogDO cmsClientFollowLogDO = new CmsClientFollowLogDO();
        BeanUtils.copyProperties(validator, cmsClientFollowLogDO);
        Long userId = LocalUser.getLocalUser().getId();
        cmsClientFollowLogDO.setUserId(userId);
        return clientFollowLogMapper.insert(cmsClientFollowLogDO) > 0;
    }
}
