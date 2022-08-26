package com.saki.work.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.system.module.dto.RolePermissionGroupDTO;
import com.saki.work.system.module.po.RolePermissionGroupPO;

import java.io.Serializable;

/**
 * (RolePermissionGroup)表服务接口
 *
 * @author lzh
 * @since 2021-07-14 16:38:33
 */
public interface RolePermissionGroupService extends IService<RolePermissionGroupPO> {
    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    RolePermissionGroupDTO getByPK(Serializable id);

    /**
     * 通用分页查询
     *
     * @return 实例对象
     */
    IPage listPage(RolePermissionGroupDTO rolePermissionGroupDTO);

    /**
     * 通用删除
     *
     * @param id
     */
    void delete(Serializable id);
}