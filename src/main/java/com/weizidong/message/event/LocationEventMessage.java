package com.weizidong.message.event;

import com.weizidong.base.MsgType;
import com.weizidong.message.event.base.EventMessage;

/**
 * 上报地理位置事件
 * 
 * @author WeiZiDong
 *
 */
public class LocationEventMessage extends EventMessage {
	/**
	 * 上报地理位置事件
	 */
	private String Latitude;
	/**
	 * 地理位置经度
	 */
	private String Longitude;
	/**
	 * 地理位置精度
	 */
	private String Precision;

	@Override
	public String getEvent() {
		return MsgType.Event.LOCATION;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String Latitude) {
		this.Latitude = Latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String Longitude) {
		this.Longitude = Longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String Precision) {
		this.Precision = Precision;
	}

}
