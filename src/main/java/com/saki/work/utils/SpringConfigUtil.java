package com.saki.work.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringConfigUtil {
    @Value("${spring.profiles.active}")
    public String active;
}
