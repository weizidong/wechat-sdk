package com.weizidong.message.output;

import com.weizidong.message.base.Video;

/**
 * 回复视频消息
 *
 * @author 魏自东
 * @date 2018/2/9 14:07
 */
@SuppressWarnings("serial")
public class VideoOutputMessage extends OutputMessage {

    /**
     * 消息类型:视频消息
     */
    private final String MsgType = "video";
    /**
     * 通过上传多媒体文件，得到的id
     */
    private Video Video;

    /**
     * 创建一个视频 Output Message.
     * <p>
     * 并且MsgType的值为video.
     */
    public VideoOutputMessage() {
    }

    /**
     * 创建一个视频 Output Message.
     * <p>
     * 并且MsgType的值为video.
     *
     * @param mediaId 通过上传多媒体文件，得到的id
     */
    public VideoOutputMessage(String mediaId) {
        Video = new Video(mediaId);
    }

    /**
     * 创建一个视频 Output Message.
     * <p>
     * 并且MsgType的值为video.
     *
     * @param mediaId 通过上传多媒体文件，得到的id
     * @param title   视频消息的标题
     */
    public VideoOutputMessage(String mediaId, String title) {
        Video = new Video(mediaId, title);
    }

    /**
     * 创建一个视频 Output Message.
     * <p>
     * 并且MsgType的值为video.
     *
     * @param mediaId     通过上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     */
    public VideoOutputMessage(String mediaId, String title, String description) {
        Video = new Video(mediaId, title, description);
    }

    /**
     * 创建一个视频 Output Message.
     * <p>
     * 并且MsgType的值为video.
     *
     * @param video 视频
     */
    public VideoOutputMessage(Video video) {
        Video = video;
    }

    /**
     * 获取 消息类型
     *
     * @return 消息类型
     */
    @Override
    public String getMsgType() {
        return MsgType;
    }

    /**
     * 获取 通过上传多媒体文件，得到的id
     *
     * @return 通过上传多媒体文件，得到的id
     */
    public Video getVideo() {
        return Video;
    }

    /**
     * 设置 通过上传多媒体文件，得到的id
     *
     * @param video 通过上传多媒体文件，得到的id
     */
    public void setVideo(Video video) {
        Video = video;
    }

    @Override
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.MsgType + "]]></MsgType>");
        sb.append("<Video>");
        sb.append("<MediaId><![CDATA[").append(this.getVideo().getMediaId()).append("]]></MediaId>");
        sb.append("<Title><![CDATA[").append(this.getVideo().getTitle()).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.getVideo().getDescription()).append("]]></Description>");
        sb.append("</Video>");
        sb.append("</xml>");
        return sb.toString();
    }
}
