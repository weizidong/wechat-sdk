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
 * 获取AccessToken
 *
 * @author 魏自东
 * @date 2018/2/7 10:44
 */
public class Token extends BaseResp {
    private static final Logger log = LogManager.getLogger(Token.class);
    /**
     * 获取access_token
     */
    private static final String TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    private static RedisUtil ru = RedisUtil.getRu();
    /**
     * 网页授权接口调用凭证
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expires_in;
    /**
     * 获取令牌时间
     */
    private Long timestamp;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
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
     * 获取AccessToken
     *
     * @return token
     * @author 魏自东
     * @date 2018/2/7 10:44
     */
    public static Token get() {
        String key = WechatConfigs.getProperty("wechat.token_cache_key");
        Token token = ru.get(key, Token.class);
        debug("缓存中的Token：" + token);
        // 超时时间提前5分钟，
        if (token != null && System.currentTimeMillis() - token.getTimestamp() < (token.getExpires_in() - 300) * 1000) {
            return token;
        }
        Long ts = System.currentTimeMillis();
        String appid = WechatConfigs.getOAuthAppId();
        String secret = WechatConfigs.getOAuthSecret();
        String api = MessageFormat.format(TOKEN_API, appid, secret);
        token = HttpClientUtil.doGet(api, Token.class);
        if (token.getAccess_token() == null) {
            log.error("获取Token失败：" + token.toError());
            throw new WeChatException(token.toError());
        }
        debug("微信获取的Token：" + token.toString());
        token.setTimestamp(ts);
        ru.setex(key, token, token.getExpires_in());
        return token;
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
