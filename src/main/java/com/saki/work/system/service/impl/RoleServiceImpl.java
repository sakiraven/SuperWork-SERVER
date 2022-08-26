package com.saki.work.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saki.work.system.mapper.RoleMapper;
import com.saki.work.system.module.dto.RoleDTO;
import com.saki.work.system.module.po.RolePO;
import com.saki.work.system.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * (Role)表服务实现类
 *
 * @author lzh
 * @since 2021-07-14 16:38:28
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public RoleDTO getByPK(Serializable id) {
        RoleDTO reRoleDTO = new RoleDTO();
        BeanUtils.copyProperties(this.getById(id), reRoleDTO);
        return reRoleDTO;
    }

    @Override
    public IPage listPage(RoleDTO roleDTO) {
        Page<RolePO> rolePOPage = new Page<>(roleDTO.getPage(), roleDTO.getPageSize());
        Page<RolePO> page = this.page(rolePOPage);
        return page;
    }

    @Override
    public void delete(Serializable id) {
        this.removeById(id);
    }

}