package com.weizidong.message.base;

/**
 * 回复音乐消息中的音乐对象
 *
 * @author 魏自东
 * @date 2018/2/9 14:12
 */
@SuppressWarnings("serial")
public class Music implements java.io.Serializable {
    /**
     * 音乐标题
     */
    private String Title;
    /**
     * 音乐描述
     */
    private String Description;
    /**
     * 音乐链接
     */
    private String MusicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;
    /**
     * 缩略图的媒体id，通过上传多媒体文件，得到的id
     */
    private String ThumbMediaId;

    public Music(String title, String description, String musicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicUrl;
        ThumbMediaId = thumbMediaId;
    }

    public Music(String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicUrl;
        this.HQMusicUrl = hqMusicUrl;
        ThumbMediaId = thumbMediaId;
    }

    public Music(String title, String musicUrl, String thumbMediaId) {
        Title = title;
        MusicUrl = musicUrl;
        ThumbMediaId = thumbMediaId;
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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String MusicUrl) {
        this.MusicUrl = MusicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String ThumbMediaId) {
        this.ThumbMediaId = ThumbMediaId;
    }
}
