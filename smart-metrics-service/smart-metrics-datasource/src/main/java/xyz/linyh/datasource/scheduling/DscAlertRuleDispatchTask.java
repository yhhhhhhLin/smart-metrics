package xyz.linyh.datasource.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author linzz
 */
@Component
public class DscAlertRuleDispatchTask {

    /**
     * 每分钟判断添加告警的数据源是否可连
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void dscAlertRuleTask(){

    }
}
