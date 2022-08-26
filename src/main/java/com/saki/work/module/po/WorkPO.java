package com.saki.work.module.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Work)业务PO
 *
 * @author lzh
 * @since 2022-08-17 11:24:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "as_work")
public class WorkPO implements Serializable {
    private static final long serialVersionUID = 760586624134905503L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户信息
     */
    @TableField(value = "user_info_id")
    private Long userInfoId;

    /**
     * 关卡id 用户查询关卡详情
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 头像路径
     */
    @TableField(value = "face_path")
    private String facePath;

    /**
     * 整个缩略图
     */
    @TableField(value = "entire_path")
    private String entirePath;

    /**
     * 缩略图
     */
    @TableField(value = "thumbnail_path")
    private String thumbnailPath;

    /**
     * 用户自己的留言
     */
    @TableField(value = "mine_message")
    private String mineMessage;

    /**
     * 超级小桀是否评论
     */
    @TableField(value = "is_jie_message")
    private Boolean isJieMessage;

    /**
     * 超级小桀评价类型
     */
    @TableField(value = "jie_message_type")
    private String jieMessageType;

    /**
     * 超级小桀留言
     */
    @TableField(value = "jie_message")
    private String jieMessage;

    /**
     * 是否通过审核
     */
    @TableField(value = "approve_status")
    private String approveStatus;

    /**
     * 拒绝原因
     */
    @TableField(value = "reject_message")
    private String rejectMessage;

    /**
     * 标签json
     */
    @TableField(value = "tags_json")
    private String tagsJson;

    /**
     * 喜欢数
     */
    @TableField(value = "like_nums")
    private Integer likeNums;

    /**
     * 不喜欢数量
     */
    @TableField(value = "not_like_nums")
    private Integer notLikeNums;

    /**
     * 普通数量
     */
    @TableField(value = "ordinary_nums")
    private Integer ordinaryNums;

    /**
     * 是否初始化
     */
    @TableField(value = "is_init")
    private Boolean isInit;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 初始化次数
     */
    @TableField(value = "init_count")
    private Integer initCount;

}