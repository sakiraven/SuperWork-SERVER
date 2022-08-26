package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class COSSecretVO {
    @ApiModelProperty("临时id")
    private String tmpSecretId;

    @ApiModelProperty("临时key")
    private String tmpSecretKey;

    @ApiModelProperty("临时token")
    private String sessionToken;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("过期时间")
    private Long expiredTime;

}
