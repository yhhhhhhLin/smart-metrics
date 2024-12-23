package xyz.linyh.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.linyh.datasource.mapper.DscAlertRuleMapper;
import xyz.linyh.datasource.model.dto.AlertRuleAddOrUpdateDto;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.service.DscAlertRuleService;

/**
 * @author linzz
 * @description 针对表【dsc_alert_rule(数据源告警规则表)】的数据库操作Service实现
 * @createDate 2024-12-08 21:52:27
 */
@Service
public class DscAlertRuleServiceImpl extends ServiceImpl<DscAlertRuleMapper, DscAlertRule>
        implements DscAlertRuleService {

    @Override
    public Boolean addRule(AlertRuleAddOrUpdateDto dto) {
        Long dscId = dto.getDscId();
        return null;
    }
}




