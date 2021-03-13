package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("cms_client_interest")
public class CmsClientInterestDO extends BaseModel {


    private Long clientId;

    /**
     * 0 id means product need to be purchased
     */
    private Long productId;

    /**
     * if product_id is 0, needs to add a note
     */
    private String notes;


}
