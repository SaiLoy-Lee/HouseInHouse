package com.fy.tools;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2017/9/15.
 */
public class MD5HashPassword {
    //获取密文
    public static String getPassword(String hhUserUsername,String hhUserPassword){

        Md5Hash hash = new Md5Hash(hhUserPassword, hhUserUsername, 3);

        return hash.toString();
    }


}
