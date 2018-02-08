package com.weizidong.message.base;

import javax.xml.bind.annotation.XmlElement;

/**
 * 微信消息的基类
 *
 * @author 魏自东
 * @date 2018/2/8 10:17
 */
public abstract class BaseMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;
    /**
     * 消息id，64位整型
     */
    private Long MsgId;

    public String getMsgType() {
        return MsgType;
    }

    @XmlElement(name = "MsgType")
    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

    public String getToUserName() {
        return ToUserName;
    }

    @XmlElement(name = "ToUserName")
    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    @XmlElement(name = "FromUserName")
    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    @XmlElement(name = "CreateTime")
    public void setCreateTime(Long CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Long getMsgId() {
        return MsgId;
    }

    @XmlElement(name = "MsgId")
    public void setMsgId(Long MsgId) {
        this.MsgId = MsgId;
    }
}
