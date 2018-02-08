package com.weizidong.message.input;

import com.weizidong.message.base.BaseMessage;

/**
 * 链接消息
 *
 * @author 魏自东
 * @date 2018/2/8 17:08
 */
public class LinkInputMessage extends BaseMessage {

    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;

    public LinkInputMessage(String title, String description, String url) {
        Title = title;
        Description = description;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

}
