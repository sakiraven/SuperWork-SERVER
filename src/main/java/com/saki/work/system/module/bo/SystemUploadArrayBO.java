package com.saki.work.system.module.bo;

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
public class SystemUploadArrayBO {
    @ApiModelProperty(value = "类型", required = true)
    private String fileType;

    @ApiModelProperty(value = "文件", required = true)
    private List fileList;
}
