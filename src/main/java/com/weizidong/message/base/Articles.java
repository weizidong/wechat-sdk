package com.weizidong.message.base;

import java.io.Serializable;

/**
 * 图文消息
 *
 * @author 魏自东
 * @date 2018/2/9 14:15
 */
public class Articles implements Serializable {

    /**
     * 图文消息标题
     */
    private String Title;
    /**
     * 图文消息描述
     */
    private String Description;
    /**
     * 发送被动响应时设置的图片url 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转链接
     */
    private String Url;

    /**
     * @param title       图文消息标题
     * @param description 图文消息描述
     * @param picUrl      发送被动响应时设置的图片url 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * @param url         点击图文消息跳转链接
     */
    public Articles(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    /**
     * 获取 图文消息的标题
     *
     * @return 图文消息的标题
     */
    public String getTitle() {
        return Title;
    }

    /**
     * 设置 图文消息的标题
     *
     * @param Title 图文消息的标题
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * 获取 图文消息的描述
     *
     * @return 图文消息的描述
     */
    public String getDescription() {
        return Description;
    }

    /**
     * 设置 图文消息的描述
     *
     * @param Description 图文消息的描述
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * 获取 图片链接
     *
     * @return 图片链接
     */
    public String getPicUrl() {
        return PicUrl;
    }

    /**
     * 设置 图片链接
     *
     * @param PicUrl 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    /**
     * 获取 点击图文消息跳转链接
     *
     * @return 点击图文消息跳转链接
     */
    public String getUrl() {
        return Url;
    }

    /**
     * 设置 点击图文消息跳转链接
     *
     * @param Url 点击图文消息跳转链接
     */
    public void setUrl(String Url) {
        this.Url = Url;
    }

}
