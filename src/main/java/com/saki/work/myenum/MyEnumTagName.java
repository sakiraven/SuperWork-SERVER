package com.saki.work.myenum;

public enum MyEnumTagName {
    PUZZLE_SOLVING("Puzzle solving", "解谜"),
    SINGLE_PLAYER("Single player", "单打"),
    STANDARD("Standard", "标准"),
    SPEEDRUN("Speedrun", "记时挑战"),
    Autoscroll("Autoscroll", "自动卷轴"),
    Themed("Themed", "机关设计"),
    Multiplayer_versus("Multiplayer versus", "多人对战"),
    Short_and_sweet("Short and sweet", "一次通过"),
    Auto_mario("Auto mario", "自动马力欧"),
    Music("Music", "音乐"),
    Art("Art", "美术"),
    Technical("Technical", "技巧"),
    Shooter("Shooter", "射击"),
    Link("Link", "林克"),
    ;

    public final String key;
    public final String content;

    MyEnumTagName(String key, String content) {
        this.key = key;
        this.content = content;
    }

    public static String getContentByKey(String key){
        for (MyEnumTagName myEnumTagName : values()) {
            if (myEnumTagName.key.equals(key)){
                return myEnumTagName.content;
            }
        }
        return "未知";
    }
}
