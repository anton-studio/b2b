package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.CmsClientInterestDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-13
 */
public interface CmsClientInterestService extends IService<CmsClientInterestDO> {
    boolean create(CmsClientInterestDO validator);

    List<CmsClientInterestDO> getByClientId(Long id);

    void deleteInterestByClientId(Long id);
}
