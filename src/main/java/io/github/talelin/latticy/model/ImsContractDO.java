package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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

    private BigDecimal totalAmount;

    private BigDecimal prepayAmount;

    private BigDecimal actualDeliveryFee;

    private BigDecimal otherFee;

    private String paymentMethod;

    private String notes;

    private String paymentStatus;

    private BigDecimal rawCost;

    private String reviewStatus;

    private Long ownedBy;

    /**
     * if this address is null, use client address for contract printing
     */
    private String deliveryAddress;

    /**
     * usd
     */
    private BigDecimal shippingCost;

    private BigDecimal transactionFee;

    private String termsOfSale;

    private BigDecimal insuranceCost;

    private BigDecimal additionalCost;

    @TableField("PI_NO")
    private String PINo;


}
