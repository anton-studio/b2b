package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsAttrGroupDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsAttrGroupService extends IService<PmsAttrGroupDO> {
    boolean createAttrGroup(PmsAttrGroupDO validator);
}
