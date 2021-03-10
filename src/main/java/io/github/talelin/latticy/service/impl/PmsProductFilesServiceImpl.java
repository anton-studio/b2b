package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsProductFilesDO;
import io.github.talelin.latticy.mapper.PmsProductFilesMapper;
import io.github.talelin.latticy.service.PmsProductFilesService;
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
 * @since 2021-03-10
 */
@Service
public class PmsProductFilesServiceImpl extends ServiceImpl<PmsProductFilesMapper, PmsProductFilesDO> implements PmsProductFilesService {

    @Autowired
    PmsProductFilesMapper productFilesMapper;

    @Override
    public void create(PmsProductFilesDO validator) {
        PmsProductFilesDO pmsProductFilesDO = new PmsProductFilesDO();
        BeanUtils.copyProperties(validator, pmsProductFilesDO);
        productFilesMapper.insert(pmsProductFilesDO);
    }
}
