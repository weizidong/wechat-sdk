package com.weizidong.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 微信返回结果基类
 *
 * @author 魏自东
 * @date 2018/2/7 10:39
 */
public class BaseResp implements Serializable {
    protected static final String ERRCODE = "errcode";
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String toError() {
        return errcode + " : " + errmsg + ErrCode.getCause(errcode);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
