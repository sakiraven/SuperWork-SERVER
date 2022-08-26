package com.saki.work.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkReviewPageBO {
    @ApiModelProperty(value = "当前页",required = true)
    private Integer page;
}
