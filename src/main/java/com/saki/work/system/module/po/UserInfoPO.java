package com.saki.work.system.module.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserInfo)业务PO
 *
 * @author lzh
 * @since 2021-12-21 00:06:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "user_info")
public class UserInfoPO implements Serializable {
    private static final long serialVersionUID = 363115818080096788L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nick")
    private String nick;

    /**
     * 性别
     */
    @TableField(value = "sex_key")
    private String sexKey;

    /**
     * 头像
     */
    @TableField(value = "face_img_json")
    private String faceImgJson;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "is_delete")
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 简介
     */
    @TableField(value = "content")
    private String content;

    /**
     * 注册排名
     */
    @TableField(value = "register_rank")
    private Integer registerRank;

    /**
     * 微信id
     */
    @TableField(value = "wechat_id")
    private String wechatId;

    /**
     * qq id
     */
    @TableField(value = "qq_id")
    private String qqId;

    /**
     * 苹果id
     */
    @TableField(value = "apple_id")
    private String appleId;

}