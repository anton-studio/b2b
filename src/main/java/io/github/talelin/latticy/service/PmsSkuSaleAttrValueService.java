package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.PmsSkuSaleAttrValueDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.PmsSpuInfoDO;

/**
 * <p>
 * sku销售属性&值 服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValueDO> {
    boolean createSkuSaleAttrValue(PmsSkuSaleAttrValueDO validator);
}
