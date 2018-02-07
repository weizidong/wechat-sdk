package com.weizidong.wechat;

import com.alibaba.fastjson.JSONObject;
import com.weizidong.base.BaseResp;
import com.weizidong.base.ErrCode;
import com.weizidong.exception.WeChatException;
import com.weizidong.utils.HttpClientUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.List;

/**
 * 微信用户标签
 *
 * @author 魏自东
 * @date 2018/2/7 11:29
 */
public class UserTag extends BaseResp {
    private static Logger logger = LogManager.getLogger(UserTag.class);
    private static final String CREATE_API = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token={0}";
    private static final String GET_API = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token={0}";
    private static final String UPDATE_API = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token={0}";
    private static final String DELETE_API = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token={0}";
    private static final String GET_USER_API = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token={0}";
    private static final String TAG_MEMBERS_API = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token={0}";
    private static final String UNTAG_MEMBERS_API = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token={0}";
    private static final String GETIDLIST_API = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token={0}";
    /**
     * 标签id，由微信分配
     */
    private Integer id;
    /**
     * 标签名，UTF8编码
     */
    private String name;
    /**
     * 此标签下粉丝数
     */
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 创建标签
     * 一个公众号，最多可以创建100个标签。
     *
     * @param name 标签名称
     * @return 创建好的标签
     */
    public static UserTag create(String name) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tag", new JSONObject().fluentPut("name", name));
        String api = MessageFormat.format(CREATE_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("创建标签失败：" + err);
            throw new WeChatException(err);
        }
        return resp.getJSONObject("tag").toJavaObject(UserTag.class);
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return 创建好的标签列表
     */
    public static List<UserTag> get() {
        Token t = Token.get();
        String api = MessageFormat.format(GET_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doGet(api, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("获取公众号已创建的标签失败：" + err);
            throw new WeChatException(err);
        }
        return resp.getJSONArray("tags").toJavaList(UserTag.class);
    }

    /**
     * 编辑标签
     *
     * @param tag 修改的标签
     */
    public static void update(UserTag tag) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tag", tag);
        String api = MessageFormat.format(UPDATE_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("编辑标签失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 删除标签
     * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。
     *
     * @param id 删除的标签ID
     */
    public static void delete(Integer id) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tag", new JSONObject().fluentPut("id", id));
        String api = MessageFormat.format(DELETE_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("删除标签失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 获取标签下粉丝列表
     *
     * @param tagid      标签ID
     * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return {
     * "count":2,//这次获取的粉丝数量
     * "data":{"openid":["ocYxcuAEy30bX0NXmGn4ypqx3tI0","ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"]},//粉丝列表
     * "next_openid":"ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"//拉取列表最后一个用户的openid
     * }
     */
    public static JSONObject getUser(Integer tagid, String nextOpenid) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tagid", tagid).fluentPut("next_openid", nextOpenid);
        String api = MessageFormat.format(GET_USER_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("获取标签下粉丝列表失败：" + err);
            throw new WeChatException(err);
        }
        return resp;
    }

    /**
     * 批量为用户打标签
     * 标签功能目前支持公众号为用户打上最多20个标签。
     *
     * @param openidList 粉丝列表
     * @param tagid      标签ID
     */
    public static void tagMembers(List<String> openidList, Integer tagid) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tagid", tagid).fluentPut("openid_list", openidList);
        String api = MessageFormat.format(TAG_MEMBERS_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("批量为用户打标签失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 批量为用户取消标签
     *
     * @param openidList 粉丝列表
     * @param tagid      标签ID
     */
    public static void untagMembers(List<String> openidList, Integer tagid) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("tagid", tagid).fluentPut("openid_list", openidList);
        String api = MessageFormat.format(UNTAG_MEMBERS_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("批量为用户取消标签失败：" + err);
            throw new WeChatException(err);
        }
    }

    /**
     * 获取用户身上的标签列表
     *
     * @param openid 用户Openid
     */
    public static List<Integer> getidlist(String openid) {
        Token t = Token.get();
        JSONObject param = new JSONObject().fluentPut("openid", openid);
        String api = MessageFormat.format(GETIDLIST_API, t.getAccess_token());
        JSONObject resp = HttpClientUtil.doPostJson(api, param, JSONObject.class);
        if (resp.containsKey(ERRCODE) && resp.getInteger(ERRCODE) != 0) {
            String err = resp.getInteger(ERRCODE) + " : " + resp.getString("errmsg") + ErrCode.getCause(resp.getInteger(ERRCODE));
            logger.error("获取用户身上的标签列表失败：" + err);
            throw new WeChatException(err);
        }
        return resp.getJSONArray("tagid_list").toJavaList(Integer.class);
    }

}
