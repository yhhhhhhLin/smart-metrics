package xyz.linyh.datasource.client;


import lombok.extern.slf4j.Slf4j;

/**
 * 邮箱通知
 * @author linzz
 */
@Slf4j
public class EmailNotificationClient implements NotificationClient {

    @Override
    public void send(Long sendUserId, Long targetUserId, String message) {
        log.info("用户:{} 通过邮箱，发送:{} 给用户:{}", sendUserId, message, targetUserId);

    }
}
