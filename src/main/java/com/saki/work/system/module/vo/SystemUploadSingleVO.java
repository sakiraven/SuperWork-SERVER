package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUploadSingleVO {
    @ApiModelProperty("返回访问路径")
    private String fdfsRelativePath;
}
