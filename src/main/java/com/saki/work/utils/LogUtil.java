package com.saki.work.utils;

import com.saki.work.myenum.MyEnumLogMessage;
import lombok.extern.log4j.Log4j2;

import java.text.MessageFormat;

/**
 * @author lzh
 * @date 2020/11/18 - 18:25
 */
@Log4j2
public class LogUtil {
    /**
     * 字典丢失
     *
     * @param dictType
     * @param code
     */
    public static void missDictError(String dictType, String code) {
        log.error(
                MessageFormat.format(
                        MyEnumLogMessage.MISS_DICE_STILL_DATA.content,
                        LzhUtil.getClassNameByUpTwoLevel(),
                        LzhUtil.getMethodNameByUpTwoLevel(),
                        dictType,
                        code
                )
        );
    }

    public static void missUserInfoError(Long id) {
        log.error(
                MessageFormat.format(
                        MyEnumLogMessage.MISS_USER_INFO_STILL_DATA.content,
                        LzhUtil.getClassNameByUpTwoLevel(),
                        LzhUtil.getMethodNameByUpTwoLevel(),
                        id
                )
        );
    }

    public static void notFoundMyEnum(String code) {
        log.error(
                MessageFormat.format(
                        MyEnumLogMessage.NOT_FOUND_MY_ENUM.content,
                        LzhUtil.getClassNameByUpTwoLevel(),
                        LzhUtil.getMethodNameByUpTwoLevel(),
                        code
                )
        );
    }

    public static void notFoundMyEnum(Integer code) {
        log.error(
                MessageFormat.format(
                        MyEnumLogMessage.NOT_FOUND_MY_ENUM.content,
                        LzhUtil.getClassNameByUpTwoLevel(),
                        LzhUtil.getMethodNameByUpTwoLevel(),
                        code
                )
        );
    }

    public static void uploadError() {
        log.warn("文件上传异常");
    }

    public static void uploadFault() {
        log.warn("文件上传失败");
    }
}
