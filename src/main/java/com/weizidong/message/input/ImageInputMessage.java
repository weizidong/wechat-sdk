package com.weizidong.message.input;

import com.weizidong.message.base.BaseMessage;

/**
 * 图片消息
 *
 * @author 魏自东
 * @date 2018/2/8 17:02
 */
public class ImageInputMessage extends BaseMessage {

    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    public ImageInputMessage(String picUrl, String mediaId) {
        PicUrl = picUrl;
        MediaId = mediaId;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String MediaId) {
        this.MediaId = MediaId;
    }

}
