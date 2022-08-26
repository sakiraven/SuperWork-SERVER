package com.saki.work.system.service.impl;

import com.saki.work.common.redis.RedisUtil;
import com.saki.work.myenum.MyEnumDicKey;
import com.saki.work.myenum.MyEnumRedisKey;
import com.saki.work.system.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
}
