package com.saki.work.system.module.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserInfoRoleGroup)业务PO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "user_info_role_group")
public class UserInfoRoleGroupPO implements Serializable {
    private static final long serialVersionUID = 221813480344540558L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_info_id")
    private Long userInfoId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

}