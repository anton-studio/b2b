package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.latticy.model.PmsCategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsCategoryService extends IService<PmsCategoryDO> {
    boolean createCategory(PmsCategoryDO validator);
}
