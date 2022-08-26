package com.saki.work.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.saki.work.base.recall.RecallCUD;
import com.saki.work.base.recall.RecallList;
import com.saki.work.base.recall.RecallVO;
import com.saki.work.system.module.bo.*;
import com.saki.work.system.module.dto.UserInfoDTO;
import com.saki.work.system.module.vo.*;
import com.saki.work.system.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * (UserInfo)表服务实现类
 *
 * @author lzh
 * @since 2021-07-15 10:02:57
 */
@Api(tags = "(UserInfo)表服务实现类")
@Validated
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("通过主键查询单条数据")
    @PostMapping("/general/getbypk")
    public RecallVO getById(@NotNull Serializable id) {
        return new RecallVO(this.userInfoService.getByPK(id), UserInfoGetByIdVO.class);
    }

    @ApiOperation("翻页查询")
    @PostMapping("/general/listpage")
    public RecallList<UserInfoListPageVO> listPage(@Valid @RequestBody UserInfoListPageBO userInfoListPageBO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfoListPageBO, userInfoDTO);

        return new RecallList(this.userInfoService.listPage(userInfoDTO), UserInfoListPageVO.class);
    }

    @ApiOperation(
            value = "删除",
            notes = "删除规则"
    )
    @PostMapping("/general/delete")
    public RecallCUD delete(@NotNull Serializable id) {
        this.userInfoService.delete(id);
        return new RecallCUD();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public RecallVO<LoginVO> login(@RequestBody LoginBO loginBO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(loginBO, userInfoDTO);
        return this.userInfoService.login(userInfoDTO);
    }

    @ApiOperation("注销登录")
    @PostMapping("/loginout")
    public RecallCUD loginOut() {
        StpUtil.logout();
        return new RecallCUD();
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public RecallVO register(@RequestBody RegisterBO registerBO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(registerBO, userInfoDTO);
        this.userInfoService.register(userInfoDTO);
        return new RecallVO();
    }

    @ApiOperation("退出shiro登录")
    @PostMapping("logout")
    public RecallCUD logout(HttpServletRequest httpServletRequest) {
        StpUtil.logout();
        return new RecallCUD();
    }
}