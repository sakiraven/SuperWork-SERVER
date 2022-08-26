package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumNoticeTemplate {
    // 1. [声控周不鸽、用户名称]赞了我的[评论、视频...]
    LIKE_NUM_ONE("like", "{0}赞了我的{1}"),
    // 1. [声控周不鸽、用户名称]赞了我的[评论、视频...]
    LIKE_NUM_MANY("like", "{0}赞了我的{1}"),
    // 2. [声控周不鸽、用户名称]回复了你的评论
    COMMENT_TWO("comment_two", "{0}回复了你的评论"),
    // 3. [声控周不鸽、用户名称]评论了你的[相集]
    COMMENT_ONE("comment_one", "{0}评论了你的{1}"),
    // 4. [声控周不鸽]的留言:[巴啦巴拉巴拉]
    MESSAGE_LEAVE("message_leave", "{0}的留言"),
    // 5. [声控周不鸽]对您[相集]的打赏:[10元]
    REWARD("reward", "{0}对您{1}的打赏:{2}"),
    ;

    public final String type;
    public final String content;

    MyEnumNoticeTemplate(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static MyEnumNoticeTemplate getEnumByType(String type) {
        for (MyEnumNoticeTemplate item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return null;
    }
}
