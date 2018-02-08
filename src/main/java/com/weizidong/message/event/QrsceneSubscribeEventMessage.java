package com.weizidong.message.event;

import com.weizidong.message.base.EventMessage;

/**
 * 扫描带参数二维码事件
 * <p>
 * 用户未关注
 *
 * @author 魏自东
 * @date 2018/2/8 18:01
 */
public class QrsceneSubscribeEventMessage extends EventMessage {
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

    public QrsceneSubscribeEventMessage(String ticket) {
        Ticket = ticket;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String Ticket) {
        this.Ticket = Ticket;
    }

}
