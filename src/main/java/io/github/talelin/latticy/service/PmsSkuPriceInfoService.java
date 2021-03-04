package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsSkuPriceInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.PmsSkuSaleAttrValueDO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsSkuPriceInfoService extends IService<PmsSkuPriceInfoDO> {
    boolean createSkuPrice(PmsSkuPriceInfoDO validator);
}
