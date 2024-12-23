package xyz.linyh.common.enums;

import lombok.Getter;
import xyz.linyh.common.exception.BusinessException;

@Getter
public enum AlertMethodEnum {

    MESSAGE(1,"短信"),
    EMAIL(2,"邮箱");


    private final Integer code;

    private final String method;

    AlertMethodEnum(Integer code, String dscTypeName) {
        this.code = code;
        this.method = dscTypeName;
    }

    public static AlertMethodEnum getAlertMethodEnum(Integer code) {
        AlertMethodEnum[] values = AlertMethodEnum.values();
        for (AlertMethodEnum value : values) {
            if(value.getCode().equals(code)) {
                return value;
            }
        }
        throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "没有对应的告警规则");
    }
}
