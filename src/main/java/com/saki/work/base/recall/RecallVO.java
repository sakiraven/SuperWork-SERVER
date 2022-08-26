package com.saki.work.base.recall;

import com.saki.work.myenum.MyEnumMessage;
import com.saki.work.utils.CopyBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzh
 * @date 2020/5/28 - 14:22
 * @Description
 */
@Data
public class RecallVO<T> {
    @ApiModelProperty("返回结果")
    private String result;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("是否成功")
    private Boolean isSuccess;

    @ApiModelProperty("结果对象")
    private T data;


    public RecallVO() {
        this.result = MyEnumMessage.SUCCESS.type;
        this.msg = MyEnumMessage.SUCCESS.content;
        this.isSuccess = true;
    }

//    public RecallVO(String result, String msg, Boolean isSuccess, T sourceVO, Class toClass) {
//        this.msg = msg;
//        this.result = result;
//        this.isSuccess = isSuccess;
//        CopyBean copyBean = new CopyBean();
//        copyBean.copyVO(sourceVO, this.data, toClass);
//    }

    public RecallVO(T sourceVO, Class toClass) {
        this.msg = MyEnumMessage.SUCCESS.content;
        this.result = MyEnumMessage.SUCCESS.type;
        this.isSuccess = true;
        CopyBean copyBean = new CopyBean();
        this.data = (T) copyBean.copyVO(sourceVO, this.data, toClass);
    }

    public RecallVO(T sourceVO) {
        this.msg = MyEnumMessage.SUCCESS.content;
        this.result = MyEnumMessage.SUCCESS.type;
        this.isSuccess = true;
        this.data = sourceVO;
    }

    public RecallVO(MyEnumMessage myEnumMessage, T sourceVO) {
        this.msg = myEnumMessage.content;
        this.result = myEnumMessage.type;
        this.isSuccess = myEnumMessage.isSuccess;
        this.data = sourceVO;
    }
}
