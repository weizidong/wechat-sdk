package com.weizidong.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * 微信Xml转换工具类
 *
 * @author 魏自东
 * @date 2018/2/9 17:11
 */
public class WeChatXmlUtil {
    /**
     * 将xml转换成实体类
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        T bean;
        try {
            bean = clazz.newInstance();
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                Field f = clazz.getDeclaredField(e.getName());
                f.setAccessible(true);
                String type = f.getType().toString();
                if (type.endsWith("Date")) {
                    f.set(bean, Date.parse(e.getText()));
                } else if (type.endsWith("int") || type.endsWith("Integer")) {
                    f.set(bean, Integer.parseInt(e.getText()));
                } else if (type.endsWith("long") || type.endsWith("Long")) {
                    f.set(bean, Long.parseLong(e.getText()));
                } else if (type.endsWith("double") || type.endsWith("Double")) {
                    f.set(bean, Double.parseDouble(e.getText()));
                } else {
                    f.set(bean, e.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析xml成实体[" + clazz + "]错误!", e);
        }
        return bean;
    }

}
