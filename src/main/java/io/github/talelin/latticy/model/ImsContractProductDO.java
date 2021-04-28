package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author generator@TaleLin
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ims_contract_product")
public class ImsContractProductDO extends BaseModel {


    private Long contractId;

    private Long spuId;

    private Long skuId;

    private Long quantity;

    /**
     * contract price(real unit price)
     */
    private BigDecimal price;

    /**
     * write your own
     */
    private String specialRequest;


}
