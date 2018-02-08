package com.weizidong.message.base;

/**
 * 微信消息的基类
 *
 * @author 魏自东
 * @date 2018/2/8 10:17
 */
public abstract class EventMessage extends BaseMessage {
    /**
     * 事件类型
     */
    private String Event;
    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id<br/>
     * 事件KEY值，与自定义菜单接口中KEY值对应<br/>
     * 事件KEY值，设置的跳转URL
     */
    private String EventKey;

    public void setEvent(String event) {
        Event = event;
    }

    public String getEvent() {
        return Event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
