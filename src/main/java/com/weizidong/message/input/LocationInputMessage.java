package com.weizidong.message.input;

import com.weizidong.base.MsgType;
import com.weizidong.message.input.base.BaseMessage;

/**
 * 地理位置消息
 * 
 * @author WeiZiDong
 *
 */
public class LocationInputMessage extends BaseMessage {
	/**
	 * 地理位置维度
	 */
	private String Location_X;
	/**
	 * 地理位置经度
	 */
	private String Location_Y;
	/**
	 * 地图缩放大小
	 */
	private Long Scale;
	/**
	 * 地理位置信息
	 */
	private String Label;

	@Override
	public String getMsgType() {
		return MsgType.LOCATION;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String Location_X) {
		this.Location_X = Location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String Location_Y) {
		this.Location_Y = Location_Y;
	}

	public Long getScale() {
		return Scale;
	}

	public void setScale(Long Scale) {
		this.Scale = Scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String Label) {
		this.Label = Label;
	}

}
