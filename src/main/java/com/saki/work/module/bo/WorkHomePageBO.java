package com.saki.work.module.bo;

import com.saki.work.myenum.MyEnumOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkHomePageBO {
    @NotNull
    @ApiModelProperty("排序")
    private MyEnumOrder myEnumOrder;

    @ApiModelProperty("是否桀评")
    private Boolean isJieMessage;

    @NotNull
    @ApiModelProperty(value = "当前页",required = true)
    private Integer page;
}
