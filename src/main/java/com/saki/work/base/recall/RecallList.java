package com.saki.work.base.recall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.myenum.MyEnumMessage;
import com.saki.work.utils.CopyBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/5/28 - 14:22
 * @Description
 */
@Data
public class RecallList<T> {
    @ApiModelProperty("返回结果")
    private String result;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("是否成功")
    private Boolean isSuccess;

    @ApiModelProperty("结果对象")
    private Data data;

    public RecallList() {
    }

    /**
     * 初始化
     *
     * @param result  状态码
     * @param msg     结果信息
     * @param iPage   分页插件
     * @param toClass 将转为类型
     */
    public RecallList(String result, String msg, IPage iPage, Class toClass) {
        this.msg = msg;
        this.result = result;
        this.initList(iPage, toClass);
    }

    /**
     * 初始化
     *
     * @param iPage   分页插件
     * @param toClass 将转为类型
     */
    public RecallList(IPage iPage, Class toClass) {
        if (iPage.getRecords().isEmpty()) {
            throw new BaseBusinessException(MyEnumMessage.PAGE_END);
        }
        this.msg = MyEnumMessage.SUCCESS.content;
        this.result = MyEnumMessage.SUCCESS.type;
        this.initList(iPage, toClass);
    }


    /**
     * 时间偏移分页
     * 与
     * id偏移分页
     *
     * @param list   源数据
     * @param toClass 将转为类型
     */
    public RecallList(List list, Class toClass) {
        if (list.isEmpty()) {
            throw new BaseBusinessException(MyEnumMessage.PAGE_END);
        }
        this.msg = MyEnumMessage.SUCCESS.content;
        this.result = MyEnumMessage.SUCCESS.type;
        this.initList(list, toClass);
    }

    private void initList(IPage iPage, Class toClass) {
        this.data = new Data();
        this.data.list = new ArrayList<T>();
        this.isSuccess = true;
        this.data.page = (int) iPage.getCurrent();
        // 1- dto 转 vo(toPo)
        CopyBean<T> objectCopyBean = new CopyBean<>();
        objectCopyBean.copyList(iPage.getRecords(), this.data.list, toClass);
    }
    private void initList(List list, Class toClass) {
        this.data = new Data();
        this.data.list = new ArrayList<T>();
        this.isSuccess = true;
        // 1- dto 转 vo(toPo)
        CopyBean<T> objectCopyBean = new CopyBean<>();
        objectCopyBean.copyList(list, this.data.list, toClass);
    }
    public RecallList(List pathList) {
        this.msg = MyEnumMessage.SUCCESS.content;
        this.result = MyEnumMessage.SUCCESS.type;
        this.data = new Data();
        this.data.list = new ArrayList();
        this.data.list = pathList;
        this.isSuccess = true;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @ApiModelProperty("结果对象")
        private List list;

        @ApiModelProperty("当前页")
        private Integer page;
    }
}
