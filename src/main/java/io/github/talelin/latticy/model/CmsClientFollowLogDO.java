package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author generator@TaleLin
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cms_client_follow_log")
public class CmsClientFollowLogDO extends BaseModel {


    private Long clientId;

    private Integer star;

    private Date followTime;

    /**
     * followed by which user
     */
    private Long userId;

    /**
     * follow content
     */
    private String content;


}
