package com.saki.work.system.module.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemDTO {
    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件
     */
    private MultipartFile file;

    /**
     * 返回访问路径
     */
    private String fdfsRelativePath;

    @ApiModelProperty(value = "文件", required = true)
    private List<MultipartFile> fileList;

    @ApiModelProperty(value = "返回路径", required = true)
    private List<String> fdfsRelativePathList;

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

    @ApiModelProperty("版本号")
    private String version;
}
