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
public class UserInfoNumVO {
    @ApiModelProperty("未读通知数")
    Integer unreadSystemNoticeNum;

    @ApiModelProperty("未读评论数")
    Integer unreadCommentReplyNum;

    @ApiModelProperty("未读留言数")
    Integer unreadMessageNum;

    @ApiModelProperty("未读留言数")
    Integer unreadLikeNum;

    @ApiModelProperty("未读打赏数")
    Integer unreadRewardNum;

    @ApiModelProperty("未读集邮数")
    Integer unreadStampNum;

    @ApiModelProperty("参展数量")
    Integer photoNum;

    @ApiModelProperty("集邮数量")
    Integer photoStampNum;

    @ApiModelProperty("获赞数量")
    Integer likeNum;

    @ApiModelProperty("日推数量")
    Integer newsDayPushNum;

    @ApiModelProperty("关注数")
    private Integer followNum;

    @ApiModelProperty("粉丝数")
    private Integer fansNum;
}
