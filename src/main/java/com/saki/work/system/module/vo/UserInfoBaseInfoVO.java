package com.saki.work.system.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoBaseInfoVO {
    private Long id;

    @ApiModelProperty("昵称")
    private String nick;

    @ApiModelProperty("性别")
    private String sexKey;

    @ApiModelProperty("头像")
    private String faceImg;

    @ApiModelProperty("经验")
    private Integer levelExp;

    @ApiModelProperty("正在使用的认证")
    private String authUse;

    @ApiModelProperty("认证列表")
    private String authJson;

    @ApiModelProperty("简介")
    private String content;

    @ApiModelProperty("关注数")
    private Integer followNum;

    @ApiModelProperty("粉丝数")
    private Integer fansNum;

    @ApiModelProperty("打赏数")
    private Integer rewardMonthNum;

    @ApiModelProperty("获赞总数")
    private Integer likeNum;
}
