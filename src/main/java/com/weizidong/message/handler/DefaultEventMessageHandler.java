package com.weizidong.message.handler;

import com.weizidong.message.event.ClickEventMessage;
import com.weizidong.message.event.LocationEventMessage;
import com.weizidong.message.event.LocationSelectEventMessage;
import com.weizidong.message.event.PicPhotoOrAlbumEventMessage;
import com.weizidong.message.event.PicSysPhotoEventMessage;
import com.weizidong.message.event.PicWeixinEventMessage;
import com.weizidong.message.event.QrsceneScanEventMessage;
import com.weizidong.message.event.QrsceneSubscribeEventMessage;
import com.weizidong.message.event.ScanCodePushEventMessage;
import com.weizidong.message.event.ScanCodeWaitMsgEventMessage;
import com.weizidong.message.event.SubscribeEventMessage;
import com.weizidong.message.event.UnSubscribeEventMessage;
import com.weizidong.message.event.ViewEventMessage;
import com.weizidong.message.event.base.EventMessage;
import com.weizidong.message.output.TextOutputMessage;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 微信公众平台接受事件处理器
 *
 * 此消息处理器只负责接收消息和返回已收到消息的功能，无特殊功能。
 * 
 * @author WeiZiDong
 *
 */
public class DefaultEventMessageHandler implements IEventHandler {

	private OutputMessage allType(EventMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage subscribe(SubscribeEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage unSubscribe(UnSubscribeEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage qrsceneScan(QrsceneScanEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage location(LocationEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage click(ClickEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage view(ViewEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage scanCodePush(ScanCodePushEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage picSysPhoto(PicSysPhotoEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage picWeixin(PicWeixinEventMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage locationSelect(LocationSelectEventMessage msg) {
		return allType(msg);
	}

}
