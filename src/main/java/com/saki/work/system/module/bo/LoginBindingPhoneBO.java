package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginBindingPhoneBO {
    @NotBlank
    @ApiModelProperty(value = "登录方式", required = true)
    private String loginType;

    @NotBlank
    @ApiModelProperty(value = "手机号", required = true)
    private String account;

    @NotBlank
    @ApiModelProperty(value = "手机验证码", required = true)
    private String phoneCode;

    @ApiModelProperty(value = "苹果登录ID", required = false)
    private String appleId;

    @ApiModelProperty(value = "微信登录ID", required = false)
    private String wechatId;

    @ApiModelProperty(value = "qq登录ID", required = false)
    private String qqId;

    @ApiModelProperty(value = "昵称", required = false)
    private String otherPlatformNick;

    @ApiModelProperty(value = "头像url", required = false)
    private String otherPlatformFaceImgUrl;
}

