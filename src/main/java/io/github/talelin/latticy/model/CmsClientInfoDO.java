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
@TableName("cms_client_info")
public class CmsClientInfoDO extends BaseModel {


    private String country;

    private String clientName;

    private String companyName;

    /**
     * JSON string of array
     */
    private String contactMethods;

    /**
     * generated by frontend code
     */
    private String code;

    /**
     * should be int(enum), optimise later
     */
    private String clientLevel;

    /**
     * should be int(enum), optimise later
     */
    private String industry;

    private String source;

    /**
     * user id, if not owned by anyone, should be in public sea(公海)
     */
    private Long ownedBy;


}