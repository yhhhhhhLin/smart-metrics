package xyz.linyh.datasource.scheduling;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.linyh.datasource.client.DatasourceClient;
import xyz.linyh.datasource.client.NotificationClient;
import xyz.linyh.datasource.factory.DatasourceClientFactory;
import xyz.linyh.datasource.factory.NotificationClientFactory;
import xyz.linyh.datasource.model.entity.DscAlertRule;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.service.DscAlertLogService;
import xyz.linyh.datasource.service.DscAlertRuleService;
import xyz.linyh.datasource.service.DscInfoService;
import xyz.linyh.datasource.service.impl.DscInfoServiceImpl;
import xyz.linyh.datasource.utils.RedisUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 数据源告警调度任务
 * 每分钟检查配置了告警的数据源是否可连接，并发送提醒
 *
 * @author linzz
 */
@Component
@Slf4j
public class DscAlertRuleDispatchTask {

    @Resource
    private DscAlertRuleService dscAlertRuleService;

    @Resource
    private DscInfoService dscInfoService;

    @Resource
    private DscAlertLogService dscAlertLogService;

    @Resource
    private RedisUtil redisUtil;

    @Resource(name = "dscAlertRuleCheckTaskExecutor")
    private ThreadPoolExecutor dscAlertRuleCheckTaskExecutor;

    /**
     * 每分钟判断添加告警的数据源是否可连
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void dscAlertRuleTask() {
//        RedisScript
//        redisUtil

        // 找出所有配置了告警，并且status是开启状态的所有数据源规则
        List<DscAlertRule> dscAlertRules = dscAlertRuleService.listAllEnableAlertRule();
        if (dscAlertRules == null || dscAlertRules.isEmpty()) {
            return;
        }

        Map<Long, DscAlertRule> dscIdAndAlertRuleMap = dscAlertRules.stream()
                .collect(Collectors.toMap(DscAlertRule::getDscId, dscAlertRule -> dscAlertRule));
        List<Long> dscIds = dscAlertRules.stream().map(DscAlertRule::getDscId).toList();

        List<DscInfo> dscInfos = dscInfoService.listByIds(dscIds);
        Map<Long, DscInfo> dscIdAndDscInfoMap = dscInfos.stream()
                .collect(Collectors.toMap(DscInfo::getId, dscInfo -> dscInfo));

        dscIdAndDscInfoMap.forEach((dscId, dscInfo) ->
                dscAlertRuleCheckTaskExecutor.submit(() -> {
                    Boolean isConnected = checkDscConnect(dscInfo);
                    DscAlertRule alertRule= dscIdAndAlertRuleMap.get(dscId);
                    if (!isConnected) {
                        sendAlert(alertRule, dscInfo);
                    }else{
                        log.info("告警规则 {} 检查完毕，没有错误",alertRule);
                    }
                })
        );
    }

    /**
     * 检查数据源是否可连接
     */
    private Boolean checkDscConnect(DscInfo dscInfo) {
        AES aes = SecureUtil.aes(DscInfoServiceImpl.DSC_PASSWORD_SLAT.getBytes());
        String password = Arrays.toString(aes.decrypt(dscInfo.getPassword()));
        DatasourceClient client = DatasourceClientFactory.getClient(
                dscInfo.getDscType(),
                dscInfo.getUrl(),
                dscInfo.getUsername(),
                password
        );
        try {
            return client.testConnection();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 发送告警消息
     */
    private void sendAlert(DscAlertRule alertRule, DscInfo dscInfo) {
        Integer ruleType = alertRule.getRuleType();
        Integer notifyChannel = alertRule.getNotifyChannel();

//        判断配置了每日首次发现规则的当日是否已经发送告警
        if (ruleType == 1) {
            String alertTime = alertRule.getAlertTime();
            String todayTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (alertTime != null && alertTime.equals(todayTime)) {
                return;
            }
        }
        NotificationClient notificationClient = NotificationClientFactory.getClient(notifyChannel);

        String message = String.format("数据源 [%s] (ID: %d) 无法连接，规则 [%s] 已触发告警！",
                dscInfo.getDscName(), dscInfo.getId(), alertRule.getRuleName());
        notificationClient.send(null, alertRule.getNotifyRecipients(), message);

//        添加告警日志记录
        dscAlertLogService.addAlertLog(alertRule, dscInfo, message);
        dscAlertRuleService.addAlertTime(alertRule.getId());

        log.error("告警规则 {} 出现异常",alertRule);
    }
}
