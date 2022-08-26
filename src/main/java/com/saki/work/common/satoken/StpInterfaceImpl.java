package com.saki.work.common.satoken;

import java.util.ArrayList;
import java.util.List;

import com.saki.work.system.service.UserInfoService;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpInterface;

import javax.annotation.Resource;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object userInfoId, String loginType) {
        List<String> roleList = this.userInfoService.listRoleName(Long.parseLong(userInfoId.toString()));
        return roleList;
    }
}
