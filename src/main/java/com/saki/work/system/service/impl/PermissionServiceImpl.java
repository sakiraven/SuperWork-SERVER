package com.saki.work.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saki.work.system.mapper.PermissionMapper;
import com.saki.work.system.module.dto.PermissionDTO;
import com.saki.work.system.module.po.PermissionPO;
import com.saki.work.system.service.PermissionService;
import com.saki.work.system.service.RolePermissionGroupService;
import com.saki.work.system.service.UserInfoRoleGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;

/**
 * (Permission)表服务实现类
 *
 * @author lzh
 * @since 2021-07-14 16:38:20
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionPO> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private UserInfoRoleGroupService userInfoRoleGroupService;

    @Resource
    private RolePermissionGroupService rolePermissionGroupService;

    @Override
    public PermissionDTO getByPK(Serializable id) {
        PermissionDTO rePermissionDTO = new PermissionDTO();
        BeanUtils.copyProperties(this.getById(id), rePermissionDTO);
        return rePermissionDTO;
    }

    @Override
    public IPage listPage(PermissionDTO permissionDTO) {
        Page<PermissionPO> permissionPOPage = new Page<>(permissionDTO.getPage(), permissionDTO.getPageSize());
        Page<PermissionPO> page = this.page(permissionPOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

    /**
     * 获取对象
     * 1. 获取用户角色
     * 2. 根据角色获取权限
     *
     * @param userInfoId
     * @return
     */
    @Override
    public Set<String> setPermissionByUserInfoId(String userInfoId) {
        Set<String> rePermsList = this.permissionMapper.setPermissionByUserInfoId(userInfoId);
        return rePermsList;
    }

}