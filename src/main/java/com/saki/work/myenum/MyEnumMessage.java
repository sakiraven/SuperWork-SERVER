package com.saki.work.myenum;

/**
 * @Description: 服务端状态
 */
public enum MyEnumMessage {
    // 服务器状态
    SUCCESS("200", "成功", true),
    METHOD_ERROR("500", "后端异常", false),
    FAIL("204", "失败", false),
    NOT_FOUND("404", "查找不到页面", false),
    NOT_LOGIN("401", "未登录", false),
    UNAUTHORIZED("403", "未授权", false),
    RUN_TIME_EXCEPTION("301", "自定义异常", false),
    NULL_POINTER_EXCEPTION("501", "空指针", false),
    UPLOAD_FILE_ERROR("502", "文件上传失败", false),
    UPLOAD_FILES_ERROR("503", "批量上传失败", false),

    NOT_FOUND_BY_PK("404001", "找不到主键信息", false),
    PAGE_END("404002", "没有下一页数据", false),
    NOT_FOUND_COMMENT_ONE("404003", "评论已被删除", false),
    NOT_FOUND_COMMENT_TWO("404004", "回复已被删除", false),
    NOT_FOUND_PHOTO_COSER_PHO("404005", "相集已被删除", false),
    NOT_FOUND_PHOTO("404006", "漫展已被删除", false),
    NOT_FOUND_PHOTO_COSER_PHO_STAMP("404007", "集邮已被删除", false),
    NOT_FOUND_USER_INFO("404008", "未查询到用户信息", false),
    NOT_FOUND_MESSAGE("404009", "未查询到留言信息", false),
    NOT_FOUND_NEWS("404010", "未查询到日推", false),
    NOT_AUTH("40011", "无权限", false),

    // 错误信息
    ACCOUNT_PASSWORD("50001", "用户名或者密码错误", false),
    NOT_FOUND_USER_PK("50002", "找不到用户信息", false),
    REGISTER_ERROR_ACCOUNT_EXIST("50003", "注册失败,用户名已存在", false),
    TOKEN_NOT_FOUND("50005", "token不存在", false),
    TOKEN_EXPIRE("50006", "token过期请重新登陆", false),
    TOKEN_FALSE("50007", "伪造token", false),
    INTERFACE_COUNT_ERROR("50008", "请求频率过高,请稍后", false),
    IMG_VERIFY_CODE_EXPIRE("50009", "图形验证码错误或已过期,请重试", false),
    PHONE_VERIFY_CODE_MAX_REQUEST("50010", "短信验证码每日只能发送10次", false),
    PHONE_VERIFY_CODE_INTERVAL_LENGTH("50011", "短信验证码已发送,请1分钟后尝试", false),
    PHOTO_VERIFY_CODE_EXPIRE("50012", "短信验证码错误或已过期,请重试", false),
    BINDING_PHONE("50013", "请绑定手机号", false),
    PHONE_REGISTERED("50014", "手机号已注册,请更换", false),
    NEWS_STATUS_NOT_POST("50015", "推送状态未发布,作者可能修改版本中", false),
    NEWS_UPDATE_STATUS_ERROR("50016", "您已提交修改", false),
    DATA_EXIST("50017", "已存在相同的相集,刷新试试", false),
    REQUEST_LIMIT("50018", "请求已达上限, 请明日再试", false),

    EXIST_COURSE_ID("50019", "当前关卡ID正在等待审核或者已通过审核", false),
    MASTER_ID_ERROR("50020", "登陆码错误", false),
    STATUS_ERROR("50021", "状态不符合预期", false),

    // 用户信息

    // 留言相关
    CANT_MESSAGE_EXIST("52101", "只能留言一条", false),
    // 动态相关
    MOMENT_NOT_FOLLOW("52201", "只能留言一条", false),
    // redis 相关
    REDIS_NOT_FOUND("52301", "没有获取到重要信息", false),
    ;

    public final String type;
    public final String content;
    public final Boolean isSuccess;

    MyEnumMessage(String type, String content, Boolean isSuccess) {
        this.type = type;
        this.content = content;
        this.isSuccess = isSuccess;
    }

    public static String getContentByType(String type) {
        for (MyEnumMessage item : values()) {
            if (item.type.equals(type)) {
                return item.content;
            }
        }
        return "";
    }

    public String getType() {
        return type;
    }
}
