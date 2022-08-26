package com.saki.work.utils;

import com.saki.work.common.global.exception.capture.BaseBusinessException;
import org.springframework.util.DigestUtils;

import java.security.SecureRandom;

public class Md5Util {
    public static String getPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    public static String getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            sr.nextBytes(bytes);
            return new String(bytes);
        } catch (Exception ex) {
            BaseBusinessException.throwRunTimeException();
        }
        return "";
    }
}
