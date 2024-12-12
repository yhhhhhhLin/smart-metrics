package xyz.linyh.datasource.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;

import java.util.HashMap;

/**
 * 全局异常处理器
 *
 * @author linzz
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
}
