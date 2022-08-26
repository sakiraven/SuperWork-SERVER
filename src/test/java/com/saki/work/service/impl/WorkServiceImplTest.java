package com.saki.work.service.impl;

import com.saki.work.service.WorkService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkServiceImplTest {
    @Resource
    private WorkService workService;

    @Test
    void test() {
        this.workService.postInit();
//        this.workService.putTestData();
    }


}
