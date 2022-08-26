package com.saki.work.utils;


import java.util.*;
import java.util.Map.Entry;

/**
 * @author lzh
 * @date 2020/1/26 - 22:04
 */
public class ResultMap {
    /**
     * list转换
     *
     * @param pageInfoList
     * @return
     */
    public static List<Map<String, Object>> toReplaceKeyLowList(List<Map> pageInfoList) {
        List<Map<String, Object>> relist = new ArrayList<>();
        for (Map map : pageInfoList) {
            map = ResultMap.toReplaceKeyLow(map);
            relist.add(map);
        }
        return relist;
    }

    /**
     * 把map的key转换成驼峰命名
     *
     * @param map
     * @return
     */
    public static Map<String, Object> toReplaceKeyLow(Map<String, Object> map) {
        Map re_map = new HashMap();
        if (re_map != null) {
            Iterator var2 = map.entrySet().iterator();

            while (var2.hasNext()) {
                Entry<String, Object> entry = (Entry) var2.next();
                re_map.put(underlineToCamel((String) entry.getKey()), map.get(entry.getKey()));
            }
            map.clear();
        }
        return re_map;
    }

    public static final char UNDERLINE = '_';


    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(Character.toLowerCase(param.charAt(i)));
            }
        }
        return sb.toString();
    }


}
