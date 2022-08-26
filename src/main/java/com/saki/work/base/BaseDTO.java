package com.saki.work.base;

import com.saki.work.system.module.dto.COSImageDTO;
import com.saki.work.system.module.vo.UserInfoNumVO;
import com.saki.work.system.module.vo.UserInfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @author lzh
 * @date 2020/9/14 - 11:17
 */
@Data
public class BaseDTO {
    @ApiModelProperty(value = "上次查询最后一个id 用于分页", required = true)
    private Long lastId;

    @ApiModelProperty("当前页")
    private Integer page;

    @ApiModelProperty("每页行数")
    private Integer pageSize;

    @ApiModelProperty("用户信息vo")
    private UserInfoVO userInfoVO;

    @ApiModelProperty("用户信息id")
    private Long sendUserInfoId;

    @ApiModelProperty("用户信息id")
    private Long receiveUserInfoId;

    @ApiModelProperty("发送用户信息vo")
    private UserInfoVO sendUserInfoVO;

    @ApiModelProperty("接收用户信息vo")
    private UserInfoVO receiveUserInfoVO;

    @ApiModelProperty("创建时间标签")
    private String createTimeLabel;

    @ApiModelProperty("未读消息数量")
    private UserInfoNumVO userInfoNumVO;

    @ApiModelProperty(value = "是否存在", required = false)
    private Boolean isExist;

    @ApiModelProperty(value = "图片",required = true)
    private COSImageDTO cosImageDTO;

    @ApiModelProperty(value = "最后一条时间条件",required = true)
    private String lastDateTime;

    @ApiModelProperty(value = "时间条件",required = true)
    private String dateTimeLimit;

    @ApiModelProperty("创建时间戳")
    private Long createTimeStamp;

    @ApiModelProperty("登陆用户id")
    private Long loginUserInfoId;

    @ApiModelProperty("角色列表")
    private List<String> roleList;
}
