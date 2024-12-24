package xyz.linyh.datasource.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author linzz
 */
@Data
public class DscAlertRuleVO {

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

    private String createdUserName;


    /**
     * 最后更新人ID
     */
    private Long updatedUserId;

    private String updatedUserName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
