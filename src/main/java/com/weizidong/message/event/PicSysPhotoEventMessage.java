package com.weizidong.message.event;

import com.weizidong.message.base.EventMessage;
import com.weizidong.message.base.SendPicsInfo;

/**
 * 自定义菜单事件
 * <p>
 * 扫码推事件的事件推送
 *
 * @author 魏自东
 * @date 2018/2/8 18:06
 */
public class PicSysPhotoEventMessage extends EventMessage {
    /**
     * 发送的图片信息
     */
    private SendPicsInfo SendPicsInfo;

    public PicSysPhotoEventMessage(SendPicsInfo sendPicsInfo) {
        SendPicsInfo = sendPicsInfo;
    }

    public SendPicsInfo getSendPicsInfo() {
        return SendPicsInfo;
    }

    public void setSendPicsInfo(SendPicsInfo SendPicsInfo) {
        this.SendPicsInfo = SendPicsInfo;
    }

}
