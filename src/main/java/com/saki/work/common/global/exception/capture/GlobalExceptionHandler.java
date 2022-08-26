package com.saki.work.common.global.exception.capture;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.saki.work.myenum.MyEnumMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lzh
 * @date 2020/3/1 - 19:59
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // sa-token权限验证
    @ExceptionHandler(NotRoleException.class)
    public JsonResult unauthorizedException(NotRoleException ex) {
        return resultFormat(MyEnumMessage.UNAUTHORIZED.type, MyEnumMessage.UNAUTHORIZED.content, ex, MyEnumMessage.UNAUTHORIZED.isSuccess);
    }

    // sa-token 未登录
    @ExceptionHandler(NotLoginException.class)
    public JsonResult unauthenticatedException(NotLoginException ex) {
        return resultFormat(MyEnumMessage.NOT_LOGIN.type, MyEnumMessage.NOT_LOGIN.content, ex, MyEnumMessage.NOT_LOGIN.isSuccess);
    }

    // 自定义异常
    @ExceptionHandler(BaseBusinessException.class)
    public JsonResult runtimeExceptionHandler(BaseBusinessException ex) {
        return resultFormat(ex.getType(), ex.getMessage(), ex, ex.getIsSuccess());
    }

    //运行时异常
//    @ExceptionHandler(RuntimeException.class)
//    public JsonResult runtimeExceptionHandler(RuntimeException ex) {
//        return resultFormat(ex.hashCode(), ex.getMessage(), ex, MyEnumMessage.RUN_TIME_EXCEPTION.isSuccess);
//    }

    //运行时异常
    @ExceptionHandler(Exception.class)
    public JsonResult exceptionHandler(Exception ex) {
        return resultFormat(MyEnumMessage.RUN_TIME_EXCEPTION.type, MyEnumMessage.RUN_TIME_EXCEPTION.content, ex, MyEnumMessage.RUN_TIME_EXCEPTION.isSuccess);
    }

    private <T extends Throwable> JsonResult resultFormat(String code, String cnMsg, T ex, Boolean success) {
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JsonResult.failed(code, ex.getMessage(), success);
    }
}
