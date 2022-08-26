package com.saki.work.system.module.bo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVisitPageBO {
    @ApiModelProperty(value = "当前页", required = true)
    private Integer page;

    @ApiModelProperty(value = "每页行数", required = true)
    private Integer pageSize;
}
