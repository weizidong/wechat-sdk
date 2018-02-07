package com.weizidong.wechat;

import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 微信用户信息
 *
 * @author 魏自东
 * @date 2018/2/7 11:10
 */
public class WechatUser extends BaseResp {
    private static Logger logger = LogManager.getLogger(WechatUser.class);
    private static final String USER_INFO_API = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
    private static final String BATCHGET_USER_API = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={0}";
    private static final String UPDATEREMARK_API = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token={0}";
    private static final String GET_USER_API = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";
    private static final String GETBLACKLIST_API = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token={0}";
    private static final String BATCHBLACKLIST_API = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token={0}";
    private static final String BATCHUNBLACKLIST_API = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token={0}";
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Integer subscribe;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private Date subscribe_time;
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupid;
    /**
     * 用户被打上的标签ID列表
     */
    private List<Integer> tagid_list;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private List<String> privilege;

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Date getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Date subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public List<Integer> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Integer> tagid_list) {
        this.tagid_list = tagid_list;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param openid 普通用户的标识，对当前公众号唯一
     * @return 用户信息
     */
    public static WechatUser getUserInfo(String openid) {
        Token t = Token.get();
        String api = MessageFormat.format(USER_INFO_API, t.getAccess_token(), openid);
        WechatUser user = HttpClientUtil.doGet(api, WechatUser.class);
        if (user.getErrcode() != null && user.getErrcode() != 0) {
            logger.error("获取用户基本信息失败：" + user.toError());
            throw new WeChatException(user.toError());
        }
        return user;
    }

    /**
     * 批量获取用户基本信息
     *
     * @param openidList 普通用户的标识列表
     * @return 用户信息
     */
    public static List<WechatUser> batchGetUserInfo(List<String> openidList) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("user_list", openidList.stream().map(openid ->
                new JSONObject().fluentPut("openid", openid).fluentPut("lang", "zh_CN")).collect(Collectors.toList()));
        String api = MessageFormat.format(BATCHGET_USER_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("批量获取用户基本信息失败：" + err);
            throw new WeChatException(err);
        }
        return resp.getJSONArray("user_info_list").toJavaList(WechatUser.class);
    }

    /**
     * 设置用户备注名
     *
     * @param openid 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     */
    public static void updateRemark(String openid, String remark) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("openid", openid).fluentPut("remark", remark);
        String api = MessageFormat.format(UPDATEREMARK_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("设置用户备注名失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 获取用户列表
     * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
     *
     * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return {"total":2,
     * "count":2,
     * "data":{"openid":["OPENID1","OPENID2"]},
     * "next_openid":"NEXT_OPENID"
     * }
     */
    public static JSONObject updateRemark(String nextOpenid) {
        Token t = Token.get();
        String api = MessageFormat.format(GET_USER_API, t.getAccess_token(), nextOpenid);
        JSONObject resp = HttpClientUtil.doGet(api, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("获取用户列表失败：" + err);
            throw new WeChatException(err);
        }
        return resp;
    }

    /**
     * 获取公众号的黑名单列表
     * 公众号可通过该接口来获取帐号的黑名单列表，黑名单列表由一串 OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     * 该接口每次调用最多可拉取 10000 个OpenID，当列表数较多时，可以通过多次拉取的方式来满足需求。
     *
     * @param beginOpenid 当 begin_openid 为空时，默认从开头拉取。
     * @return {
     * "total":23000,
     * "count":10000,
     * "data":{"openid":["OPENID1","OPENID2",...,"OPENID10000"]},
     * "next_openid":"OPENID10000"
     * }
     */
    public static JSONObject getBlackList(String beginOpenid) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("begin_openid", beginOpenid);
        String api = MessageFormat.format(GETBLACKLIST_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("获取公众号的黑名单列表失败：" + err);
            throw new WeChatException(err);
        }
        return resp;
    }

    /**
     * 拉黑用户
     * 公众号可通过该接口来拉黑一批用户，黑名单列表由一串 OpenID （加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     *
     * @param openidList 需要拉入黑名单的用户的openid，一次拉黑最多允许20个
     */
    public static void batchBlackList(List<String> openidList) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("openid_list", openidList);
        String api = MessageFormat.format(BATCHBLACKLIST_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("拉黑用户失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 取消拉黑用户
     * 公众号可通过该接口来取消拉黑一批用户，黑名单列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     *
     * @param openidList 需要拉出黑名单的用户的openid，一次拉黑最多允许20个
     */
    public static void batchUnBlackList(List<String> openidList) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("openid_list", openidList);
        String api = MessageFormat.format(BATCHUNBLACKLIST_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("取消拉黑用户失败：" + err);
            throw new WeChatException(err);
        }
    }


}
