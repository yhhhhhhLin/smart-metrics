package xyz.linyh.common.enums;

/**
 * @author linzz
 */

public enum LoginTypeEnum {
    ACCOUNT(1),
    EMAIL(2);

    private final int code;

    LoginTypeEnum(int code) {
        this.code = code;
    }

    public static LoginTypeEnum fromCode(int code) {
        for (LoginTypeEnum type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("不支持的登陆参数");
    }
}