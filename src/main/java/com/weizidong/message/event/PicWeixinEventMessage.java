package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;
import com.weizidong.message.event.base.SendPicsInfo;

/**
 * 自定义菜单事件
 *
 * 弹出微信相册发图器的事件推送
 * 
 * @author WeiZiDong
 *
 */
public class PicWeixinEventMessage extends EventMessage {
	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;
	/**
	 * 发送的图片信息
	 */
	private SendPicsInfo SendPicsInfo;

	@Override
	public String getEvent() {
		return MsgType.Event.PIC_WEIXIN;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}

	public SendPicsInfo getSendPicsInfo() {
		return SendPicsInfo;
	}

	public void setSendPicsInfo(SendPicsInfo SendPicsInfo) {
		this.SendPicsInfo = SendPicsInfo;
	}

}
