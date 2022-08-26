package com.saki.work.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saki.work.system.module.dto.PermissionDTO;
import com.saki.work.system.module.po.PermissionPO;

import java.io.Serializable;
import java.util.Set;

/**
 * (Permission)表服务接口
 *
 * @author lzh
 * @since 2021-07-14 16:38:19
 */
public interface PermissionService extends IService<PermissionPO> {
    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    PermissionDTO getByPK(Serializable id);

    /**
     * 通用分页查询
     *
     * @return 实例对象
     */
    IPage listPage(PermissionDTO permissionDTO);

    /**
     * 通用删除
     *
     * @param id
     */
    void delete(Serializable id);

    /**
     * 根据用户id获取 权限
     *
     * @param userInfoId
     * @return
     */
    Set<String> setPermissionByUserInfoId(String userInfoId);
}