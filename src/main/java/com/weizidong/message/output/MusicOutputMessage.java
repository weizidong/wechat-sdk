package com.weizidong.message.output;

import com.weizidong.message.base.Music;

/**
 * 回复音乐消息
 *
 * @author 魏自东
 * @date 2018/2/9 14:11
 */
@SuppressWarnings("serial")
public class MusicOutputMessage extends OutputMessage {

    /**
     * 消息类型:音乐消息
     */
    private final String MsgType = "music";
    /**
     * 音乐消息对象
     */
    private Music Music;

    @Override
    public String getMsgType() {
        return MsgType;
    }

    public MusicOutputMessage(Music music) {
        Music = music;
    }

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music Music) {
        this.Music = Music;
    }

    @Override
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.MsgType + "]]></MsgType>");
        sb.append("<Music>");
        sb.append("<Title><![CDATA[").append(this.getMusic().getTitle()).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.getMusic().getDescription()).append("]]></Description>");
        sb.append("<MusicUrl><![CDATA[").append(this.getMusic().getMusicUrl()).append("]]></MusicUrl>");
        sb.append("<HQMusicUrl><![CDATA[").append(this.getMusic().getHQMusicUrl()).append("]]></HQMusicUrl>");
        sb.append("<ThumbMediaId><![CDATA[").append(this.getMusic().getThumbMediaId()).append("]]></ThumbMediaId>");
        sb.append("</Music>");
        sb.append("</xml>");
        return sb.toString();
    }
}
