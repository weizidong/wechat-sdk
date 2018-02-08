package com.weizidong.message.output;

/**
 * 返回默认消息
 *
 * @author 魏自东
 * @date 2018/2/7 15:29
 */
public class DefaultOutputMessage extends OutputMessage {

    @Override
    public String getMsgType() {
        return null;
    }

    @Override
    public String toXML() {
        return "";
    }

}
