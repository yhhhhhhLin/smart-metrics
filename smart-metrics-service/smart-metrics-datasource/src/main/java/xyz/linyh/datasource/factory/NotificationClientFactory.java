package xyz.linyh.datasource.factory;


import xyz.linyh.common.enums.AlertMethodEnum;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.datasource.client.EmailNotificationClient;
import xyz.linyh.datasource.client.NotificationClient;
import xyz.linyh.datasource.client.SmsNotificationClient;

/**
 * 通知客户端工厂
 *
 * @author linzz
 */
public class NotificationClientFactory {

    public static NotificationClient getClient(Integer type) {
        AlertMethodEnum alertMethodEnum = AlertMethodEnum.getAlertMethodEnum(type);
        return switch (alertMethodEnum) {
            case EMAIL -> new EmailNotificationClient();
            case MESSAGE -> new SmsNotificationClient();
            default ->
                    throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "没有这个通知方式" + alertMethodEnum.name());
        };
    }
}
