package com.wojtek.noteshare.util;

import org.springframework.util.DigestUtils;

public class PasswordUtil {

    private static final String SALT = "NordSalt";

    public static String encryptPassword(String password) {
        String saltedPassword = SALT + password;
        return DigestUtils.md5DigestAsHex(saltedPassword.getBytes());
    }
}
