package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lzh
 * @date 2020/10/9 - 14:16
 */
@Data
public class LoginBO {
    @NotBlank
    @ApiModelProperty(value = "登录方式", required = true)
    private String loginType;

    @ApiModelProperty(value = "手机号", required = false)
    private String account;

    @ApiModelProperty(value = "手机验证码", required = false)
    private String phoneCode;

    @ApiModelProperty(value = "苹果登录ID", required = false)
    private String appleId;

    @ApiModelProperty(value = "微信登录ID", required = false)
    private String wechatId;

    @ApiModelProperty(value = "qq登录ID", required = false)
    private String qqId;

    @ApiModelProperty(value = "主人id", required = false)
    private String masterId;
}
