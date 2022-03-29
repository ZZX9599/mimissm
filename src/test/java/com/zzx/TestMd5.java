package com.zzx;

import com.zzx.utils.MD5Util;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public class TestMd5 {
    public static void main(String[] args) {
        String pwd=MD5Util.getMD5("JXLZZX79");
        System.out.println(pwd);
        System.out.println(pwd.length());
    }
}
