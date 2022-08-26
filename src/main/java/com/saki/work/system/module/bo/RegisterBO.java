package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzh
 * @date 2020/10/12 - 14:40
 */
@Data
public class RegisterBO {
    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

}
