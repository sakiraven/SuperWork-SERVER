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
 * (Role)业务PO
 *
 * @author lzh
 * @since 2021-11-29 17:35:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "role")
public class RolePO implements Serializable {
    private static final long serialVersionUID = -84436435056942283L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

}