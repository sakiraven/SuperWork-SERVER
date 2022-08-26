package com.saki.work.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzh
 * @date 2020/9/14 - 10:32
 */
@Data
public class BaseGetBO {
    @ApiModelProperty(value = "主键id", required = false)
    private Integer id;

    @ApiModelProperty(value = "当前时间", required = false)
    private String createTime;

    @ApiModelProperty(value = "开始时间", required = false)
    private String startTime;

    @ApiModelProperty(value = "结束时间", required = false)
    private String endTime;

    @ApiModelProperty(value = "当前页", required = false)
    private Integer page;

    @ApiModelProperty(value = "每页行数", required = false)
    private Integer pageSize;

}
