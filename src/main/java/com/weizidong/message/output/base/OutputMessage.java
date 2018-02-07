/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weizidong.message.output.base;

import java.io.Serializable;

/**
 * 被动返回消息的基类
 *
 * @author 魏自东
 * @date 2018/2/7 15:29
 */
public abstract class OutputMessage implements Serializable {

    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;

    /**
     * 获取 接收方帐号（收到的OpenID）
     *
     * @return 接收方帐号（收到的OpenID）
     */
    public String getToUserName() {
        return ToUserName;
    }

    /**
     * 设置 接收方帐号（收到的OpenID）
     *
     * @return 接收方帐号（收到的OpenID）
     */
    public String getFromUserName() {
        return FromUserName;
    }

    /**
     * 获取 消息创建时间 （整型）
     *
     * @return 消息创建时间 （整型）
     */
    public Long getCreateTime() {
        return CreateTime;
    }

    /**
     * 获取 消息类型
     *
     * @return 消息类型
     */
    public abstract String getMsgType();

    /**
     * 将对象转换为xml字符串
     *
     * @return 对象xml字符串
     */
    public abstract String toXML();

    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public void setCreateTime(Long CreateTime) {
        this.CreateTime = CreateTime;
    }
}
