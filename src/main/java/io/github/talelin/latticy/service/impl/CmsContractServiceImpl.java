package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.CmsContractDO;
import io.github.talelin.latticy.mapper.CmsContractMapper;
import io.github.talelin.latticy.service.CmsContractService;
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
public class CmsContractServiceImpl extends ServiceImpl<CmsContractMapper, CmsContractDO> implements CmsContractService {


    @Autowired
    CmsContractMapper cmsContractMapper;

    @Override
    public boolean create(CmsContractDO validator) {
        CmsContractDO cmsContractDO = new CmsContractDO();
        BeanUtils.copyProperties(validator, cmsContractDO);
        return cmsContractMapper.insert(cmsContractDO) > 0;
    }
}
