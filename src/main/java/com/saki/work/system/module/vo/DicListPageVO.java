package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Dic)分页查询接口对外VO
 *
 * @author lzh
 * @since 2021-08-03 14:43:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DicListPageVO implements Serializable {
    private static final long serialVersionUID = 755348929227503920L;

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