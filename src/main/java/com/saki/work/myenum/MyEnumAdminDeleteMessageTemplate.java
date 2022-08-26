package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumAdminDeleteMessageTemplate {
    /**
     * 0 漫展标题
     * 1 栏目类型
     */
    PHOTO_COSER_PHO("photo_coser_pho", "您在漫展：{0}，{1} 栏目下的相集已被删除"),
    /**
     * 0 日推标题
     */
    NEWS("news", "您发布的日推：{0} 已被删除"),
    /**
     * 0 漫展标题
     * 1 栏目类型
     * 2 用户昵称
     */
    PHOTO_COSER_PHO_STAMP("photo_coser_pho_stamp", "您在漫展：{0} {1}栏目下，与{2}的集邮已被删除"),
    /**
     * 0 类型
     * 1 评论内容
     */
    COMMENT_ONE("comment_one", "您在{0}下的评论：{1} 已被删除"),
    /**
     * 0 类型
     * 1 回复内容
     */
    COMMENT_TWO("comment_two", "您在{0}下的回复：{1} 已被删除"),
    ;

    public final String type;
    public final String content;

    MyEnumAdminDeleteMessageTemplate(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static MyEnumAdminDeleteMessageTemplate getEnumByType(String type) {
        for (MyEnumAdminDeleteMessageTemplate item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return null;
    }
}
