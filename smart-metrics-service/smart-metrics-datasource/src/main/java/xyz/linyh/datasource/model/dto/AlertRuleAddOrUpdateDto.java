package xyz.linyh.datasource.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class AlertRuleAddOrUpdateDto {

    /**
     * 告警规则
     */
    private String alertName;

    /**
     * 告警数据源ID
     */
    private Long dscId;

    /**
     * 告警方式 --> AlertMethodEnum
     */
    private Integer alertMethod;

    /**
     * 告警接收者方式 1：告警创建人，2：数据源创建人，3：其他
     */
    private Integer notifyRecipientsType;
    /**
     * 通知接收人id
     */
    private Long notifyRecipients;


}
