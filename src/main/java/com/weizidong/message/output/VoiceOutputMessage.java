package com.weizidong.message.output;

import com.weizidong.message.output.base.OutputMessage;
import com.weizidong.message.output.base.Voice;

/**
 * 回复语音消息
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class VoiceOutputMessage extends OutputMessage {

	/**
	 * 消息类型:语音消息
	 */
	private final String MsgType = "voice";
	/**
	 * 通过上传多媒体文件，得到的id封装的Voice对象
	 */
	private Voice Voice;

	/**
	 * 创建一个新的 Output Message.并且MsgType的值为voice.
	 */
	public VoiceOutputMessage() {
	}

	/**
	 * 创建一个自定义语音Id mediaId的Output Message.
	 *
	 * @param voice
	 *            语音资源Id
	 */
	public VoiceOutputMessage(Voice voice) {
		Voice = voice;
	}

	/**
	 * 获取 消息类型
	 *
	 * @return 消息类型
	 */
	@Override
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * 获取 通过上传多媒体文件，得到的id封装的Voice对象
	 *
	 * @return 通过上传多媒体文件，得到的id封装的Voice对象
	 */
	public Voice getVoice() {
		return Voice;
	}

	/**
	 * 设置 通过上传多媒体文件，得到的id封装的Voice对象
	 *
	 * @param voice
	 *            通过上传多媒体文件，得到的id封装的Voice对象
	 */
	public void setVoice(Voice voice) {
		Voice = voice;
	}

	@Override
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[" + this.MsgType + "]]></MsgType>");
		sb.append("<Voice>");
		sb.append("<MediaId><![CDATA[").append(this.getVoice().getMediaId()).append("]]></MediaId>");
		sb.append("</Voice>");
		sb.append("</xml>");
		return sb.toString();
	}
}