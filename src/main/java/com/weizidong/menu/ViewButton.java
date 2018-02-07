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
package com.weizidong.menu;

/**
 * 跳转URL
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public class ViewButton extends SingleButton {

    /**
     * view类型必须.网页链接，用户点击菜单可打开链接，不超过256字节
     */
    private String url;

    public ViewButton() {
    }

    public ViewButton(String name, String url) {
        this.setName(name);
        this.url = url;
    }

    public String getType() {
        return ButtonType.View.toString();
    }

    /**
     * 获取 网页链接 <p>view类型必须.网页链接，用户点击菜单可打开链接，不超过256字节</p>
     *
     * @return 网页链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 网页链接 <p>view类型必须.网页链接，用户点击菜单可打开链接，不超过256字节</p>
     *
     * @param url 网页链接
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
