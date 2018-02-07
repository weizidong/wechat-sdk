package com.weizidong.wechat;

import com.weizidong.base.BaseResp;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import com.weizidong.utils.RedisUtil;
import com.weizidong.utils.WechatConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * 微信jsapi_ticket
 *
 * @author 魏自东
 * @date 2018/2/7 14:48
 */
public class Ticket extends BaseResp {
    private static final Logger log = LogManager.getLogger(Ticket.class);
    /**
     * 获取jsapi_ticket
     */
    private static final String TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
    private static RedisUtil ru = RedisUtil.getRu();
    /**
     * 调用微信JS接口的临时票据
     */
    private String ticket;
    /**
     * 有效期为7200秒
     */
    private Integer expires_in;
    /**
     * 获取令牌时间
     */
    private Long timestamp;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取jsapi_ticket
     *
     * @return Ticket
     * @author 魏自东
     * @date 2018/2/7 10:44
     */
    public static Ticket get() {
        String key = WechatConfigs.getProperty("wechat.ticket_cache_key");
        Ticket ticket = ru.get(key, Ticket.class);
        debug("缓存中的Ticket：" + ticket);
        // 超时时间提前5分钟，
        if (ticket != null && System.currentTimeMillis() - ticket.getTimestamp() < (ticket.getExpires_in() - 300) * 1000) {
            return ticket;
        }
        Token t = Token.get();
        String api = MessageFormat.format(TICKET_API, t.getAccess_token());
        ticket = HttpClientUtil.doGet(api, Ticket.class);
        if (ticket.getTicket() == null) {
            log.error("获取Ticket失败：" + ticket.toError());
            throw new WeChatException(ticket.toError());
        }
        debug("微信获取的Ticket：" + ticket.toString());
        ticket.setTimestamp(System.currentTimeMillis());
        ru.setex(key, ticket, ticket.getExpires_in());
        return ticket;
    }

    /**
     * debug输出
     *
     * @param msg msg
     * @author 魏自东
     * @date 2018/2/7 10:46
     */
    private static void debug(String msg) {
        if (WechatConfigs.isDebug()) {
            log.debug(msg);
        }
    }
}
