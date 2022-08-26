package com.saki.work.base.recall;

import com.saki.work.myenum.MyEnumMessage;

/**
 * @author lzh
 * @date 2020/6/10 - 13:18
 */
public class RecallMethod {
    /**
     * 例如添加插入返回值 判断 success还是fail
     *
     * @param intResult
     * @return
     */
    public static String intResultToStrResult(Integer intResult) {
        if (intResult.equals(0)) {
            return MyEnumMessage.FAIL.type;
        } else {
            return MyEnumMessage.SUCCESS.type;
        }
    }
}
