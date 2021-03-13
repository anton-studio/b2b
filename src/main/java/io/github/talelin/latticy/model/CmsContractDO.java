package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cms_contract")
public class CmsContractDO extends BaseModel {


    /**
     * could be selected by sales in frontend
     */
    private LocalDate contractDate;

    /**
     * could be selected by sales in frontend
     */
    private LocalDate deliveryDate;

    private Long clientId;

    private Long ownedBy;

    /**
     * modify later, by on currency
     */
    private String totalAmount;

    /**
     * modify later, by on currency
     */
    private String actualDeliveryFee;

    /**
     * modify later
     */
    private String otherFee;

    private String otherFeeComment;

    private String paymentMethod;

    /**
     * should be sku, separated by ,
     */
    private String productIds;

    /**
     * contract notes, handwritten by sales
     */
    private String notes;

    /**
     * 支付预付款|款项收齐|其他
     */
    private String paymentStatus;

    private String paymentPreAmount;

    private String paymentOtherNotes;

    /**
     * not modify by sales, only admin
     */
    private String rawCost;

    /**
     * 待审批|已审批 -- 业务填写款项收齐提示是否进入审批状态 审批状态 will show up in admins panel
     */
    private String reviewStatus;


}
