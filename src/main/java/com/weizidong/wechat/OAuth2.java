package com.weizidong.wechat;

import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import com.weizidong.utils.HttpUtil;
import com.weizidong.utils.WechatConfigs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * 微信网页授权
 *
 * @author 魏自东
 * @date 2018/2/7 10:15
 */
public class OAuth2 extends BaseResp {
    private static Logger logger = LogManager.getLogger(OAuth2.class);
    /**
     * 授权地址
     */
    private static final String AUTHORIZE_API = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";
    /**
     * 通过code换取网页授权access_token
     */
    private static final String ACCESS_TOKEN_API = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    private static final String USERINFO_API = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
    /**
     * openid
     */
    private String openid;
    /**
     * accessToken
     */
    private String accessToken;
    /**
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
     */
    public static final String SNSAPI_BASE = "snsapi_base";
    /**
     * snsapi_userinfo（弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    public static final String SNSAPI_USERINFO = "snsapi_userinfo";

    /**
     * 获取授权重定向路径
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @param scope       应用授权作用域，
     *                    snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *                    snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @param state       重定向后会带上state参数，可以填写a-zA-Z0-9的参数值，最多128字节
     */
    public static String authorize(String redirectUri, String scope, String state) {
        String appid = WechatConfigs.getOAuthAppId();
        String api = MessageFormat.format(AUTHORIZE_API, appid, HttpUtil.encode(redirectUri), scope, state);
        if (WechatConfigs.isDebug()) {
            logger.debug("获取授权重定向路径：" + api);
        }
        return api;
    }

    /**
     * 获取授权重定向路径,state默认为当前时间戳
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @param scope       应用授权作用域，
     *                    snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *                    snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    public static String authorize(String redirectUri, String scope) {
        String appid = WechatConfigs.getOAuthAppId();
        String api = MessageFormat.format(AUTHORIZE_API, appid, HttpUtil.encode(redirectUri), scope, Long.toString(System.currentTimeMillis()));
        if (WechatConfigs.isDebug()) {
            logger.debug("获取授权重定向路径：" + api);
        }
        return api;
    }

    /**
     * 通过code换取网页授权access_token
     *
     * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     */
    public void getAccessToken(String code) {
        String appid = WechatConfigs.getOAuthAppId();
        String secret = WechatConfigs.getOAuthSecret();
        String api = MessageFormat.format(ACCESS_TOKEN_API, appid, secret, code);
        JSONObject resp = HttpClientUtil.doGet(api, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger("errcode") + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger("errcode"));
            logger.error("通过code换取网页授权access_token失败：" + err);
            throw new WeChatException(err);
        }
        this.openid = resp.getString("openid");
        this.accessToken = resp.getString("access_token");
        if (WechatConfigs.isDebug()) {
            logger.debug("通过code换取网页授权access_token：" + this.toString());
        }
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    public WechatUser getUserInfo() {
        String api = MessageFormat.format(USERINFO_API, accessToken, openid);
        WechatUser resp = HttpClientUtil.doGet(api, WechatUser.class);
        if (resp.getErrcode() != null && resp.getErrcode() != 0) {
            logger.error("拉取用户信息失败：" + resp.toError());
            throw new WeChatException(resp.toError());
        }
        if (WechatConfigs.isDebug()) {
            logger.debug("拉取用户信息：" + resp.toString());
        }
        return resp;
    }

    @Override
    public String toString() {
        return "{\"openid\":\"" + openid + "\",\"accessToken\":\"" + accessToken + "\"}";
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
