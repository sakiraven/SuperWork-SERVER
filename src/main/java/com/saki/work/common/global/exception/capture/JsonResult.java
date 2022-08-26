package com.saki.work.common.global.exception.capture;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class JsonResult<Data> implements Serializable {

    private String result;   //返回码 非0即失败
    private String msg; //消息提示
    private Map<String, Object> data; //返回的数据
    private Boolean isSuccess;

    public JsonResult() {

    }

    ;

    public JsonResult(String result, String msg, Map<String, Object> data, Boolean isSuccess) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public static JsonResult success() {
        return success(new HashMap(0));
    }

    public static JsonResult success(Map<String, Object> data) {
        return new JsonResult("0", "解析成功", data, true);
    }


    public static JsonResult failed(String code, String msg, Boolean isSuccess) {
        return new JsonResult(code, msg, new HashMap(0), isSuccess);
    }
}
