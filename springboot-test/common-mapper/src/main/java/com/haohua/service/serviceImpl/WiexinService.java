package com.haohua.service.serviceImpl;    /*
 * @author  Administrator
 * @date 2018/12/6
 */


import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WiexinService {

    /**
     * 将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String check(String token, String timestamp, String nonce) {
        String[] array = {token, timestamp, nonce};
        Arrays.sort(array);
        String appendStr = array[0] + array[1] + array[2];
        //进行sa1加密
        String encryptString = DigestUtils.sha1Hex(appendStr.getBytes());
        return encryptString;

    }

    private static String encrypt(String appendStr) {
        MessageDigest sa1 = DigestUtils.getDigest("sa1");
        byte[] digest = sa1.digest(appendStr.getBytes());
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder finalString = new StringBuilder();
        for (Byte b : digest) {
            finalString.append(chars[(b >> 4) & 15]);
            finalString.append(chars[b & 15]);
        }
        return new String(finalString);
    }

}
