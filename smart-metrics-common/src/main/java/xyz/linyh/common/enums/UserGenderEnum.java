package xyz.linyh.common.enums;

import lombok.Getter;

/**
 * @author linzz
 */

@Getter
public enum UserGenderEnum {
    MALE(0, "男"),
    FEMALE(1, "女"),
    UNKNOWN(2, "未知");

    private final int code;
    private final String description;

    UserGenderEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserGenderEnum fromCode(int code) {
        for (UserGenderEnum gender : values()) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        return UNKNOWN;
    }
}