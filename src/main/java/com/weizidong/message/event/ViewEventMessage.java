package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;

/**
 * 自定义菜单事件
 *
 * 点击菜单跳转链接时的事件推送
 * 
 * @author WeiZiDong
 *
 */
public class ViewEventMessage extends EventMessage {
	/**
	 * 事件KEY值，设置的跳转URL
	 */
	private String EventKey;
	private String MenuId;

	@Override
	public String getEvent() {
		return MsgType.Event.VIEW;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}

	public String getMenuId() {
		return MenuId;
	}

	public void setMenuId(String MenuId) {
		this.MenuId = MenuId;
	}

}
