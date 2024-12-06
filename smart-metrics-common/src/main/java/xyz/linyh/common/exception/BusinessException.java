package xyz.linyh.common.exception;


import lombok.Getter;
import xyz.linyh.common.enums.ErrorCodeEnum;

/**
 * 自定义异常类
 *
 * @author linzz
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCodeEnum errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
