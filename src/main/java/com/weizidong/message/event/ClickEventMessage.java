package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;

/**
 * 自定义菜单事件
 * 
 * @author WeiZiDong
 *
 */
public class ClickEventMessage extends EventMessage {
	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;

	@Override
	public String getEvent() {
		return MsgType.Event.CLICK;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}

}
