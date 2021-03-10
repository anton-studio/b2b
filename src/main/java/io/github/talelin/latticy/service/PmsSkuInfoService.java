package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.model.PmsSkuInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.PmsSpuInfoDO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsSkuInfoService extends IService<PmsSkuInfoDO> {
    boolean createSkuIfo(PmsSkuInfoDO validator);
    void deleteSkuBySpuId(Long spuId);
}
