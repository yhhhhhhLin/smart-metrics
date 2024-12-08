package xyz.linyh.datasource.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据源告警规则表
 *
 * @author linzz
 * @TableName dsc_alert_rule
 */
@TableName(value = "dsc_alert_rule")
@Data
public class DscAlertRule implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据源ID
     */
    private Long dscId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 告警规则类型 1：每日首次发现连接失败 2：每次发现连接失败
     */
    private Integer ruleType;

    /**
     * 是否启用告警（0:禁用，1:启用）
     */
    private Integer isEnabled;

    /**
     * 通知方式 1: 短信 2：邮箱 ....
     */
    private Integer notifyChannel;

    /**
     * 通知接收者id
     */
    private Long notifyRecipients;

    /**
     * 告警接收者方式 1：告警创建人，2：数据源创建人，3：其他
     */
    private Integer notifyRecipientsType;

    /**
     * 创建人ID
     */
    private Long createdUserId;


    /**
     * 最后更新人ID
     */
    private Long updatedUserId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 逻辑删除标志（0-未删除，1-已删除）
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}