package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.CmsContractDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-13
 */
public interface CmsContractService extends IService<CmsContractDO> {

    boolean create(CmsContractDO validator);
}
