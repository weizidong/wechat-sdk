package com.weizidong.base;

/**
 * MsgType类型常量
 *
 * @author 魏自东
 * @date 2018/2/7 15:25
 */
public abstract class MsgType {
    /**
     * 文本消息
     */
    public static final String TEXT = "text";
    /**
     * 图片消息
     */
    public static final String IMAGE = "image";
    /**
     * 语音消息
     */
    public static final String VOICE = "voice";
    /**
     * 视频消息
     */
    public static final String VIDEO = "video";
    /**
     * 小视频消息
     */
    public static final String SHORTVIDEO = "shortvideo";
    /**
     * 地理位置消息
     */
    public static final String LOCATION = "location";
    /**
     * 链接消息
     */
    public static final String LINK = "link";
    /**
     * 音乐消息(发送)
     */
    public static final String MUSIC = "music";
    /**
     * 图文消息(发送)
     */
    public static final String MPNEWS = "mpnews";
    /**
     * 图文消息(发送)
     */
    public static final String NEWS = "news";
    /**
     * 卡券消息(发送)
     */
    public static final String WXCARD = "wxcard";
    /**
     * 小程序卡片(发送)
     */
    public static final String MINIPROGRAMPAGE = "miniprogrampage";
    /**
     * 事件推送
     */
    public static final String EVENT = "event";

    /**
     * 事件具体类型
     */
    public static abstract class Event {
        /**
         * 订阅
         */
        public static final String SUBSCRIBE = "SUBSCRIBE";
        /**
         * 取消订阅
         */
        public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
        /**
         * 扫码
         */
        public static final String SCAN = "SCAN";
        /**
         * 上报地理位置
         */
        public static final String LOCATION = "LOCATION";
        /**
         * 点击菜单拉取消息
         */
        public static final String CLICK = "CLICK";
        /**
         * 点击菜单跳转链接
         */
        public static final String VIEW = "VIEW";
        /**
         * 扫码推事件的事件推送
         */
        public static final String SCANCODE_PUSH = "SCANCODE_PUSH";
        /**
         * 扫码推事件且弹出“消息接收中”提示框的事件推送
         */
        public static final String SCANCODE_WAITMSG = "SCANCODE_WAITMSG";
        /**
         * 弹出系统拍照发图的事件推送
         */
        public static final String PIC_SYSPHOTO = "pic_sysphoto";
        /**
         * 弹出拍照或者相册发图的事件推送
         */
        public static final String PIC_PHOTO_OR_ALBUM = "PIC_PHOTO_OR_ALBUM";
        /**
         * 弹出微信相册发图器的事件推送
         */
        public static final String PIC_WEIXIN = "PIC_WEIXIN";
        /**
         * 弹出地理位置选择器的事件推送
         */
        public static final String LOCATION_SELECT = "LOCATION_SELECT";

    }
}
