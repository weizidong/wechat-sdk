package com.weizidong.wechat;

import com.weizidong.utils.WechatConfigs;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * 多媒体接口
 *
 * @author 魏自东
 * @date 2018/3/6 14:05
 */
public class Media {
    private static final Logger log = LogManager.getLogger(Media.class);
    public static String BASE_PATH = "";
    public static final String RESOURCE_URL = "/wechat_media/";

    static {
        String basePath = System.getProperty("jetty.home");
        if (basePath == null) {
            BASE_PATH = System.getProperty("user.dir") + "/src/main/webapp";
        } else {
            BASE_PATH = basePath + "/webapps";
        }
    }

    /**
     * 授权地址
     */
    private static final String GET_API = "http://api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";

    /**
     * 获取临时素材
     *
     * @param mediaId 媒体文件ID
     * @return 保存路径
     */
    public static String get(String mediaId) {
        Token t = Token.get();
        String api = MessageFormat.format(GET_API, t.getAccess_token(), mediaId);
        debug("请求下载：\t" + api);
        try {
            URL url = new URL(api);
            URLConnection conn = url.openConnection();
            String fileName = conn.getHeaderField("Content-disposition");
            fileName = fileName.substring(fileName.indexOf("\"") + 1, fileName.length() - 1);
            debug("文件名：\t" + fileName);
            File file = new File(BASE_PATH + RESOURCE_URL, fileName);
            InputStream input = conn.getInputStream();
            try {
                FileOutputStream output = FileUtils.openOutputStream(file);
                try {
                    IOUtils.copy(input, output);
                } finally {
                    IOUtils.closeQuietly(output);
                }
            } finally {
                IOUtils.closeQuietly(input);
            }
            debug("文件路径：\t" + RESOURCE_URL + fileName);
            return RESOURCE_URL + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * debug输出
     *
     * @param msg msg
     * @author 魏自东
     * @date 2018/2/7 10:46
     */
    private static void debug(String msg) {
        if (WechatConfigs.isDebug()) {
            log.debug(msg);
        }
    }
}
