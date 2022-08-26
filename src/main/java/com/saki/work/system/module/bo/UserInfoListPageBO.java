package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserInfo)分页查询接口对外BO
 *
 * @author lzh
 * @since 2021-07-14 11:06:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoListPageBO implements Serializable {
    private static final long serialVersionUID = -47842504311853612L;

    @ApiModelProperty(value = "当前页", required = true)
    private Long page;

    @ApiModelProperty(value = "每页行数", required = true)
    private Long pageSize;

    private Long id;

    @ApiModelProperty(value = "盐", required = false)
    private String salt;

    @ApiModelProperty(value = "账号", required = false)
    private String account;

    @ApiModelProperty(value = "密码", required = false)
    private String password;

    @ApiModelProperty(value = "昵称", required = false)
    private String nick;

    @ApiModelProperty(value = "性别", required = false)
    private Boolean sex;

    @ApiModelProperty(value = "头像", required = false)
    private String faceImg;

    @ApiModelProperty(value = "是否删除", required = false)
    private Boolean isDelete;

    @ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;

    @ApiModelProperty(value = "注册时间", required = false)
    private Date registerTime;
}