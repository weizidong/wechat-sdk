package com.weizidong.message.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 加密过后的消息接收
 *
 * @author 魏自东
 * @date 2018/4/2 20:24
 */
@XmlRootElement(name = "xml")
public class EncryptInputMessage {
    private String MsgSignature;
    private String Encrypt;
    private String TimeStamp;
    private String Nonce;

    public String getMsgSignature() {
        return MsgSignature;
    }

    @XmlElement(name = "MsgSignature")
    public void setMsgSignature(String msgSignature) {
        MsgSignature = msgSignature;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    @XmlElement(name = "Encrypt")
    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    @XmlElement(name = "TimeStamp")
    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getNonce() {
        return Nonce;
    }

    @XmlElement(name = "Nonce")
    public void setNonce(String nonce) {
        Nonce = nonce;
    }

    @Override
    public String toString() {
        return "EncryptInputMessage{" +
                "MsgSignature='" + MsgSignature + '\'' +
                ", Encrypt='" + Encrypt + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                ", Nonce='" + Nonce + '\'' +
                '}';
    }
}
