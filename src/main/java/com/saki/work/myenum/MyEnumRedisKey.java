package com.saki.work.myenum;


import com.saki.work.utils.LogUtil;

public enum MyEnumRedisKey {
    // 作业是否可以评价 0 = work_id  hash_key = user_info_id value = comment_type(string)
    TYPE_HASH_KEY_WORK_COMMENT_0("work:{0}:comment", "作业是否可以评价"),


    // 图形验证码 0 == uuid 1 == code
    TYPE_STRING_KEY_SYSTEM_VERIFY_IMG_CODE_0_1("system:verify_img_code_{0}_{1}", "图形验证码"),
    // 短信验证码 0 == phone 1 == code
    TYPE_STRING_KEY_SYSTEM_VERIFY_PHONE_CODE_0_1("system:verify_phone_code:{0}_{1}", "短信验证码"),
    // 验证码最后发送时间 0 = phone value = 毫秒级时间戳
    TYPE_STRING_KEY_SYSTEM_VERIFY_PHONE_CODE_LAST_TIME_0("system:verify_phone_code:last_time_{0}", "验证码最后发送时间"),
    // 验证码请求次数 0 = phone value = 请求次数
    TYPE_STRING_KEY_SYSTEM_VERIFY_PHONE_CODE_TODAY_COUNT_0("system:verify_phone_code:today_count_{0}", "今日请求次数"),
    // cos 临时密钥
    TYPE_STRING_KEY_SECRET_COS_JSON("scret:cos_json", "cos 临时密钥"),
    // 字典表 0 = key
    TYPE_STRING_KEY_SYSTEM_DIC_0("system:dic:{0}", "字典表"),
    // 橘色信息 hash key = userInfoId
    TYPE_HASH_KEY_USER_INFO_ROLE("user_info:role", "角色信息"),
    // 注册排名
    TYPE_STRING_KEY_SYSTEM_REGISTER_RANK("system:register_rank", "注册排名"),
    ;

    public final String type;
    public final String content;

    MyEnumRedisKey(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static MyEnumRedisKey getEnumByType(String type) {
        for (MyEnumRedisKey item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return null;
    }
}
