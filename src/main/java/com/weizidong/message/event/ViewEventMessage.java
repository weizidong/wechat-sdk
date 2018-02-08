package com.weizidong.message.event;

import com.weizidong.message.base.EventMessage;

/**
 * 自定义菜单事件
 * <p>
 * 点击菜单跳转链接时的事件推送
 *
 * @author 魏自东
 * @date 2018/2/8 18:03
 */
public class ViewEventMessage extends EventMessage {
    private String MenuId;

    public ViewEventMessage(String menuId) {
        MenuId = menuId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String MenuId) {
        this.MenuId = MenuId;
    }

}
