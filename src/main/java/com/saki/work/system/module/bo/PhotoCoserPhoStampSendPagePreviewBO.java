package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoCoserPhoStampSendPagePreviewBO {
    @NotNull
    @ApiModelProperty(value = "外键id", required = false)
    private Long photoCoserPhoId;

    @NotNull
    @ApiModelProperty(value = "外键id", required = false)
    private Long coserPhoUserInfoId;
}
