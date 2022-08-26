package com.saki.work.system.module.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Dic)分页查询接口对外BO
 *
 * @author lzh
 * @since 2021-08-03 14:43:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DicListPageBO implements Serializable {
    private static final long serialVersionUID = -30159839981349388L;

    @ApiModelProperty(value = "当前页", required = true)
    private Long page;

    @ApiModelProperty(value = "每页行数", required = true)
    private Long pageSize;

    @ApiModelProperty(value = "主键", required = false)
    private String key;

    @ApiModelProperty(value = "类型", required = false)
    private String type;

    @ApiModelProperty(value = "描述", required = false)
    private String describe;

    @ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private Date updateTime;
}