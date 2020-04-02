package com.ikutarian.util;

import org.springframework.util.DigestUtils;

public class Md5Utils {

    public static String getMd5Str(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }
}
