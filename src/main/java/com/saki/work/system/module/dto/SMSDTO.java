package com.saki.work.system.module.dto;

import com.saki.work.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSDTO extends BaseDTO {
    @ApiModelProperty(value = "code类型",required = true)
    private String codeType;

    @ApiModelProperty(value = "图形验证码id",required = true)
    private String imgVerifyUUID;

    @ApiModelProperty(value = "图形验证码",required = true)
    private String imgVerifyCode;

    @ApiModelProperty(value = "注册时需要使用",required = true)
    private String phone;
}
