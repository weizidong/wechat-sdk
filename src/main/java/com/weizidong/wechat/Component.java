package com.weizidong.wechat;

import com.alibaba.fastjson.JSONObject;
import com.weizidong.aes.AesException;
import com.weizidong.aes.WXBizMsgCrypt;
import com.weizidong.utils.HttpClientUtil;
import com.weizidong.utils.RedisUtil;
import com.weizidong.utils.WechatConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.MessageFormat;

/**
 * 第三方平台
 *
 * @author 魏自东
 * @date 2018/4/2 18:31
 */
public class Component {
    private static final Logger log = LogManager.getLogger(Component.class);
    private static RedisUtil ru = RedisUtil.getRu();
    private static WXBizMsgCrypt pc;

    static {
        try {
            pc = new WXBizMsgCrypt(WechatConfigs.getProperty("component.Token"), WechatConfigs.getProperty("component.Key"), WechatConfigs.getProperty("component.AppID"));
        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取access_token
     */
    private static final String TOKEN_API = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    private static final String COMPONENT_VERIFY_TICKET_KEY = "COMPONENT_VERIFY_TICKET_KEY";
    private static final String COMPONENT_TOKEN_KEY = "COMPONENT_TOKEN_KEY";

    /**
     * 推送component_verify_ticket协议
     * 在第三方平台创建审核通过后，
     * 微信服务器会向其“授权事件接收URL”每隔10分钟定时推送component_verify_ticket。
     * 第三方平台方在收到ticket推送后也需进行解密（详细请见【消息加解密接入指引】），
     * 接收到后必须直接返回字符串success。
     *
     * @param xml          接收到的加密xml
     * @param nonce        随机字符串
     * @param msgSignature 数据指纹
     * @param timestamp    时间戳
     * @return success
     */
    public static String verifyTicket(String xml, String msgSignature, String timestamp, String nonce) throws AesException, DocumentException {
        // 解密
        log.debug("-------------------收到推送component_verify_ticket协议");
        log.debug(msgSignature);
        log.debug(timestamp);
        log.debug(nonce);
        log.debug(xml);
        String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
        log.debug(result2);
        // 序列化
        Document document = DocumentHelper.parseText(result2);
        Element root = document.getRootElement();
        String componentVerifyTicket = root.element("ComponentVerifyTicket").getText();
        ru.set(COMPONENT_VERIFY_TICKET_KEY, componentVerifyTicket);
        return "success";
    }

    private static final String TIME_KEY = "time";
    private static final String TOKEN_KEY = "token";
    /**
     * 1小时40分钟
     */
    private static final Long MAX_TIME = 6000000L;

    /**
     * 获取第三方平台component_access_token
     * 第三方平台component_access_token是第三方平台的下文中接口的调用凭据，也叫做令牌（component_access_token）。
     * 每个令牌是存在有效期（2小时）的，且令牌的调用不是无限制的，请第三方平台做好令牌的管理，在令牌快过期时（比如1小时50分）再进行刷新。
     *
     * @return 第三方平台component_access_token
     */
    public static String getComponentToken() {
        // 缓存中获取
        JSONObject token = ru.get(COMPONENT_TOKEN_KEY, JSONObject.class);
        if (token != null && token.getLong(TIME_KEY) != null && token.getLong(TIME_KEY) + MAX_TIME > System.currentTimeMillis()) {
            return token.getString(TOKEN_KEY);
        }
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID"))
                .fluentPut("component_appsecret", WechatConfigs.getProperty("component.AppSecret"))
                .fluentPut("component_verify_ticket", ru.get(COMPONENT_VERIFY_TICKET_KEY, String.class));
        JSONObject resp = HttpClientUtil.doPostJson(TOKEN_API, param, JSONObject.class);
        // 获取到token
        String componentAccessToken = resp.getString("component_access_token");
        // 缓存到redis
        token = new JSONObject();
        token.put(TOKEN_KEY, componentAccessToken);
        token.put(TIME_KEY, System.currentTimeMillis());
        ru.set(COMPONENT_TOKEN_KEY, token);
        return componentAccessToken;
    }

    private static final String CREATE_PREAUTHCODE_API = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token={0}";

    /**
     * 获取预授权码pre_auth_code
     * 该API用于获取预授权码。预授权码用于公众号或小程序授权时的第三方平台方安全验证。
     *
     * @return 预授权码pre_auth_code
     */
    public static String getPreAuthCode() {
        // 获取第三方平台component_access_token
        String componentAccessToken = getComponentToken();
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID"));
        JSONObject resp = HttpClientUtil.doPostJson(MessageFormat.format(CREATE_PREAUTHCODE_API, componentAccessToken), param, JSONObject.class);
        // 获取到预授权码pre_auth_code
        return resp.getString("pre_auth_code");
    }

    private static final String QUERY_AUTH_API = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token={0}";
    private static final String AUTHORIZER_KEY = "AUTHORIZER_KEY";

    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
     * 该API用于使用授权码换取授权公众号或小程序的授权信息，
     * 并换取authorizer_access_token和authorizer_refresh_token。
     * 授权码的获取，需要在用户在第三方平台授权页中完成授权流程后，
     * 在回调URI中通过URL参数提供给第三方平台方。
     * 请注意，由于现在公众号或小程序可以自定义选择部分权限授权给第三方平台，
     * 因此第三方平台开发者需要通过该接口来获取公众号或小程序具体授权了哪些权限，
     * 而不是简单地认为自己声明的权限就是公众号或小程序授权的权限。
     *
     * @param authorizationCode 授权code,会在授权成功时返回给第三方平台，详见第三方平台授权流程说明
     * @return 获取到的授权信息
     * authorizer_appid	授权方appid
     * authorizer_access_token	授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * expires_in	有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * time 获取时间
     * authorizer_refresh_token	接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
     * func_info	授权给开发者的权限集列表，ID为1到26分别代表： 1、消息管理权限 2、用户管理权限 3、帐号服务权限 4、网页服务权限 5、微信小店权限 6、微信多客服权限 7、群发与通知权限 8、微信卡券权限 9、微信扫一扫权限 10、微信连WIFI权限 11、素材管理权限 12、微信摇周边权限 13、微信门店权限 14、微信支付权限 15、自定义菜单权限 16、获取认证状态及信息 17、帐号管理权限（小程序） 18、开发管理与数据分析权限（小程序） 19、客服消息管理权限（小程序） 20、微信登录权限（小程序） 21、数据分析权限（小程序） 22、城市服务接口权限 23、广告管理权限 24、开放平台帐号管理权限 25、 开放平台帐号管理权限（小程序） 26、微信电子发票权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
     */
    public static JSONObject getQueryAuth(String authorizationCode) {
        // 获取第三方平台component_access_token
        String componentAccessToken = getComponentToken();
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID")).fluentPut("authorization_code", authorizationCode);
        JSONObject resp = HttpClientUtil.doPostJson(MessageFormat.format(QUERY_AUTH_API, componentAccessToken), param, JSONObject.class);
        // 获取到授权信息
        JSONObject authorizationInfo = resp.getJSONObject("authorization_info");
        // 获取授权方appid
        String authorizerAppid = authorizationInfo.getString("authorizer_appid");
        authorizationInfo.put(TIME_KEY, System.currentTimeMillis());
        // 缓存授权信息
        ru.hset(AUTHORIZER_KEY, authorizerAppid, authorizationInfo);
        return authorizationInfo;
    }

    private static final String AUTHORIZER_TOKEN_API = "https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token={0}";

    /**
     * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
     * 该API用于在授权方令牌（authorizer_access_token）失效时，可用刷新令牌（authorizer_refresh_token）获取新的令牌。请注意，此处token是2小时刷新一次，开发者需要自行进行token的缓存，避免token的获取次数达到每日的限定额度
     *
     * @param authorizerAppid        授权方appid
     * @param authorizerRefreshToken 接口调用凭据刷新令牌
     * @return 获取到的授权信息
     * authorizer_appid	授权方appid
     * authorizer_access_token	授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * expires_in	有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * time 获取时间
     * authorizer_refresh_token	接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
     * func_info	授权给开发者的权限集列表，ID为1到26分别代表： 1、消息管理权限 2、用户管理权限 3、帐号服务权限 4、网页服务权限 5、微信小店权限 6、微信多客服权限 7、群发与通知权限 8、微信卡券权限 9、微信扫一扫权限 10、微信连WIFI权限 11、素材管理权限 12、微信摇周边权限 13、微信门店权限 14、微信支付权限 15、自定义菜单权限 16、获取认证状态及信息 17、帐号管理权限（小程序） 18、开发管理与数据分析权限（小程序） 19、客服消息管理权限（小程序） 20、微信登录权限（小程序） 21、数据分析权限（小程序） 22、城市服务接口权限 23、广告管理权限 24、开放平台帐号管理权限 25、 开放平台帐号管理权限（小程序） 26、微信电子发票权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
     */
    public static JSONObject getAuthorizerToken(String authorizerAppid, String authorizerRefreshToken) {
        // 缓存中获取授权信息
        JSONObject authorizationInfo = ru.hget(AUTHORIZER_KEY, authorizerAppid, JSONObject.class);
        if (authorizationInfo.getLong(TIME_KEY) != null && authorizationInfo.getLong(TIME_KEY) + MAX_TIME < System.currentTimeMillis()) {
            return authorizationInfo;
        }
        // 获取第三方平台component_access_token
        String componentAccessToken = getComponentToken();
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID")).fluentPut("authorizer_appid", authorizerAppid).fluentPut("authorizer_refresh_token", authorizerRefreshToken);
        JSONObject resp = HttpClientUtil.doPostJson(MessageFormat.format(AUTHORIZER_TOKEN_API, componentAccessToken), param, JSONObject.class);
        // 获取接口调用凭据刷新令牌和授权方接口调用凭据
        authorizationInfo.fluentPut(TIME_KEY, System.currentTimeMillis())
                .fluentPut("authorizer_access_token", resp.getString("authorizer_access_token"))
                .fluentPut("authorizer_refresh_token", resp.getString("authorizer_refresh_token"));
        // 缓存授权信息
        ru.hset(AUTHORIZER_KEY, authorizerAppid, authorizationInfo);
        return authorizationInfo;
    }

    /**
     * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
     * 该API用于在授权方令牌（authorizer_access_token）失效时，可用刷新令牌（authorizer_refresh_token）获取新的令牌。请注意，此处token是2小时刷新一次，开发者需要自行进行token的缓存，避免token的获取次数达到每日的限定额度
     *
     * @param authorizerAppid 授权方appid
     * @return 获取到的授权信息
     * authorizer_appid	授权方appid
     * authorizer_access_token	授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * expires_in	有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * time 获取时间
     * authorizer_refresh_token	接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
     * func_info	授权给开发者的权限集列表，ID为1到26分别代表： 1、消息管理权限 2、用户管理权限 3、帐号服务权限 4、网页服务权限 5、微信小店权限 6、微信多客服权限 7、群发与通知权限 8、微信卡券权限 9、微信扫一扫权限 10、微信连WIFI权限 11、素材管理权限 12、微信摇周边权限 13、微信门店权限 14、微信支付权限 15、自定义菜单权限 16、获取认证状态及信息 17、帐号管理权限（小程序） 18、开发管理与数据分析权限（小程序） 19、客服消息管理权限（小程序） 20、微信登录权限（小程序） 21、数据分析权限（小程序） 22、城市服务接口权限 23、广告管理权限 24、开放平台帐号管理权限 25、 开放平台帐号管理权限（小程序） 26、微信电子发票权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
     */
    public static JSONObject getAuthorizerToken(String authorizerAppid) {
        // 缓存中获取授权信息
        JSONObject authorizationInfo = ru.hget(AUTHORIZER_KEY, authorizerAppid, JSONObject.class);
        if (authorizationInfo.getLong(TIME_KEY) != null && authorizationInfo.getLong(TIME_KEY) + MAX_TIME < System.currentTimeMillis()) {
            return authorizationInfo;
        }
        // 获取第三方平台component_access_token
        String componentAccessToken = getComponentToken();
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID")).fluentPut("authorizer_appid", authorizerAppid).fluentPut("authorizer_refresh_token", authorizationInfo.get("authorizer_refresh_token"));
        JSONObject resp = HttpClientUtil.doPostJson(MessageFormat.format(AUTHORIZER_TOKEN_API, componentAccessToken), param, JSONObject.class);
        // 获取接口调用凭据刷新令牌和授权方接口调用凭据
        authorizationInfo.fluentPut(TIME_KEY, System.currentTimeMillis())
                .fluentPut("authorizer_access_token", resp.getString("authorizer_access_token"))
                .fluentPut("authorizer_refresh_token", resp.getString("authorizer_refresh_token"));
        // 缓存授权信息
        ru.hset(AUTHORIZER_KEY, authorizerAppid, authorizationInfo);
        return authorizationInfo;
    }

    private static final String GET_AUTHORIZER_INFO_API = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token={0}";

    /**
     * 获取授权方的帐号基本信息
     * 该API用于获取授权方的基本信息，包括头像、昵称、帐号类型、认证类型、微信号、原始ID和二维码图片URL。
     * 需要特别记录授权方的帐号类型，在消息及事件推送时，对于不具备客服接口的公众号，需要在5秒内立即响应；而若有客服接口，则可以选择暂时不响应，而选择后续通过客服接口来发送消息触达粉丝。
     *
     * @param authorizerAppid 授权方appid
     * @return 获取到的授权方信息
     * nick_name	授权方昵称
     * head_img	授权方头像
     * service_type_info	授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     * verify_type_info	授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     * user_name	授权方公众号的原始ID
     * principal_name	公众号的主体名称
     * alias	授权方公众号所设置的微信号，可能为空
     * business_info	用以了解以下功能的开通状况（0代表未开通，1代表已开通）： open_store:是否开通微信门店功能 open_scan:是否开通微信扫商品功能 open_pay:是否开通微信支付功能 open_card:是否开通微信卡券功能 open_shake:是否开通微信摇一摇功能
     * qrcode_url	二维码图片的URL，开发者最好自行也进行保存
     * authorization_info	授权信息
     * authorization_appid	授权方appid
     * func_info	公众号授权给开发者的权限集列表，ID为1到15时分别代表： 1.消息管理权限 2.用户管理权限 3.帐号服务权限 4.网页服务权限 5.微信小店权限 6.微信多客服权限 7.群发与通知权限 8.微信卡券权限 9.微信扫一扫权限 10.微信连WIFI权限 11.素材管理权限 12.微信摇周边权限 13.微信门店权限 14.微信支付权限 15.自定义菜单权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
     */
    public static JSONObject getAuthorizerInfo(String authorizerAppid) {
        // 获取第三方平台component_access_token
        String componentAccessToken = getComponentToken();
        // 请求微信接口获取
        JSONObject param = new JSONObject();
        param.fluentPut("component_appid", WechatConfigs.getProperty("component.AppID")).fluentPut("authorizer_appid", authorizerAppid);
        JSONObject resp = HttpClientUtil.doPostJson(MessageFormat.format(GET_AUTHORIZER_INFO_API, componentAccessToken), param, JSONObject.class);
        return resp;
    }

    private static final String COMPONENTLOGINPAGE = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid={0}&pre_auth_code={1}&redirect_uri={2}&auth_type={3}";

    /**
     * 获取授权页网址
     *
     * @param authType    要授权的帐号类型， 1则商户扫码后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。如果为未制定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
     * @param redirectUri 回调URI
     * @return 授权页网址
     */
    public static String getComponentLoginPage(String redirectUri, Integer authType) {
        String preAuthCode = getPreAuthCode();
        String componentAppid = WechatConfigs.getProperty("component.AppID");
        return MessageFormat.format(COMPONENTLOGINPAGE, componentAppid, preAuthCode, redirectUri, authType);
    }
}
