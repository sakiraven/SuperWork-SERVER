package com.saki.work.system.module.dto;

import com.saki.work.system.module.dto.base.UserInfoBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserInfo)业务DTO
 *
 * @author lzh
 * @since 2021-12-21 00:06:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO extends UserInfoBaseDTO implements Serializable {
    private static final long serialVersionUID = 381817502754497970L;

    private Long id;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nick;

    @ApiModelProperty("性别")
    private String sexKey;

    @ApiModelProperty("头像")
    private String faceImgJson;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("简介")
    private String content;

    @ApiModelProperty("注册排名")
    private Integer registerRank;

    @ApiModelProperty("微信id")
    private String wechatId;

    @ApiModelProperty("qq id")
    private String qqId;

    @ApiModelProperty("苹果id")
    private String appleId;


}