package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ims_contract")
public class ImsContractDO extends BaseModel {


    private LocalDateTime contractTime;

    private LocalDateTime deliveryTime;

    private Long clientId;

    private Long totalAmount;

    private Long prepayAmount;

    private Long actualDeliveryFee;

    private Long otherFee;

    private String paymentMethod;

    private String notes;

    private String paymentStatus;

    private Long rawCost;

    private String reviewStatus;

    private Long ownedBy;

    /**
     * if this address is null, use client address for contract printing
     */
    private String deliveryAddress;

    /**
     * usd
     */
    private Long shippingCost;

    private Long transactionFee;

    private String termsOfSale;

    private String insuranceCost;

    private String additionalCost;


}
