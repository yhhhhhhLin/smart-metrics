package xyz.linyh.datasource.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.linyh.common.dto.PageCommonDto;

/**
 * @author linzz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlertRulePageDto extends PageCommonDto {

    /**
     * 告警名称，可模糊查询
     */
    private String alertRuleName;
}
