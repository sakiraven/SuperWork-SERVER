package com.saki.work.common.filter;


import com.saki.work.common.global.exception.capture.BaseBusinessException;
import com.saki.work.myenum.MyEnumMessage;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LimitRequestAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    private static ConcurrentHashMap<String, ExpiringMap<String, Integer>> book = new ConcurrentHashMap<>();

    // 定义切点
    // 让所有有@LimitRequest注解的方法都执行切面方法
    @Pointcut("@annotation(limitRequest)")
    public void excudeService(LimitRequest limitRequest) {
    }

    @Around("excudeService(limitRequest)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, LimitRequest limitRequest) throws Throwable {
        // 获得request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 获取Map对象， 如果没有则返回默认值
        // 第一个参数是key， 第二个参数是默认值
        ExpiringMap<String, Integer> uc = book.getOrDefault(request.getRequestURI(), ExpiringMap.builder().variableExpiration().build());
        String ip = this.getRemortIP(request);
        Integer uCount = uc.getOrDefault(ip, 0);
        String ipKey = MessageFormat.format("gede:ip", ip);
        String blackIpKey = MessageFormat.format("gede:blackip", ip);
        this.redisTemplate.opsForZSet().incrementScore(ipKey, ip, 1);
        Long rank = this.redisTemplate.opsForZSet().rank(blackIpKey, ip);

        Object requestCountObject = this.redisTemplate.opsForZSet().score(ipKey, ip);
        if (requestCountObject != null) {
            Double countDouble = Double.parseDouble(requestCountObject.toString());
            if (countDouble >= 1000) {
                this.redisTemplate.opsForZSet().incrementScore(blackIpKey, ip, 1);
            }
        }

        if (rank != null) {
            throw new BaseBusinessException(MyEnumMessage.REQUEST_LIMIT);
        }

        if (uCount >= limitRequest.count()) { // 超过次数，不执行目标方法
            throw new BaseBusinessException(MyEnumMessage.INTERFACE_COUNT_ERROR);
        } else if (uCount == 0) { // 第一次请求时，设置有效时间
            uc.put(ip, uCount + 1, ExpirationPolicy.CREATED, limitRequest.time(), TimeUnit.MILLISECONDS);
        } else { // 未超过次数， 记录加一
            uc.put(ip, uCount + 1);
        }
        book.put(request.getRequestURI(), uc);

        // result的值就是被拦截方法的返回值
        Object result = proceedingJoinPoint.proceed();

        return result;
    }

    public String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }


}
