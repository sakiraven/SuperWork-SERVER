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
public class UserInfoBaseInfoBO {
    @ApiModelProperty(value = "用户主键id", required = true)
    private Long userInfoId;

}
