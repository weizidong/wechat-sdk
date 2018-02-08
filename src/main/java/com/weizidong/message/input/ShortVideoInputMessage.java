package com.weizidong.message.input;

import com.weizidong.message.base.BaseMessage;

/**
 * 小视频消息
 *
 * @author 魏自东
 * @date 2018/2/7 15:28
 */
public class ShortVideoInputMessage extends BaseMessage {
    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
    /**
     * 视频消息 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;

    public ShortVideoInputMessage(String mediaId, String thumbMediaId) {
        MediaId = mediaId;
        ThumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String MediaId) {
        this.MediaId = MediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String ThumbMediaId) {
        this.ThumbMediaId = ThumbMediaId;
    }
}
