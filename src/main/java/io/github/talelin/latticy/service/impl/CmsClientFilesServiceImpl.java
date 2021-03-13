package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.CmsClientFilesDO;
import io.github.talelin.latticy.mapper.CmsClientFilesMapper;
import io.github.talelin.latticy.service.CmsClientFilesService;
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
public class CmsClientFilesServiceImpl extends ServiceImpl<CmsClientFilesMapper, CmsClientFilesDO> implements CmsClientFilesService {

    @Autowired
    CmsClientFilesMapper clientFilesMapper;

    @Override
    public boolean create(CmsClientFilesDO validator) {
        CmsClientFilesDO cmsClientFilesDO = new CmsClientFilesDO();
        BeanUtils.copyProperties(validator, cmsClientFilesDO);
        return clientFilesMapper.insert(cmsClientFilesDO) > 0;
    }
}
