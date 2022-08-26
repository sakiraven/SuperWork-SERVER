package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserInfo)根据主键获取对象接口对外VO
 *
 * @author lzh
 * @since 2021-07-14 11:06:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveUserInfoVO implements Serializable {
    private static final long serialVersionUID = -24626747111383117L;

    private Long id;

    @ApiModelProperty(value = "昵称", required = false)
    private String nick;

    @ApiModelProperty(value = "头像", required = false)
    private String faceImg;

}