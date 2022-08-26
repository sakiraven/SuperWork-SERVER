package com.saki.work.common.global.exception.capture;


import com.saki.work.myenum.MyEnumMessage;
import lombok.Data;

/**
 * @author lzh
 * @date 2020/10/9 - 14:30
 */
@Data
public class BaseBusinessException extends RuntimeException {
    private String type;

    private String message;

    private Boolean isSuccess;

    public BaseBusinessException(MyEnumMessage myEnumMessage) {
        this.message = myEnumMessage.content;
        this.type = myEnumMessage.type;
        this.isSuccess = myEnumMessage.isSuccess;
    }

    public static void throwRunTimeException() {
        throw new BaseBusinessException(MyEnumMessage.RUN_TIME_EXCEPTION);
    }

    public static void throwNotFoundPage404() {
        throw new BaseBusinessException(MyEnumMessage.NOT_FOUND);
    }

    public static void throwNotAuth() {
        throw new BaseBusinessException(MyEnumMessage.NOT_AUTH);
    }
}
