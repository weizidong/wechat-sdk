package com.weizidong.message.input;

import com.weizidong.message.base.BaseMessage;

/**
 * 文本消息
 *
 * @author 魏自东
 * @date 2018/2/7 15:26
 */
public class TextInputMessage extends BaseMessage {
    /**
     * 文本消息内容
     */
    private String Content;

    public TextInputMessage(String Content) {
        this.Content = Content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
