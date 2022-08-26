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
 * (RolePermissionGroup)业务PO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "role_permission_group")
public class RolePermissionGroupPO implements Serializable {
    private static final long serialVersionUID = 358827180483839624L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Long permissionId;

}