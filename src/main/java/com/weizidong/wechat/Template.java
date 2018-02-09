package com.weizidong.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import com.weizidong.utils.WechatConfigs;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * 模板消息接口
 * 模板消息仅用于公众号向用户发送重要的服务通知，只能用于符合其要求的服务场景中，如信用卡刷卡通知，商品购买成功通知等。不支持广告等营销类消息以及其它所有可能对用户造成骚扰的消息。
 * 关于使用规则，请注意：
 * <p>
 * 1、所有服务号都可以在功能->添加功能插件处看到申请模板消息功能的入口，但只有认证后的服务号才可以申请模板消息的使用权限并获得该权限；
 * 2、需要选择公众账号服务所处的2个行业，每月可更改1次所选行业；
 * 3、在所选择行业的模板库中选用已有的模板进行调用；
 * 4、每个账号可以同时使用25个模板。
 * 5、当前每个账号的模板消息的日调用上限为10万次，单个模板没有特殊限制。【
 * 2014年11月18日将接口调用频率从默认的日1万次提升为日10万次，可在MP登录后的开发者中心查看】。
 * 当账号粉丝数超过10W/100W/1000W时，模板消息的日调用上限会相应提升，以公众号MP后台开发者中心页面中标明的数字为准。
 *
 * @author 魏自东
 * @date 2018/2/9 14:23
 */
public class Template extends BaseResp {
    private static Logger logger = LogManager.getLogger(Template.class);
    private static final String SEND_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";

    /**
     * 发送模板消息
     * 注：url和miniprogram都是非必填字段，若都不传则模板无跳转；
     * 若都传，会优先跳转至小程序。
     * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
     *
     * @param touser      普通用户openid
     * @param templateId  模板ID
     * @param data        模板数据
     * @param url         模板跳转链接
     * @param miniprogram 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    public static void send(String touser, String templateId, JSONObject data, String url, JSONObject miniprogram) {
        Token t = Token.get();
        String api = MessageFormat.format(SEND_API, t.getAccess_token());
        JSONObject param = new JSONObject().fluentPut("touser", touser)
                .fluentPut("template_id", templateId).fluentPut("data", data);
        if (miniprogram != null) {
            param.put("miniprogram", miniprogram);
        }
        if (StringUtils.isNotBlank(url)) {
            param.put("url", url);
        }
        if (WechatConfigs.isDebug()) {
            logger.debug("发送模板消息：");
            logger.debug(JSON.toJSONString(param));
        }
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "发送模板消息失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
    }

    /**
     * 发送模板消息，不跳转
     *
     * @param touser     普通用户openid
     * @param templateId 模板ID
     * @param data       模板数据
     */
    public static void send(String touser, String templateId, JSONObject data) {
        send(touser, templateId, data, null);
    }

    /**
     * 发送模板消息，跳转到指定链接url
     *
     * @param touser     普通用户openid
     * @param templateId 模板ID
     * @param url        模板跳转链接
     * @param data       模板数据
     */
    public static void send(String touser, String templateId, JSONObject data, String url) {
        send(touser, templateId, data, url, null);
    }

    /**
     * 发送模板消息，跳转到指定小程序
     *
     * @param touser     普通用户openid
     * @param templateId 模板ID
     * @param appid      所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
     * @param pagepath   所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
     * @param data       模板数据
     */
    public static void send(String touser, String templateId, String appid, String pagepath, JSONObject data) {
        send(touser, templateId, data, null, new JSONObject().fluentPut("appid", appid).fluentPut("pagepath", pagepath));
    }

}
