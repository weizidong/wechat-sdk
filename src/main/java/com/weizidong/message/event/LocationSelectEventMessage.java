package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;
import com.weizidong.message.event.base.SendLocationInfo;

/**
 * 自定义菜单事件
 *
 * 弹出地理位置选择器的事件推送
 * 
 * @author WeiZiDong
 *
 */
public class LocationSelectEventMessage extends EventMessage {
	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;
	/**
	 * 发送的位置信息
	 */
	private SendLocationInfo SendLocationInfo;

	@Override
	public String getEvent() {
		return MsgType.Event.LOCATION_SELECT;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}

	public SendLocationInfo getSendLocationInfo() {
		return SendLocationInfo;
	}

	public void setSendLocationInfo(SendLocationInfo SendLocationInfo) {
		this.SendLocationInfo = SendLocationInfo;
	}

}
