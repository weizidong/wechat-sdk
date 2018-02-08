package com.weizidong.message.input;

import com.alibaba.fastjson.JSON;
import com.weizidong.message.base.*;
import com.weizidong.message.event.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信消息接受对象,包含全部字段
 *
 * @author WeiZiDong
 */
@XmlRootElement(name = "xml")
public class InputMessage extends BaseMessage {
    /**
     * 文本消息内容
     */
    private String Content;
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。<br/>
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private Long Scale;
    /**
     * 地理位置信息
     */
    private String Label;
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;
    /**
     * 事件类型
     */
    private String Event;
    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id<br/>
     * 事件KEY值，与自定义菜单接口中KEY值对应<br/>
     * 事件KEY值，设置的跳转URL
     */
    private String EventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;
    /**
     * 地理位置纬度
     */
    private String Latitude;
    /**
     * 地理位置经度
     */
    private String Longitude;
    /**
     * 地理位置精度
     */
    private String Precision;
    /**
     * 指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
     */
    private String MenuId;
    /**
     * 扫码推事件的事件推送
     */
    private ScanCodeInfo ScanCodeInfo;
    /**
     * 弹出系统拍照发图的事件推送
     */
    private SendPicsInfo SendPicsInfo;
    /**
     * 弹出地理位置选择器的事件推送
     */
    private SendLocationInfo SendLocationInfo;

    public String getContent() {
        return Content;
    }

    @XmlElement(name = "Content")
    public void setContent(String content) {
        Content = content;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    @XmlElement(name = "PicUrl")
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getLocation_X() {
        return Location_X;
    }

    @XmlElement(name = "Location_X")
    public void setLocation_X(String locationX) {
        Location_X = locationX;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    @XmlElement(name = "Location_Y")
    public void setLocation_Y(String locationY) {
        Location_Y = locationY;
    }

    public Long getScale() {
        return Scale;
    }

    @XmlElement(name = "Scale")
    public void setScale(Long scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    @XmlElement(name = "Label")
    public void setLabel(String label) {
        Label = label;
    }

    public String getTitle() {
        return Title;
    }

    @XmlElement(name = "Title")
    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    @XmlElement(name = "Url")
    public void setUrl(String url) {
        Url = url;
    }

    public String getEvent() {
        return Event;
    }

    @XmlElement(name = "Event")
    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    @XmlElement(name = "EventKey")
    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getMediaId() {
        return MediaId;
    }

    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    @XmlElement(name = "ThumbMediaId")
    public void setThumbMediaId(String ThumbMediaId) {
        this.ThumbMediaId = ThumbMediaId;
    }

    public String getFormat() {
        return Format;
    }

    @XmlElement(name = "Format")
    public void setFormat(String format) {
        Format = format;
    }

    public String getRecognition() {
        return Recognition;
    }

    @XmlElement(name = "Recognition")
    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getTicket() {
        return Ticket;
    }

    @XmlElement(name = "Ticket")
    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getLatitude() {
        return Latitude;
    }

    @XmlElement(name = "Latitude")
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    @XmlElement(name = "Longitude")
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    @XmlElement(name = "Precision")
    public void setPrecision(String Precision) {
        this.Precision = Precision;
    }


    public ScanCodeInfo getScanCodeInfo() {
        return ScanCodeInfo;
    }

    @XmlElement(name = "ScanCodeInfo")
    public void setScanCodeInfo(ScanCodeInfo ScanCodeInfo) {
        this.ScanCodeInfo = ScanCodeInfo;
    }

    public SendLocationInfo getSendLocationInfo() {
        return SendLocationInfo;
    }

    @XmlElement(name = "SendLocationInfo")
    public void setSendLocationInfo(SendLocationInfo SendLocationInfo) {
        this.SendLocationInfo = SendLocationInfo;
    }

    public SendPicsInfo getSendPicsInfo() {
        return SendPicsInfo;
    }

    @XmlElement(name = "SendPicsInfo")
    public void setSendPicsInfo(SendPicsInfo SendPicsInfo) {
        this.SendPicsInfo = SendPicsInfo;
    }

    public String getMenuId() {
        return MenuId;
    }

    @XmlElement(name = "MenuId")
    public void setMenuId(String MenuId) {
        this.MenuId = MenuId;
    }

    public TextInputMessage toTextInputMessage() {
        TextInputMessage inputMessage = new TextInputMessage(Content);
        initMessage(inputMessage);
        return inputMessage;
    }

    public ImageInputMessage toImageInputMessage() {
        ImageInputMessage inputMessage = new ImageInputMessage(PicUrl, MediaId);
        initMessage(inputMessage);
        return inputMessage;
    }

    public VoiceInputMessage toVoiceInputMessage() {
        VoiceInputMessage inputMessage = new VoiceInputMessage(MediaId, Format, Recognition);
        initMessage(inputMessage);
        return inputMessage;
    }

    public VideoInputMessage toVideoInputMessage() {
        VideoInputMessage inputMessage = new VideoInputMessage(MediaId, ThumbMediaId);
        initMessage(inputMessage);
        return inputMessage;
    }

    public ShortVideoInputMessage toShortVideoInputMessage() {
        ShortVideoInputMessage inputMessage = new ShortVideoInputMessage(MediaId, ThumbMediaId);
        initMessage(inputMessage);
        return inputMessage;
    }

    public LocationInputMessage toLocationInputMessage() {
        LocationInputMessage inputMessage = new LocationInputMessage(Location_X, Location_Y, Scale, Label);
        initMessage(inputMessage);
        return inputMessage;
    }

    public LinkInputMessage toLinkInputMessage() {
        LinkInputMessage inputMessage = new LinkInputMessage(Title, Description, Url);
        initMessage(inputMessage);
        return inputMessage;
    }

    public SubscribeEventMessage toSubscribeEventMessage() {
        SubscribeEventMessage eventMessage = new SubscribeEventMessage();
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public UnSubscribeEventMessage toUnSubscribeEventMessage() {
        UnSubscribeEventMessage eventMessage = new UnSubscribeEventMessage();
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public QrsceneScanEventMessage toQrsceneScanEventMessage() {
        QrsceneScanEventMessage eventMessage = new QrsceneScanEventMessage(Ticket);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public QrsceneSubscribeEventMessage toQrsceneSubscribeEventMessage() {
        QrsceneSubscribeEventMessage eventMessage = new QrsceneSubscribeEventMessage(Ticket);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public LocationEventMessage toLocationEventMessage() {
        LocationEventMessage eventMessage = new LocationEventMessage(Latitude, Longitude, Precision);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public ClickEventMessage toClickEventMessage() {
        ClickEventMessage eventMessage = new ClickEventMessage();
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public ViewEventMessage toViewEventMessage() {
        ViewEventMessage eventMessage = new ViewEventMessage(MenuId);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public ScanCodePushEventMessage toScanCodePushEventMessage() {
        ScanCodePushEventMessage eventMessage = new ScanCodePushEventMessage(ScanCodeInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public ScanCodeWaitMsgEventMessage toScanCodeWaitMsgEventMessage() {
        ScanCodeWaitMsgEventMessage eventMessage = new ScanCodeWaitMsgEventMessage(ScanCodeInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public PicSysPhotoEventMessage toPicSysPhotoEventMessage() {
        PicSysPhotoEventMessage eventMessage = new PicSysPhotoEventMessage(SendPicsInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public PicPhotoOrAlbumEventMessage toPicPhotoOrAlbumEventMessage() {
        PicPhotoOrAlbumEventMessage eventMessage = new PicPhotoOrAlbumEventMessage(SendPicsInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public PicWeixinEventMessage toPicWeixinEventMessage() {
        PicWeixinEventMessage eventMessage = new PicWeixinEventMessage(SendPicsInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    public LocationSelectEventMessage toLocationSelectEventMessage() {
        LocationSelectEventMessage eventMessage = new LocationSelectEventMessage(SendLocationInfo);
        initEventMessage(eventMessage);
        return eventMessage;
    }

    private void initMessage(BaseMessage inputMessage) {
        inputMessage.setToUserName(this.getToUserName());
        inputMessage.setFromUserName(this.getFromUserName());
        inputMessage.setCreateTime(this.getCreateTime());
        inputMessage.setMsgId(this.getMsgId());
        inputMessage.setMsgType(this.getMsgType());
    }

    private void initEventMessage(EventMessage eventMessage) {
        initMessage(eventMessage);
        eventMessage.setEvent(Event);
        eventMessage.setEventKey(EventKey);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
