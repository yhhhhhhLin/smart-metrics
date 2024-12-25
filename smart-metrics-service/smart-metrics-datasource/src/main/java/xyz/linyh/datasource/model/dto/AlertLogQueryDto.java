package xyz.linyh.datasource.model.dto;

import lombok.Data;
import xyz.linyh.common.dto.PageCommonDto;

/**
 * @author linzz
 */
@Data
public class AlertLogQueryDto extends PageCommonDto {

    /**
     * 告警记录id
     */
    private Long dscAlertRuleId;

    /**
     * 告警日志id
     */
    private Long dscAlertLogId;

    /**
     * 数据源id
     */
    private Long dscId;

    /**
     * 告警接收者id
     */
    private Long recipientId;
}
