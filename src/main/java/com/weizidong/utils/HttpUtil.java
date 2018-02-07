package com.weizidong.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * http工具类
 *
 * @author 魏自东
 * @date 2018/2/7 10:57
 */
public class HttpUtil {

    /**
     * url解码
     *
     * @param url 要解码的url
     * @return 解码后的url
     */
    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("url解码失败", e);
        }
    }

    /**
     * url编码
     *
     * @param url 要编码的url
     * @return 编码后的url
     */
    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("url编码失败", e);
        }
    }
}
