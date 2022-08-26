package com.saki.work.system;

import com.saki.work.service.WorkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;

@Log4j2
@Configuration
@EnableScheduling
public class Timing {
//    @Autowired
//    private CosUtil cosUtil;
//
//    public void syncRedisToMysql() {
//        // 相集喜欢数
//        // 相集不喜欢数
//    }
//
//    @Scheduled(fixedRate = 1200000)
//    public void cosSTS() {
//        this.log.info("开始加载cos临时密钥");
//        this.cosUtil.testGetCredential();
//        this.cosUtil.testUrl();
//        this.log.info("结束加载cos临时密钥");
//    }

    @Resource
    private WorkService workService;


    @Scheduled(fixedRate = 60000)
    public void cosSTS() {
        System.out.println("更新地图信息");
        this.workService.postInit();
    }
}
