package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_attr_group")
public class PmsAttrGroupDO extends BaseModel {


    /**
     * 组名
     */
    private String attrGroupName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 组图标
     */
    private String icon;


}
