package com.weizidong.message.input;

import com.weizidong.message.base.BaseMessage;

/**
 * 语音消息
 *
 * @author 魏自东
 * @date 2018/2/8 17:03
 */
public class VoiceInputMessage extends BaseMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
    /**
     * 语音识别结果，使用UTF8编码
     */
    private String Recognition;

    public VoiceInputMessage(String mediaId, String format, String recognition) {
        MediaId = mediaId;
        Format = format;
        Recognition = recognition;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String MediaId) {
        this.MediaId = MediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String Format) {
        this.Format = Format;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String Recognition) {
        this.Recognition = Recognition;
    }

}
