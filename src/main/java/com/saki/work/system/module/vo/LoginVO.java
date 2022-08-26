package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lzh
 * @date 2020/10/9 - 14:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    @ApiModelProperty(value = "header value")
    private String token;

    @ApiModelProperty(value = "用户id")
    private Long userInfoId;

    @ApiModelProperty(value = "角色列表")
    private List<String> roleList;
}
