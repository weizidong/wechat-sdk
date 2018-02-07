package com.weizidong.message.output;

import com.weizidong.message.output.base.Image;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 回复图片消息
 *
 * @author 魏自东
 * @date 2018/2/7 15:29
 */
public class ImageOutputMessage extends OutputMessage {

    /**
     * 消息类型:图片消息
     */
    private final String MsgType = "image";
    /**
     * 通过上传多媒体文件，得到的id
     */
    private Image Image;

    /**
     * 创建一个图片 Output Message.
     * <p>
     * 并且MsgType的值为image.
     */
    public ImageOutputMessage() {
    }

    /**
     * 创建一个图片 的Output Message.
     * <p>
     * 并且MsgType的值为image.
     *
     * @param image 图片
     */
    public ImageOutputMessage(Image image) {
        this.Image = image;
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
     * @return 通过上传多媒体文件，得到的id封装的image对象
     */
    public Image getImage() {
        return this.Image;
    }

    /**
     * 设置 通过上传多媒体文件，得到的id
     *
     * @param image 通过上传多媒体文件，得到的id封装的image对象
     */
    public void setImage(Image image) {
        this.Image = image;
    }

    @Override
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.MsgType + "]]></MsgType>");
        sb.append("<Image>");
        sb.append("<MediaId><![CDATA[").append(this.getImage().getMediaId()).append("]]></MediaId>");
        sb.append("</Image>");
        sb.append("</xml>");
        return sb.toString();
    }
}
