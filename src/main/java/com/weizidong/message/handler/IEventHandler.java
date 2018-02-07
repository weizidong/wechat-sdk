package com.weizidong.message.handler;

import com.weizidong.message.event.*;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 接收事件推送
 *
 * @author WeiZiDong
 */
public interface IEventHandler {

    /**
     * 关注事件
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage subscribe(SubscribeEventMessage msg);

    /**
     * 取消关注事件
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage unSubscribe(UnSubscribeEventMessage msg);

    /**
     * 用户未关注，扫描带参数二维码事件
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage msg);

    /**
     * 用户已关注，扫描带参数二维码事件
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage qrsceneScan(QrsceneScanEventMessage msg);

    /**
     * 上报地理位置事件
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage location(LocationEventMessage msg);

    /**
     * 自定义菜单事件,点击菜单拉取消息时的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage click(ClickEventMessage msg);

    /**
     * 自定义菜单事件,点击菜单跳转链接时的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage view(ViewEventMessage msg);

    /**
     * 自定义菜单事件,扫码推事件的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage scanCodePush(ScanCodePushEventMessage msg);

    /**
     * 自定义菜单事件,扫码推事件的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage msg);

    /**
     * 自定义菜单事件,弹出系统拍照发图的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage picSysPhoto(PicSysPhotoEventMessage msg);

    /**
     * 自定义菜单事件,弹出拍照或者相册发图的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage msg);

    /**
     * 自定义菜单事件,弹出微信相册发图器的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage picWeixin(PicWeixinEventMessage msg);

    /**
     * 自定义菜单事件,弹出地理位置选择器的事件推送
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage locationSelect(LocationSelectEventMessage msg);
}
