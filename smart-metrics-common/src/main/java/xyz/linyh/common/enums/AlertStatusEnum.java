package xyz.linyh.common.enums;

import lombok.Getter;
import xyz.linyh.common.exception.BusinessException;

/**
 * @author linzz
 */

@Getter
public enum AlertStatusEnum {

    WAITING(1, "等待"),
    NOTIFICATION_SENT(2, "通知发送"),
    RESOLVED(3, "解决");

    private final Integer code;
    private final String description;

    AlertStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AlertStatusEnum fromCode(Integer code) {
        for (AlertStatusEnum status : AlertStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR , "未知告警状态码："+code);
    }
}
