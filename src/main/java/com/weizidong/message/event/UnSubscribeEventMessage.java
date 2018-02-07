package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;

/**
 * 取消关注事件
 * 
 * @author WeiZiDong
 *
 */
public class UnSubscribeEventMessage extends EventMessage {

	@Override
	public String getEvent() {
		return MsgType.Event.UNSUBSCRIBE;
	}

}
