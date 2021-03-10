package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsProductFilesDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-10
 */
public interface PmsProductFilesService extends IService<PmsProductFilesDO> {

    void create(PmsProductFilesDO validator);
}
