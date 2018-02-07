package com.weizidong.message.handler;

import com.weizidong.message.input.ImageInputMessage;
import com.weizidong.message.input.LinkInputMessage;
import com.weizidong.message.input.LocationInputMessage;
import com.weizidong.message.input.ShortVideoInputMessage;
import com.weizidong.message.input.TextInputMessage;
import com.weizidong.message.input.VideoInputMessage;
import com.weizidong.message.input.VoiceInputMessage;
import com.weizidong.message.input.base.BaseMessage;
import com.weizidong.message.output.TextOutputMessage;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 微信公众平台接受消息处理器
 *
 * 此消息处理器只负责接收消息和返回已收到消息的功能，无特殊功能。
 * 
 * @author WeiZiDong
 *
 */
public class DefaultNormalMessageHandler implements IMessageHandler {

	private OutputMessage allType(BaseMessage msg) {
		TextOutputMessage out = new TextOutputMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}

	@Override
	public OutputMessage doText(TextInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doImage(ImageInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doVoice(VoiceInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doVideo(VideoInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doShortvideo(ShortVideoInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doLocation(LocationInputMessage msg) {
		return allType(msg);
	}

	@Override
	public OutputMessage doLink(LinkInputMessage msg) {
		return allType(msg);
	}
}
