package com.weizidong.message.base;

import java.io.Serializable;

/**
 * 回复图片消息中的图片对象
 *
 * @author 魏自东
 * @date 2018/2/9 14:03
 */
@SuppressWarnings("serial")
public class Image implements Serializable {
    /**
     * 通过上传多媒体文件，得到的id
     */
    private String MediaId;

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    /**
     * 获取 通过上传多媒体文件，得到的id
     *
     * @return 通过上传多媒体文件，得到的id
     */
    public String getMediaId() {
        return MediaId;
    }

    /**
     * 设置 通过上传多媒体文件，得到的id
     *
     * @param mediaId 通过上传多媒体文件，得到的id
     */
    public void setMediaId(String mediaId) {
        this.MediaId = mediaId;
    }
}
