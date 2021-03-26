package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_sku_info")
public class PmsSkuInfoDO extends BaseModel {


    /**
     * spuId
     */
    private Long spuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku介绍描述
     */
    private String skuDesc;

    /**
     * 所属分类id-冗余设计
     */
    private Long catalogId;

    /**
     * 默认图片
     */
    private String skuDefaultImg;

    /**
     * 标题
     */
    private String skuTitle;

    /**
     * 价格区间, 真实价格根据采购数量决定
     */
//    private String price;

    /**
     * 销量
     */
    private Long saleCount;

    private String size;

    /**
     * kg
     */
    private Long weight;

    private Long quantityPerCarton;

    private Long netWeightPerCarton;

    private Long grossWeightPerCarton;

    private String cartonMeasurement;

    private String cartonSize;


}
