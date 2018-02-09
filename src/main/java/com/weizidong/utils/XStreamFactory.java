package com.weizidong.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 将微信POST的流转换为XStream，然后转换为InputMessage对象
 *
 * @author 魏自东
 * @date 2018/2/9 17:10
 */
public class XStreamFactory {

    /**
     * 将输入流转读取成字符串
     *
     * @param in 输入流
     * @return 字符串
     */
    public static String inputStream2String(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }
}
