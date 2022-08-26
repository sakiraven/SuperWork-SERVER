package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumDicKey {
    // 1. 基础类型
    BASE_PHOTO_COSER_PHO("photo_coser_pho", "base", "相集"),
    BASE_PHOTO_COSER_PHO_STAMP("photo_coser_pho_stamp", "base", "相集集邮"),
    BASE_PHOTO("photo", "base", "漫展"),
    BASE_MOMENT("moment", "base", "动态"),
    BASE_ANIMATION("animation", "base", "新番"),
    BASE_NEWS("news", "base", "新闻"),
    BASE_COMMENT_ONE("comment_one", "base", "评论"),
    BASE_COMMENT_TWO("comment_two", "base", "回复"),
    BASE_USER_INFO("user_info", "base", "用户信息用户信息"),
    BASE_MESSAGE_LEAVE("message_leave", "base", "留言添加"),
    BASE_MESSAGE_REPLY("message_reply", "base", "留言回复"),
    BASE_WORLD_MESSAGE("world_message", "base", "讨论"),

    ADMIN_DELETE_NEWS("admin_delete_news", "admin_delete", "新闻"),
    ADMIN_DELETE_COMMENT_ONE("admin_delete_comment_one", "admin_delete", "评论"),
    ADMIN_DELETE_COMMENT_TWO("admin_delete_comment_two", "admin_delete", "回复"),
    ADMIN_DELETE_PHOTO_COSER_PHO("admin_delete_photo_coser_pho", "admin_delete", "相集"),
    ADMIN_DELETE_PHOTO_COSER_PHO_STAMP("admin_delete_photo_coser_pho_stamp", "admin_delete", "相集集邮"),

    // 2. 动态表
    MOMENT_FK_INFO("fkInfo", "moment", "外部信息"),
    MOMENT_ORIGINAL("originalInfo", "moment", "原生信息"),

    // 3. 通知类型
    NOTICE_LIKE("notice_like", "notice", "喜欢通知"),
    NOTICE_COMMENT_ONE("notice_comment_one", "notice", "一级评论通知"),
    NOTICE_COMMENT_TWO("notice_comment_two", "notice", "二级评论通知"),
    NOTICE_COMMENT_AND_REPLY("notice_comment_and_reply", "notice", "一级评论与二级评论"),
    NOTICE_MESSAGE_AND_REPLY("notice_message_and_reply", "notice", "留言与回复留言"),
    NOTICE_STAMP("notice_stamp", "notice", "集邮通知"),
    NOTICE_NOTICE("notice_notice", "notice", "通知"),
    NOTICE_MESSAGE("notice_message", "notice", "留言"),
    NOTICE_REWARD("notice_reward", "notice", "打赏"),
    NOTICE_SYSTEM("notice_system", "notice", "系统通知"),

    // 4. photoCoserPho类型
    PHOTO_COSER_PHO_COSER("coser", "photo_coser_pho", "coser"),
    PHOTO_COSER_PHO_PHO("pho", "photo_coser_pho", "摄影师"),

    // 5. sms 类型
    LOGIN_OR_REGISTER("login_or_register", "sms", "注册或登录验证码"),

    // 6. 登录类型
    LOGIN_WECHAT("login_wechat","login","微信登录"),
    LOGIN_APPLE("login_apple","login","苹果登录"),
    LOGIN_QQ("login_qq","login","qq登录"),
    LOGIN_PHONE("login_phone","login","手机号登录"),
    LOGIN_MASTER("login_master","login","登陆码登陆"),

    // 7. 新闻类型
    NEWS_DAY_PUSH("day_push","news","日推"),
    NEWS_WEEK_PUSH("week_push","news","周推"),

    NEWS_STATUS_CHECK("check","news_status","审核中"),
    NEWS_STATUS_REJECT("reject","news_status","已拒绝"),
    NEWS_STATUS_POST("post","news_status","已发布"),

    // 8. 举报类型
    REPORT_STATUS_PENDING("pending","repost_status","待处理"),
    REPORT_STATUS_IGNORE("ignore","repost_status","忽略"),

    // 系统
    VERSION_IOS("version_ios","version","ios当前版本号"),
    VERSION_ANDROID("version_android","version","android当前版本号"),

    // 0. 默认
    ERROR_DEFAULT("error_default", "default", "丢失"),
    ;

    public final String key;
    public final String type;
    public final String describe;

    MyEnumDicKey(String key, String type, String describe) {
        this.key = key;
        this.type = type;
        this.describe = describe;
    }

    public static String getContentByType(String key) {
        for (MyEnumDicKey item : values()) {
            if (item.type.equals(key)) {
                return item.describe;
            }
        }
        LogUtil.notFoundMyEnum(key);
        return MyEnumDataDefault.UNDEFINED.content;
    }

    public static MyEnumDicKey getEnumByKey(String key) {
        for (MyEnumDicKey item : values()) {
            if (item.key.equals(key)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(key);
        return null;
    }
}
