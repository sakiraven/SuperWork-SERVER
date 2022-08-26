package com.saki.work.common.websocket;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.saki.work.myenum.MyEnumWebSocketType;
import com.saki.work.system.module.dto.UserInfoDTO;
import com.saki.work.system.service.UserInfoService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@ServerEndpoint(value = "/websocket/{token}", configurator = WebSocketConfiguration.class)
@Component
public class WebSocketServer {

    /**
     * 当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 用来存放每个客户端对应的 WebSocketServer 对象
     */
    private static ConcurrentHashMap<Long, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收 token
     */
    private Long userInfoId = 0L;

    public static WebSocketServer getOnlineByUserInfoId(Long receiveUserInfoId) {
        return webSocketMap.get(receiveUserInfoId);
    }
    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, String userInfoId) throws IOException {
        log.info("发送消息到:" + userInfoId + "，报文:" + message);
        if (StrUtil.isNotBlank(userInfoId) && webSocketMap.containsKey(userInfoId)) {
            webSocketMap.get(userInfoId).sendMessage(message);
        } else {
            log.error("用户" + userInfoId + ",不在线！");
        }
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token) {
        this.session = session;
        this.userInfoId = Long.parseLong(StpUtil.getLoginIdByToken(token).toString());
        if (webSocketMap.containsKey(userInfoId)) {
            webSocketMap.remove(userInfoId);
            webSocketMap.put(userInfoId, this);
        } else {
            webSocketMap.put(userInfoId, this);
            addOnlineCount();
        }
        log.info("用户连接:" + userInfoId + ",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage(new Gson().toJson(new WebSocketVO(MyEnumWebSocketType.CONNECTION.type, "")));
        } catch (IOException e) {
            log.error("用户:" + userInfoId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userInfoId)) {
            webSocketMap.remove(userInfoId);
            subOnlineCount();
        }
        log.info("用户退出:" + userInfoId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.info("用户消息:" + userInfoId + ",报文:" + message);
        if (!StringUtils.isEmpty(message)) {
            try {
                // todo 接收消息
                WebSocketVO webSocketVO = new Gson().fromJson(message, WebSocketVO.class);
                if (webSocketVO.getType().equals(MyEnumWebSocketType.HEART.type)) {
                    // 心跳立即回复
                    this.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userInfoId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /**
     * 实现服务器主动推送
     */
    public void sendWebSocketVO(WebSocketVO webSocketVO) throws IOException {
        this.session.getBasicRemote().sendText(new Gson().toJson(webSocketVO));
    }
    public static synchronized AtomicInteger getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.getAndIncrement();
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.getAndDecrement();
    }
}