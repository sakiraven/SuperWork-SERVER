package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

/**
 * @Description: 字典表type
 */
public enum MyEnumDataDefault {
    UNDEFINED("undefined", "未知"),
    ;

    public final String type;
    public final String content;

    MyEnumDataDefault(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static String getContentByType(String type) {
        for (MyEnumDataDefault item : values()) {
            if (item.type.equals(type)) {
                return item.content;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return MyEnumDataDefault.UNDEFINED.content;
    }
}
