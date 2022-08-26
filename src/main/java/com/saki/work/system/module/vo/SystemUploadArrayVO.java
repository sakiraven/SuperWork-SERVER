package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUploadArrayVO {
    @ApiModelProperty(value = "返回路径", required = true)
    private List<String> fdfsRelativePathList;
}

