package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoCenterPageHomeBO {
    @ApiModelProperty(value = "用户id", required = false)
    private Long userInfoId;
}
