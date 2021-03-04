package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsSpuInfoDO;
import io.github.talelin.latticy.mapper.PmsSpuInfoMapper;
import io.github.talelin.latticy.service.PmsSpuInfoService;
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
 * @since 2021-03-04
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfoDO> implements PmsSpuInfoService {

    @Autowired
    PmsSpuInfoMapper pmsSpuInfoMapper;

    @Override
    public boolean createSpuIfo(PmsSpuInfoDO validator) {
        PmsSpuInfoDO pmsSpuInfoDO = new PmsSpuInfoDO();
        BeanUtils.copyProperties(validator, pmsSpuInfoDO);
        return pmsSpuInfoMapper.insert(pmsSpuInfoDO) > 0;
    }
}
