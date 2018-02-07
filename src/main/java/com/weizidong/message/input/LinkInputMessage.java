package com.weizidong.message.input;

import com.weizidong.base.MsgType;
import com.weizidong.message.input.base.BaseMessage;

/**
 * 链接消息
 * 
 * @author WeiZiDong
 *
 */
public class LinkInputMessage extends BaseMessage {

	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;

	@Override
	public String getMsgType() {
		return MsgType.LINK;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String Url) {
		this.Url = Url;
	}

}
