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
@TableName("pms_spu_info")
public class PmsSpuInfoDO extends BaseModel {


    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述
     */
    private String spuDescription;

    /**
     * 所属分类id
     */
    private Long catalogId;

    private Long attrGroupId;

    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;

    /**
     * SPU 默认图片地址
     */
    private String imgUrl;

    /**
     * 价格区间, 真实价格根据采购数量决定
     */
    private String price;

    private String spuTitle;

    private String printableAttrIds;


}
