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
 * (Permission)业务PO
 *
 * @author lzh
 * @since 2021-11-29 17:35:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "permission")
public class PermissionPO implements Serializable {
    private static final long serialVersionUID = -85223626199998630L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 权限
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 排序 升序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

}