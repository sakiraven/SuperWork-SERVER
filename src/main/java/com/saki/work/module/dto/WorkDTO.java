package com.saki.work.module.dto;

import com.saki.work.module.dto.base.WorkBaseDTO;
import com.saki.work.myenum.MyEnumApproveStatus;
import com.saki.work.myenum.MyEnumCommentType;
import com.saki.work.myenum.MyEnumOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Work)业务DTO
 *
 * @author lzh
 * @since 2022-08-16 10:11:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDTO extends WorkBaseDTO implements Serializable {
    private static final long serialVersionUID = 736499857597187568L;

    @ApiModelProperty("排序")
    private MyEnumOrder myEnumOrder;

    @ApiModelProperty("是否可以评价")
    private Boolean isMineMessage;

    @ApiModelProperty("评价类型")
    private String mineMessageType;

    @ApiModelProperty("喜欢数量标签")
    private String likeNumsLabel;

    @ApiModelProperty("不喜欢数量标签")
    private String notLikeNumsLabel;

    @ApiModelProperty("普通数量标签")
    private String ordinaryNumsLabel;

    @ApiModelProperty(value = "评论类型",required = true)
    private MyEnumCommentType myEnumCommentType;

    @ApiModelProperty(value = "审查状态",required = true)
    private MyEnumApproveStatus myEnumApproveStatus;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<String> tagNameList;
}