package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_sku_price_info")
public class PmsSkuPriceInfoDO extends BaseModel {


    /**
     * skuId
     */
    private Long skuId;

    /**
     * 起始数量
     */
    private Long startCount;

    /**
     * end count
     */
    private Long endCount;

    /**
     * 真实价格
     */
    private BigDecimal price;


}
