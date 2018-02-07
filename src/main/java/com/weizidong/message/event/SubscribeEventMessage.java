package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;

/**
 * 关注事件
 * 
 * @author WeiZiDong
 *
 */
public class SubscribeEventMessage extends EventMessage {

	@Override
	public String getEvent() {
		return MsgType.Event.SUBSCRIBE;
	}

}
