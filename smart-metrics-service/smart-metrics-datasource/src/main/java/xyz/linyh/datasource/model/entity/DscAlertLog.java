package xyz.linyh.datasource.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 数据源告警日志表
 * @TableName data_source_alert_log
 */
@TableName(value ="data_source_alert_log")
@Data
public class DscAlertLog implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 告警规则ID
     */
    private Long alertRuleId;

    /**
     * 数据源ID
     */
    private Long dscId;

    /**
     * 告警触发时间
     */
    private Date alertTime;

    /**
     * 告警状态 1：等待 2：通知发送 3：解决
     */
    private Integer alertStatus;

    /**
     * 告警错误信息描述
     */
    private String errorMessage;

    /**
     * 通知方式 1: 短信 2：邮箱 ....
     */
    private Integer notifyChannel;

    /**
     * 通知接收者id
     */
    private Object notifyRecipients;

    /**
     * 告警解决时间
     */
    private Date resolvedAt;

    /**
     * 解决人ID
     */
    private Long resolvedUserId;

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