package com.weizidong.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.base.MsgType;
import com.weizidong.exception.WeChatException;
import com.weizidong.message.base.Articles;
import com.weizidong.utils.HttpClientUtil;
import com.weizidong.utils.WechatConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客服消息接口
 * 当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），微信将会把消息数据推送给开发者，
 * 开发者可以在一段时间内（目前修改为48小时）调用客服接口，通过POST一个JSON数据包来发送消息给普通用户。
 *
 * @author 魏自东
 * @date 2018/2/9 14:23
 */
public class Custom extends BaseResp {
    private static Logger logger = LogManager.getLogger(Custom.class);
    private static final String ADD_KFACCOUNT_API = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token={0}";
    private static final String UPDATE_KFACCOUNT_API = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token={0}";
    private static final String DEL_KFACCOUNT_API = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token={0}";
    private static final String GETKFLIST_API = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token={0}";
    private static final String SEND_API = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

    /**
     * 添加客服帐号
     * 开发者可以通过本接口为公众号添加客服账号，每个公众号最多添加10个客服账号。
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     */
    public static void addKfAccount(String kfAccount, String nickname, String password) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("kf_account", kfAccount).fluentPut("nickname", nickname).fluentPut("password", password);
        String api = MessageFormat.format(ADD_KFACCOUNT_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "添加客服帐号失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
    }

    /**
     * 修改客服帐号
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     */
    public static void updateKfAccount(String kfAccount, String nickname, String password) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("kf_account", kfAccount).fluentPut("nickname", nickname).fluentPut("password", password);
        String api = MessageFormat.format(UPDATE_KFACCOUNT_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "修改客服帐号失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
    }

    /**
     * 删除客服帐号
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     */
    public static void delKfAccount(String kfAccount, String nickname, String password) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("kf_account", kfAccount).fluentPut("nickname", nickname).fluentPut("password", password);
        String api = MessageFormat.format(DEL_KFACCOUNT_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "删除客服帐号失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
    }

    /**
     * 获取所有客服账号
     *
     * @return [
     * {
     * "kf_account": "test1@test", // 完整客服账号，格式为：账号前缀@公众号微信号
     * "kf_nick": "ntest1", // 客服昵称
     * "kf_id": "1001", // 客服工号
     * "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0" // 客服头像
     * },
     * ...
     * ]
     */
    public static List<JSONObject> getKfList() {
        Token t = Token.get();
        String api = MessageFormat.format(GETKFLIST_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doGet(api, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "获取所有客服账号失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
        return resp.getJSONArray("kf_list").toJavaList(JSONObject.class);
    }

    /**
     * 客服接口-发消息
     *
     * @param touser  普通用户openid
     * @param msgtype 消息类型，
     *                文本为text，
     *                图片为image，
     *                语音为voice，
     *                视频消息为video，
     *                音乐消息为music，
     *                图文消息（点击跳转到外链）为news，
     *                图文消息（点击跳转到图文消息页面）为mpnews，
     *                卡券为wxcard，
     *                小程序为miniprogrampage
     * @param content 消息内容
     */
    public static void send(String touser, String msgtype, JSONObject content) {
        Token t = Token.get();
        String api = MessageFormat.format(SEND_API, t.getAccess_token());
        JSONObject param = new JSONObject().fluentPut("touser", touser).fluentPut("msgtype", msgtype).fluentPut(msgtype, content);
        if (WechatConfigs.isDebug()) {
            logger.debug("发送客服消息：");
            logger.debug(JSON.toJSONString(param));
        }
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = "发送客服消息失败：\t" + resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            throw new WeChatException(err);
        }
    }

    /**
     * 发送文本消息
     *
     * @param touser  普通用户openid
     * @param content 消息内容
     */
    public static void sendText(String touser, String content) {
        send(touser, MsgType.TEXT, new JSONObject().fluentPut("content", content));
    }

    /**
     * 发送文本消息,包含连接
     *
     * @param touser  普通用户openid
     * @param content 消息内容,发送文本消息时，支持插入跳小程序的文字链
     *                文本内容<a href="http://www.qq.com" data-miniprogram-appid="appid" data-miniprogram-path="pages/index/index">点击跳小程序</a>
     *                说明：
     *                1.data-miniprogram-appid 项，填写小程序appid，则表示该链接跳小程序；
     *                2.data-miniprogram-path项，填写小程序路径，路径与app.json中保持一致，可带参数；
     *                3.对于不支持data-miniprogram-appid 项的客户端版本，如果有herf项，则仍然保持跳href中的网页链接；
     *                4.data-miniprogram-appid对应的小程序必须与公众号有绑定关系。
     */
    public static void sendText(String touser, String content, String href) {
        send(touser, MsgType.TEXT, new JSONObject().fluentPut("content", content).fluentPut("href", href));
    }

    /**
     * 发送文本消息,包含可跳转小程序的连接
     *
     * @param touser  普通用户openid
     * @param content 消息内容,发送文本消息时，支持插入跳小程序的文字链
     *                文本内容<a href="http://www.qq.com" data-miniprogram-appid="appid" data-miniprogram-path="pages/index/index">点击跳小程序</a>
     *                说明：
     *                1.data-miniprogram-appid 项，填写小程序appid，则表示该链接跳小程序；
     *                2.data-miniprogram-path项，填写小程序路径，路径与app.json中保持一致，可带参数；
     *                3.对于不支持data-miniprogram-appid 项的客户端版本，如果有herf项，则仍然保持跳href中的网页链接；
     *                4.data-miniprogram-appid对应的小程序必须与公众号有绑定关系。
     */
    public static void sendText(String touser, String content, String href, String miniprogramAppid, String miniprogramPath) {
        send(touser, MsgType.TEXT, new JSONObject().fluentPut("content", content).fluentPut("href", href).fluentPut("data-miniprogram-appid", miniprogramAppid).fluentPut("data-miniprogram-path", miniprogramPath));
    }

    /**
     * 发送图片消息
     *
     * @param touser  普通用户openid
     * @param mediaId 发送的图片消息的媒体ID
     */
    public static void sendImage(String touser, String mediaId) {
        send(touser, MsgType.IMAGE, new JSONObject().fluentPut("media_id", mediaId));
    }

    /**
     * 发送语音消息
     *
     * @param touser  普通用户openid
     * @param mediaId 发送的语音消息的媒体ID
     */
    public static void sendVoice(String touser, String mediaId) {
        send(touser, MsgType.VOICE, new JSONObject().fluentPut("media_id", mediaId));
    }

    /**
     * 发送视频消息
     *
     * @param touser       普通用户openid
     * @param mediaId      发送的视频消息的媒体ID
     * @param thumbMediaId 缩略图图片的媒体ID
     * @param title        视频消息的标题
     * @param description  视频消息的描述
     */
    public static void sendVideo(String touser, String mediaId, String thumbMediaId, String title, String description) {
        send(touser, MsgType.VIDEO, new JSONObject()
                .fluentPut("media_id", mediaId)
                .fluentPut("thumb_media_id", thumbMediaId)
                .fluentPut("title", title)
                .fluentPut("description", description));
    }

    /**
     * 发送音乐消息
     *
     * @param touser       普通用户openid
     * @param thumbMediaId 缩略图图片的媒体ID
     * @param title        音乐消息的标题
     * @param description  音乐消息的描述
     * @param musicurl     音乐链接
     * @param hqmusicurl   高品质音乐链接，wifi环境优先使用该链接播放音乐
     */
    public static void sendMusic(String touser, String title, String description, String musicurl, String hqmusicurl, String thumbMediaId) {
        send(touser, MsgType.MUSIC, new JSONObject()
                .fluentPut("title", title)
                .fluentPut("description", description)
                .fluentPut("musicurl", musicurl)
                .fluentPut("hqmusicurl", hqmusicurl)
                .fluentPut("thumb_media_id", thumbMediaId));
    }

    /**
     * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将取前8条。
     *
     * @param touser   普通用户openid
     * @param articles 图文消息列表
     */
    public static void sendNews(String touser, List<Articles> articles) {
        if (articles == null) {
            return;
        }
        if (articles.size() > 8) {
            articles = new ArrayList<>(articles.subList(0, 8));
        }
        JSONArray list = new JSONArray();
        for (Articles a : articles) {
            list.add(new JSONObject().fluentPut("title", a.getTitle()).fluentPut("description", a.getDescription()).fluentPut("url", a.getUrl()).fluentPut("picurl", a.getPicUrl()));
        }
        send(touser, MsgType.NEWS, new JSONObject().fluentPut("articles", list));
    }

    /**
     * 发送单个图文消息（点击跳转到外链）
     *
     * @param touser   普通用户openid
     * @param articles 图文消息
     */
    public static void sendNews(String touser, Articles articles) {
        sendNews(touser, Collections.singletonList(articles));
    }

    /**
     * 发送单个图文消息（点击跳转到外链）
     *
     * @param touser      普通用户openid
     * @param title       图文消息标题
     * @param description 图文消息描述
     * @param picUrl      发送被动响应时设置的图片url 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * @param url         点击图文消息跳转链接
     */
    public static void sendNews(String touser, String title, String description, String picUrl, String url) {
        sendNews(touser, new Articles(title, description, picUrl, url));
    }

    /**
     * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     *
     * @param touser  普通用户openid
     * @param mediaId 图文消息（点击跳转到图文消息页）的媒体ID
     */
    public static void sendMpnews(String touser, String mediaId) {
        send(touser, MsgType.MPNEWS, new JSONObject().fluentPut("media_id", mediaId));
    }

    /**
     * 发送卡券
     *
     * @param touser 普通用户openid
     * @param cardId 卡券ID。
     */
    public static void sendWxcard(String touser, String cardId) {
        send(touser, MsgType.WXCARD, new JSONObject().fluentPut("card_id", cardId));
    }

    /**
     * 发送小程序卡片（要求小程序与公众号已关联）
     *
     * @param touser       普通用户openid
     * @param title        小程序卡片的标题
     * @param appid        小程序的appid，要求小程序的appid需要与公众号有关联关系
     * @param pagepath     小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
     * @param thumbMediaId 小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     */
    public static void sendMiniProgramPage(String touser, String title, String appid, String pagepath, String thumbMediaId) {
        send(touser, MsgType.MINIPROGRAMPAGE, new JSONObject().fluentPut("title", title).fluentPut("appid", appid).fluentPut("pagepath", pagepath).fluentPut("thumb_media_id", thumbMediaId));
    }
}
