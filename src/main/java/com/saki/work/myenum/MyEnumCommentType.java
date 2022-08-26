package com.saki.work.myenum;

public enum MyEnumCommentType {
    LIKE("LIKE", "喜欢"),
    NOT_LIKE("NOT_LIKE", "不喜欢"),
    ORDINARY("ORDINARY", "普通"),
    ;

    public final String key;
    public final String content;

    MyEnumCommentType(String key, String content) {
        this.key = key;
        this.content = content;
    }
}
