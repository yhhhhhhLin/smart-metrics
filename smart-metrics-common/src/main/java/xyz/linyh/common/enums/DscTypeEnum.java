package xyz.linyh.common.enums;


import lombok.Getter;
import xyz.linyh.common.exception.BusinessException;

import java.util.Arrays;

/**
 * @author linzz
 */

@Getter
public enum DscTypeEnum {

    MYSQL57(1,"mysql5.7"),
    MYSQL80(2,"mysql8.0"),
    REDIS(3, "redis");


    private final Integer code;

    private final String dscTypeName;

    DscTypeEnum(Integer code, String dscTypeName) {
        this.code = code;
        this.dscTypeName = dscTypeName;
    }

    public static DscTypeEnum getDscTypeEnum(Integer code) {
        DscTypeEnum[] values = DscTypeEnum.values();
        for (DscTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value;
            }
        }
        throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "没有对应数据源");
    }
}
