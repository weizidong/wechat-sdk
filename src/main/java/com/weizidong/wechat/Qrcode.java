package com.weizidong.wechat;

import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * 微信二维码
 *
 * @author 魏自东
 * @date 2018/2/7 14:19
 */
public class Qrcode extends BaseResp {
    private static Logger logger = LogManager.getLogger(Qrcode.class);
    private static final String CREATE_API = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
    /**
     * 临时的整型参数值
     */
    private static final String QR_SCENE = "QR_SCENE";
    /**
     * 临时的字符串参数值
     */
    private static final String QR_STR_SCENE = "QR_STR_SCENE";
    /**
     * 永久的整型参数值
     */
    private static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    /**
     * 永久的字符串参数值
     */
    private static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    /**
     * 创建二维码ticket
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param actionName    二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * HTTP GET请求（请使用https协议）https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     */
    private static String create(Integer expireSeconds, String actionName, Integer sceneId, String sceneStr) {
        Token t = Token.get();
        JSONObject param = new JSONObject();
        if (StringUtils.equalsIgnoreCase(actionName, QR_SCENE)) {
            param.fluentPut("expire_seconds", expireSeconds).fluentPut("action_info", new JSONObject().fluentPut("scene", new JSONObject().fluentPut("scene_id", sceneId)));
        } else if (StringUtils.equalsIgnoreCase(actionName, QR_STR_SCENE)) {
            param.fluentPut("expire_seconds", expireSeconds).fluentPut("action_info", new JSONObject().fluentPut("scene", new JSONObject().fluentPut("scene_str", sceneStr)));
        } else if (StringUtils.equalsIgnoreCase(actionName, QR_LIMIT_SCENE)) {
            param.fluentPut("action_info", new JSONObject().fluentPut("scene", new JSONObject().fluentPut("scene_id", sceneId)));
        } else if (StringUtils.equalsIgnoreCase(actionName, QR_LIMIT_STR_SCENE)) {
            param.fluentPut("action_info", new JSONObject().fluentPut("scene", new JSONObject().fluentPut("scene_str", sceneStr)));
        }
        param.fluentPut("action_name", actionName);
        String api = MessageFormat.format(CREATE_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "创建二维码ticket失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
        return resp.getString("ticket");
    }

    /**
     * 创建临时的整型参数值二维码ticket
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * HTTP GET请求（请使用https协议）https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     */
    public static String create(Integer expireSeconds, Integer sceneId) {
        return create(expireSeconds, QR_SCENE, sceneId, null);
    }

    /**
     * 创建临时的整型参数值二维码ticket
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * HTTP GET请求（请使用https协议）https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     */
    public static String create(Integer expireSeconds, String sceneStr) {
        return create(expireSeconds, QR_STR_SCENE, null, sceneStr);
    }

    /**
     * 创建永久的整型参数值二维码ticket
     *
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * HTTP GET请求（请使用https协议）https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     */
    public static String create(Integer sceneId) {
        return create(null, QR_LIMIT_SCENE, sceneId, null);
    }

    /**
     * 创建永久的字符串参数值二维码ticket
     *
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * HTTP GET请求（请使用https协议）https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     */
    public static String create(String sceneStr) {
        return create(null, QR_LIMIT_STR_SCENE, null, sceneStr);
    }


}
