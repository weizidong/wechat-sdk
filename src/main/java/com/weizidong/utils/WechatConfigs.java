package com.weizidong.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

/**
 * 微信平台调用基础配置
 *
 * @author WeiZiDong
 */
public class WechatConfigs {
    private static Logger logger = LogManager.getLogger(WechatConfigs.class);
    private static Properties props;

    static {
        init();
    }

    static void init() {
        // 初始化默认配置
        props = new Properties();
        props.setProperty("wechat.debug", "true");
        props.setProperty("wechat.token", "wechat_sdk");
        // 读取自定义配置
        String pro = "configs/wechat-sdk.properties";
        boolean loaded = loadProperties(props, "." + File.separatorChar + pro)
                || loadProperties(props, WechatConfigs.class.getResourceAsStream("/WEB-INF/" + pro))
                || loadProperties(props, WechatConfigs.class.getClassLoader().getResourceAsStream(pro));
        if (!loaded) {
            logger.error("没有找到wechat-sdk.properties配置文件!");
        }
    }

    /**
     * 加载属性文件
     *
     * @param props 属性文件实例
     * @param path  属性文件路径
     * @return 是否加载成功
     */
    private static boolean loadProperties(Properties props, String path) {
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                props.load(new FileInputStream(file));
                return true;
            }
        } catch (IOException e) {
            // 忽略异常
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 加载属性文件
     *
     * @param props 属性文件实例
     * @param is    属性文件流
     * @return 是否加载成功
     */
    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            if (is != null) {
                props.load(is);
                return true;
            }
        } catch (IOException ignore) {
            // 异常忽略
            ignore.printStackTrace();
        }
        return false;
    }

    /**
     * 获取开发者第三方用户唯一凭证
     *
     * @return 第三方用户唯一凭证
     */
    public static String getOAuthAppId() {
        return getProperty("wechat.appid");
    }

    /**
     * 获取开发者第三方用户唯一凭证
     *
     * @param appid 默认第三方用户唯一凭证
     * @return 第三方用户唯一凭证
     */
    public static String getOAuthAppId(String appid) {
        return getProperty("wechat.appid", appid);
    }

    /**
     * 获取开发者第三方用户唯一凭证密钥
     *
     * @return 第三方用户唯一凭证密钥
     */
    public static String getOAuthSecret() {
        return getProperty("wechat.secret");
    }

    /**
     * 获取开发者第三方用户唯一凭证密钥
     *
     * @param secret 默认第三方用户唯一凭证密钥
     * @return 第三方用户唯一凭证密钥
     */
    public static String getOAuthSecret(String secret) {
        return getProperty("wechat.secret", secret);
    }


    /**
     * 获取 是否为调试模式
     *
     * @return 是否为调试模式
     */
    public static boolean isDebug() {
        return getBoolean("wechat.debug");
    }

    public static boolean getBoolean(String name) {
        String value = getProperty(name);
        return Boolean.valueOf(value);
    }

    public static int getIntProperty(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static int getIntProperty(String name, int fallbackValue) {
        String value = getProperty(name, String.valueOf(fallbackValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    /**
     * 获取属性值
     *
     * @param name 属性名称
     * @return 属性值
     */
    public static String getProperty(String name) {
        return getProperty(name, null);
    }

    /**
     * 获取属性值
     *
     * @param name          属性名
     * @param fallbackValue 默认返回值
     * @return 属性值
     */
    public static String getProperty(String name, String fallbackValue) {
        String value;
        try {
            // 从全局系统获取
            value = System.getProperty(name, null);
            if (null == value) {
                // 先从系统配置文件获取
                value = props.getProperty(name, fallbackValue);
            }
        } catch (AccessControlException ace) {
            value = fallbackValue;
        }
        return value;
    }
}
