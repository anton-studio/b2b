package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsAttrDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.PmsAttrGroupDO;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsAttrService extends IService<PmsAttrDO> {
    boolean createAttr(PmsAttrDO validator);
}
