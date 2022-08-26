package com.saki.work.module.bo;

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
public class WorkPutInfoBO {
    @NotBlank
    @ApiModelProperty(value = "留言",required = true)
    private String mineMessage;

    @NotNull
    @ApiModelProperty(value = "课程id",required = true)
    private String courseId;
}
