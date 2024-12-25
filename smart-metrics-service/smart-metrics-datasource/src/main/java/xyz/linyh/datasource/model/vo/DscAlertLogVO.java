package xyz.linyh.datasource.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author linzz
 */
@Data
public class DscAlertLogVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 告警规则ID
     */
    private Long alertRuleId;

    /**
     * 告警规则名称
     */
    private String alertRuleName;

    /**
     * 数据源ID
     */
    private Long dscId;

    /**
     * 数据源名称
     */
    private String dscName;

    /**
     * 告警触发时间
     */
    private Date alertTime;

    /**
     * 告警状态 1：等待 2：通知发送 3：解决  -->alertStatusEnum
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
    private Long notifyRecipients;

    /**
     * 告警接收者姓名
     */
    private String notifyRecipientsName;

    /**
     * 告警解决时间
     */
    private Date resolvedAt;

    /**
     * 解决人ID
     */
    private Long resolvedUserId;

    /**
     * 解决人姓名
     */
    private String resolvedUserName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
