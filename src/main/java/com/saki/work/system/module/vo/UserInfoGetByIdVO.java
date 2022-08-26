package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserInfo)根据主键获取对象接口对外VO
 *
 * @author lzh
 * @since 2021-07-14 11:06:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoGetByIdVO implements Serializable {
    private static final long serialVersionUID = -24626747111383117L;

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