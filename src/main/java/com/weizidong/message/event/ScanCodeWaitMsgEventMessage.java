package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;
import com.weizidong.message.event.base.ScanCodeInfo;

/**
 * 自定义菜单事件
 *
 * 扫码推事件且弹出“消息接收中”提示框的事件推送
 * 
 * @author WeiZiDong
 *
 */
public class ScanCodeWaitMsgEventMessage extends EventMessage {
	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;
	/**
	 * 扫描信息
	 */
	private ScanCodeInfo ScanCodeInfo;

	@Override
	public String getEvent() {
		return MsgType.Event.SCANCODE_WAITMSG;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo ScanCodeInfo) {
		this.ScanCodeInfo = ScanCodeInfo;
	}

}
