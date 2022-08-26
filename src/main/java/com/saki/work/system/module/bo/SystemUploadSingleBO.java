package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUploadSingleBO {
    @ApiModelProperty(value = "文件", required = true)
    MultipartFile file;
    @ApiModelProperty(value = "类型", required = true)
    private String fileType;
}
