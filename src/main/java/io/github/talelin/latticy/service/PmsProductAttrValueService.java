package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.PmsSpuInfoDO;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsProductAttrValueService extends IService<PmsProductAttrValueDO> {
    boolean createProductAttrValue(PmsProductAttrValueDO validator);
    void deleteAttrBySpuId(Long spuId);
    List<PmsProductAttrValueDO> getByAttrId(Long id);
    void deleteByAttrId(Long id);
}
