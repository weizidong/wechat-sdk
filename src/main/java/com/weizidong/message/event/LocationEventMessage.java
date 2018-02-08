package com.weizidong.message.event;

import com.weizidong.message.base.EventMessage;

/**
 * 上报地理位置事件
 *
 * @author 魏自东
 * @date 2018/2/8 18:02
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

    public LocationEventMessage(String latitude, String longitude, String precision) {
        Latitude = latitude;
        Longitude = longitude;
        Precision = precision;
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
