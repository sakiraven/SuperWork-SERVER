package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

/**
 * @Description: 服务端状态
 */
public enum MyEnumLogMessage {
    // 服务器状态
    MISS_DICE_STILL_DATA("0", "dict表未查询到相关数据并丢弃不让前端展示 class:{0},position:{1},code:{2},type:{3}"),
    MISS_USER_INFO_STILL_DATA("0", "userInfo表未查询到相关数据并丢弃不让前端展示 class:{0},position:{1},userInfoId:{2}"),
    NOT_FOUND_MY_ENUM("0", "myEnum没有找到 class:{0},position:{1},code:{2}"),
    SUCCESS("0", "占位"),
    ;

    public final String type;
    public final String content;

    MyEnumLogMessage(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static String getContentByType(String type) {
        for (MyEnumLogMessage item : values()) {
            if (item.type.equals(type)) {
                return item.content;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return MyEnumDataDefault.UNDEFINED.content;
    }

}
