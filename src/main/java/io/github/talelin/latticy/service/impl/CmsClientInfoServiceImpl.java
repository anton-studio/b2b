package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.mapper.CmsClientInfoMapper;
import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.service.CmsClientInfoService;
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
public class CmsClientInfoServiceImpl extends ServiceImpl<CmsClientInfoMapper, CmsClientInfoDO> implements CmsClientInfoService {

    @Autowired
    CmsClientInfoMapper clientInfoMapper;

    @Override
    public boolean create(CmsClientInfoDO validator) {
        CmsClientInfoDO cmsClientInfoDO = new CmsClientInfoDO();
        BeanUtils.copyProperties(validator, cmsClientInfoDO);
        return clientInfoMapper.insert(cmsClientInfoDO) > 0;
    }

    @Override
    public void update(Long id, CmsClientInfoDO validator) {
        CmsClientInfoDO cmsClientInfoDO = new CmsClientInfoDO();
        BeanUtils.copyProperties(validator, cmsClientInfoDO);
        cmsClientInfoDO.setId(id);
        clientInfoMapper.updateById(cmsClientInfoDO);
    }
}
