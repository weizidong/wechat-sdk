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
 * 自定义菜单按钮
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public abstract class BaseButton {

    /**
     * 菜单标题，不超过16个字节，子菜单不超过40个字节
     */
    private String name;

    /**
     * 获取 菜单标题
     *
     * @return 菜单标题
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 菜单标题
     *
     * @param name 菜单标题
     */
    public void setName(String name) {
        this.name = name;
    }
}
