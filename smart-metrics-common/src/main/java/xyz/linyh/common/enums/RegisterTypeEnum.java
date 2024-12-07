package xyz.linyh.common.enums;

/**
 * @author linzz
 */

public enum RegisterTypeEnum {
    ACCOUNT(1),
    EMAIL(2);

    private final int code;

    RegisterTypeEnum(int code) {
        this.code = code;
    }

    public static RegisterTypeEnum fromCode(int code) {
        for (RegisterTypeEnum type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("不支持的登陆参数");
    }
}