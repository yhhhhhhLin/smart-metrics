package xyz.linyh.common.context;

/**
 * @author linzz
 */
public class UserIdContext {

    private static ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        userIdThreadLocal.set(userId);
    }

    public static Long getUserId() {
        return userIdThreadLocal.get();
    }

    public static void clear() {
        userIdThreadLocal.remove();
    }
}
