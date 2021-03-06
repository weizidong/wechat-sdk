package com.weizidong.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 数字签名工具类
 *
 * @author 魏自东
 * @date 2018/2/9 17:13
 */
public class SignatureUtil {

    /**
     * 数字签名是否合法
     *
     * @param token     访问令牌
     * @param signature 数字签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     */
    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)) {
            return false;
        }
        // 这里的token是个人端登录或注册用户时生成，在需要验证登录的接口中传入此token
        String[] tmpArr = {token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(tmpArr);
        // 将三个参数字符串拼接成一个字符串
        String tmpStr = arrayToString(tmpArr);
        // 进行sha1加密
        tmpStr = sha1Encode(tmpStr);
        return StringUtils.equalsIgnoreCase(tmpStr, signature);
    }

    /**
     * 生成数字签名
     *
     * @param token     访问令牌
     * @param timestamp 时间戳
     * @param nonce     随机数
     */
    public static String generateSignature(String token, String timestamp, String nonce) {
        // 这里的token是个人端登录或注册用户时生成，在需要验证登录的接口中传入此token
        String[] tmpArr = {token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(tmpArr);
        // 将三个参数字符串拼接成一个字符串
        String tmpStr = arrayToString(tmpArr);
        tmpStr = sha1Encode(tmpStr);
        return tmpStr;
    }

    /**
     * 数组转字符串
     */
    public static String arrayToString(String[] arr) {
        StringBuilder bf = new StringBuilder();
        for (String str : arr) {
            bf.append(str);
        }
        return bf.toString();
    }

    /**
     * 进行sha1加密
     */
    public static String sha1Encode(String sourceString) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(sourceString.getBytes()));
        } catch (Exception ignored) {
        }
        return resultString;
    }

    public static String byte2hexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            if (((int) aByte & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) aByte & 0xff, 16));
        }
        return buf.toString().toLowerCase();
    }

}
