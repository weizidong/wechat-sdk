package com.weizidong.message.input;

import com.weizidong.base.MsgType;
import com.weizidong.message.input.base.BaseMessage;

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

    @Override
    public String getMsgType() {
        return MsgType.SHORTVIDEO;
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
