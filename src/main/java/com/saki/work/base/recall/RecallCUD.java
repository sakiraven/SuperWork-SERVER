package com.saki.work.base.recall;

import com.saki.work.myenum.MyEnumMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzh
 * @date 2020/6/10 - 12:51
 */
@Data
public class RecallCUD {
    @ApiModelProperty(value = "返回结果", notes = "test")
    private String result;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("状态")
    private Boolean isSuccess;

    public RecallCUD(MyEnumMessage myEnumMessage, Boolean isSuccess) {
        this.result = myEnumMessage.type;
        this.msg = myEnumMessage.content;
        this.isSuccess = isSuccess;
    }

    public RecallCUD(MyEnumMessage myEnumMessage) {
        this.result = myEnumMessage.type;
        this.msg = myEnumMessage.content;
        this.isSuccess = myEnumMessage.isSuccess;
    }

    public RecallCUD() {
        this.result = MyEnumMessage.SUCCESS.type;
        this.msg = MyEnumMessage.SUCCESS.content;
        this.isSuccess = true;
    }
}
