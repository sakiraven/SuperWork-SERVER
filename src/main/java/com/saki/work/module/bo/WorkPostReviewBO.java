package com.saki.work.module.bo;

import com.saki.work.myenum.MyEnumApproveStatus;
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
public class WorkPostReviewBO {
    @NotNull
    @ApiModelProperty(value = "主键id",required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "审查状态",required = true)
    private MyEnumApproveStatus myEnumApproveStatus;

    @ApiModelProperty(value = "拒绝原因",required = true)
    private String rejectMessage;

}
