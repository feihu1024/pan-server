package com.feihu1024.panserver.common;

import org.apache.ftpserver.usermanager.Md5PasswordEncryptor;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义md5密码编码器 （用于ftpserver和springSecurity共用）
 */

@Component
public class CustomMD5PasswordEncoder implements PasswordEncryptor, PasswordEncoder {

    Md5PasswordEncryptor md5PasswordEncryptor;

    public CustomMD5PasswordEncoder() {
        this.md5PasswordEncryptor = new Md5PasswordEncryptor();
    }

    /**
     * @param password
     * @return encryptPassword
     * 用于springSecurity的加密
     */
    @Override
    public String encode(CharSequence password) {
        return md5PasswordEncryptor.encrypt(password.toString());
    }

    /**
     * @param prefixEncodedPassword
     * @param storedPassword
     * @return boolean
     * 用于springSecurity的匹配
     */
    @Override
    public boolean matches(CharSequence prefixEncodedPassword, String storedPassword) {
        // int prefixEndIndex = storedPassword.indexOf("}");
        // String extractEncodedPassword = storedPassword.substring(prefixEndIndex + 1);
        // return md5PasswordEncryptor.matches(prefixEncodedPassword.toString(),extractEncodedPassword);

        // 由前端进行加密，此处直接明文比较
        return prefixEncodedPassword.equals(storedPassword);
    }

    /**
     * @param password
     * @return encryptPassword
     * 用于ftpserver的加密
     */
    @Override
    public String encrypt(String password) {
        return md5PasswordEncryptor.encrypt(password);
    }

    /**
     * @param password
     * @param storedPassword
     * @return boolean
     * 用于ftpserver的匹配
     */
    @Override
    public boolean matches(String password, String storedPassword) {
        // 如果是服务端发起的登录，则添加server:前缀，此时明文比较即可
         if(password.startsWith("server:")){
             String prefixPassword = password.replace("server:","");
             return prefixPassword.equals(storedPassword);
         }else {
             return md5PasswordEncryptor.matches(password, storedPassword);
         }
    }
}
