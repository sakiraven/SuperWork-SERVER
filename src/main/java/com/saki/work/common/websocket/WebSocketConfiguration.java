package com.saki.work.common.websocket;

import cn.dev33.satoken.stp.StpUtil;
import com.saki.work.system.module.dto.UserInfoDTO;
import com.saki.work.system.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
public class WebSocketConfiguration extends ServerEndpointConfig.Configurator {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 将用户信息存储到socket的配置里
//        sec.getUserProperties().put("userInfoId", StpUtil.getLoginId());
        super.modifyHandshake(sec, request, response);
    }
}
