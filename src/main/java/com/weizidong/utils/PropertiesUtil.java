package com.weizidong.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取properties文件工具
 *
 * @author 魏自东
 * @date 2018/2/7 10:12
 */
public class PropertiesUtil {
    private static final Logger log = LogManager.getLogger(PropertiesUtil.class);
    private Properties props;
    private URI uri;

    public PropertiesUtil(String fileName) {
        readProperties("/" + fileName);
    }

    private void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStream fis = getClass().getResourceAsStream(fileName);
            props.load(fis);
            uri = this.getClass().getResource(fileName).toURI();
        } catch (Exception e) {
            log.error("配置文件【" + fileName + "】读取失败！");
            e.printStackTrace();
        }
    }

    /**
     * 获取某个属性
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取所有属性，返回一个map,不常用 可以试试props.putAll(t)
     */
    public Map<String, String> getAllProperty() {
        Map<String, String> map = new HashMap<>(16);
        Enumeration<?> enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 在控制台上打印出所有属性，调试时用。
     */
    public void printProperties() {
        props.list(System.out);
    }

    /**
     * 写入properties信息
     */
    public void writeProperties(String key, String value) {
        try {
            OutputStream fos = new FileOutputStream(new File(uri));
            props.setProperty(key, value);
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "『comments』Update key：" + key);
        } catch (Exception e) {
            log.error("配置文件【" + uri + "】写入 <" + key + ">" + value + " 失败！");
            e.printStackTrace();
        }
    }

}
