package com.weizidong.message.base;

import java.io.Serializable;

/**
 * 回复视频消息中的视频对象
 *
 * @author 魏自东
 * @date 2018/2/9 14:07
 */
@SuppressWarnings("serial")
public class Video implements Serializable {
    /**
     * 通过上传多媒体文件，得到的id
     */
    private String MediaId;
    /**
     * 视频消息的标题
     */
    private String Title;
    /**
     * 视频消息的描述
     */
    private String Description;

    public Video(String mediaId) {
        MediaId = mediaId;
    }

    public Video(String mediaId, String title) {
        MediaId = mediaId;
        Title = title;
    }

    public Video(String mediaId, String title, String description) {
        MediaId = mediaId;
        Title = title;
        Description = description;
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

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
}
