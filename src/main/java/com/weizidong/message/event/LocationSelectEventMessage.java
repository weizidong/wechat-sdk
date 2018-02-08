package com.weizidong.message.event;

import com.weizidong.message.base.EventMessage;
import com.weizidong.message.base.SendLocationInfo;

/**
 * 自定义菜单事件
 * <p>
 * 弹出地理位置选择器的事件推送
 *
 * @author 魏自东
 * @date 2018/2/8 18:08
 */
public class LocationSelectEventMessage extends EventMessage {
    /**
     * 发送的位置信息
     */
    private SendLocationInfo SendLocationInfo;

    public LocationSelectEventMessage(SendLocationInfo sendLocationInfo) {
        SendLocationInfo = sendLocationInfo;
    }

    public SendLocationInfo getSendLocationInfo() {
        return SendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo SendLocationInfo) {
        this.SendLocationInfo = SendLocationInfo;
    }

}
