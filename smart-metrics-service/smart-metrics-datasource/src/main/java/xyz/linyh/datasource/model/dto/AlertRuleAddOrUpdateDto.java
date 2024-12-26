package xyz.linyh.datasource.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class AlertRuleAddOrUpdateDto {

    /**
     * 告警规则名称
     */
    private String ruleName;

    /**
     * 告警数据源ID
     */
    private Long dscId;

    /**
     * 告警规则类型 1：每日首次发现连接失败 2：每次发现连接失败
     */
    private Integer ruleType;

    /**
     * 告警通知方式 --> AlertMethodEnum
     */
    private Integer notifyChannel;

    /**
     * 告警接收者方式 1：告警创建人，2：数据源创建人，3：其他
     */
    private Integer notifyRecipientsType;
    /**
     * 通知接收人id
     */
    private Long notifyRecipients;


}
