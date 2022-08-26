package com.saki.work.myenum;


import com.saki.work.utils.LogUtil;

public enum MyEnumRoleName {
    ADMIN("admin", "管理员"),
    ;

    public final String type;
    public final String content;

    MyEnumRoleName(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static MyEnumRoleName getEnumByType(String type) {
        for (MyEnumRoleName item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return null;
    }
}
