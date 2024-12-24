package xyz.linyh.datasource.client;

/**
 * @author linzz
 */
public interface NotificationClient {

    /**
     * 发送通知
     * @param sendUserId
     * @param targetUserId
     * @param message
     */
    void send(Long sendUserId, Long targetUserId, String message);
}
