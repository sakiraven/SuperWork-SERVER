package com.saki.work.system.module.dto;

import com.google.gson.Gson;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 默认值为"" 非 null
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class COSImageDTO {
    @ApiModelProperty(value = "源名称", required = false)
    private String originObjectName = "";

    @ApiModelProperty(value = "缩略图名称", required = false)
    private String miniObjectName = "";

    @ApiModelProperty(value = "图片唯一标识", required = false)
    private String id = "";

    @ApiModelProperty(value = "日期", required = false)
    private String date = "";

    public static COSImageDTO emptyDTO() {
        return COSImageDTO.builder()
                .originObjectName("default/default-face.png")
                .miniObjectName("default/default-face.png")
                .id(UUID.randomUUID().toString())
                .date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .build();
    }

    public static String registerDTOJson() {
        COSImageDTO cosImageDTO = COSImageDTO.builder()
                .originObjectName("default/default-face.png")
                .miniObjectName("default/default-face.png")
                .id(UUID.randomUUID().toString())
                .date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .build();
        return new Gson().toJson(cosImageDTO);
    }
}
