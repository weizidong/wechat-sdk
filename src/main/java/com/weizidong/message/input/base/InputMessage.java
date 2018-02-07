package com.weizidong.message.input.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.JSON;
import com.weizidong.message.event.ClickEventMessage;
import com.weizidong.message.event.LocationEventMessage;
import com.weizidong.message.event.LocationSelectEventMessage;
import com.weizidong.message.event.PicPhotoOrAlbumEventMessage;
import com.weizidong.message.event.PicSysPhotoEventMessage;
import com.weizidong.message.event.PicWeixinEventMessage;
import com.weizidong.message.event.QrsceneScanEventMessage;
import com.weizidong.message.event.QrsceneSubscribeEventMessage;
import com.weizidong.message.event.ScanCodePushEventMessage;
import com.weizidong.message.event.ScanCodeWaitMsgEventMessage;
import com.weizidong.message.event.SubscribeEventMessage;
import com.weizidong.message.event.UnSubscribeEventMessage;
import com.weizidong.message.event.ViewEventMessage;
import com.weizidong.message.event.base.EventMessage;
import com.weizidong.message.event.base.ScanCodeInfo;
import com.weizidong.message.event.base.SendLocationInfo;
import com.weizidong.message.event.base.SendPicsInfo;
import com.weizidong.message.input.ImageInputMessage;
import com.weizidong.message.input.LinkInputMessage;
import com.weizidong.message.input.LocationInputMessage;
import com.weizidong.message.input.ShortVideoInputMessage;
import com.weizidong.message.input.TextInputMessage;
import com.weizidong.message.input.VideoInputMessage;
import com.weizidong.message.input.VoiceInputMessage;

/**
 * 微信消息接受对象,包含全部字段
 * 
 * @author WeiZiDong
 *
 */
@XmlRootElement(name = "xml")
public class InputMessage extends BaseMessage {
	/**
	 * 消息类型
	 */
	private String MsgType;
	/**
	 * 文本消息内容
	 */
	private String Content;
	/**
	 * 图片链接（由系统生成）
	 */
	private String PicUrl;
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
	 * 指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
	 */
	private String MenuId;
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
	 * 群发的消息ID
	 */
	private String MsgID;
	/**
	 * 群发的结构，为“send success”或“send fail”或“err(num)”。但send
	 * success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因，可能的情况如下：
	 * err(10001), //涉嫌广告 err(20001), //涉嫌政治 err(20004), //涉嫌社会 err(20002), //涉嫌色情
	 * err(20006), //涉嫌违法犯罪 err(20008), //涉嫌欺诈 err(20013), //涉嫌版权 err(22000),
	 * //涉嫌互推(互相宣传) err(21000), //涉嫌其他 err(30001) // 原创校验出现系统错误且用户选择了被判为转载就不群发
	 * err(30002) // 原创校验被判定为不能群发 err(30003) // 原创校验被判定为转载文且用户选择了被判为转载就不群发
	 */
	private String Status;
	/**
	 * tag_id下粉丝数；或者openid_list中的粉丝数
	 */
	private int TotalCount;
	/**
	 * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount =
	 * SentCount + ErrorCount
	 */
	private int FilterCount;
	/**
	 * 发送成功的粉丝数
	 */
	private int SentCount;
	/**
	 * 发送失败的粉丝数
	 */
	private int ErrorCount;
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

	@Override
	public String getMsgType() {
		return MsgType;
	}

	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

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
	public void setLocationY(String location_Y) {
		Location_Y = location_Y;
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
		// 转成小写
		return Event.toLowerCase();
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

	public String getStatus() {
		return Status;
	}

	@XmlElement(name = "Status")
	public void setStatus(String Status) {
		this.Status = Status;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	@XmlElement(name = "TotalCount")
	public void setTotalCount(int TotalCount) {
		this.TotalCount = TotalCount;
	}

	public int getFilterCount() {
		return FilterCount;
	}

	@XmlElement(name = "FilterCount")
	public void setFilterCount(int FilterCount) {
		this.FilterCount = FilterCount;
	}

	public int getSentCount() {
		return SentCount;
	}

	@XmlElement(name = "SentCount")
	public void setSentCount(int SentCount) {
		this.SentCount = SentCount;
	}

	public int getErrorCount() {
		return ErrorCount;
	}

	@XmlElement(name = "ErrorCount")
	public void setErrorCount(int ErrorCount) {
		this.ErrorCount = ErrorCount;
	}

	public String getMsgID() {
		return MsgID;
	}

	@XmlElement(name = "MsgID")
	public void setMsgID(String MsgID) {
		this.MsgID = MsgID;
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

	public TextInputMessage toTextInputMessage() {
		TextInputMessage inputMessage = new TextInputMessage(Content);
		initMessage(inputMessage);
		return inputMessage;
	}

	public ImageInputMessage toImageInputMessage() {
		ImageInputMessage inputMessage = new ImageInputMessage();
		inputMessage.setPicUrl(PicUrl);
		inputMessage.setMediaId(MediaId);
		initMessage(inputMessage);
		return inputMessage;
	}

	public VoiceInputMessage toVoiceInputMessage() {
		VoiceInputMessage inputMessage = new VoiceInputMessage();
		inputMessage.setFormat(PicUrl);
		inputMessage.setMediaId(MediaId);
		inputMessage.setRecognition(Recognition);
		initMessage(inputMessage);
		return inputMessage;
	}

	public VideoInputMessage toVideoInputMessage() {
		VideoInputMessage inputMessage = new VideoInputMessage();
		inputMessage.setMediaId(MediaId);
		inputMessage.setThumbMediaId(ThumbMediaId);
		initMessage(inputMessage);
		return inputMessage;
	}

	public ShortVideoInputMessage toShortVideoInputMessage() {
		ShortVideoInputMessage inputMessage = new ShortVideoInputMessage();
		inputMessage.setMediaId(MediaId);
		inputMessage.setThumbMediaId(ThumbMediaId);
		initMessage(inputMessage);
		return inputMessage;
	}

	public LocationInputMessage toLocationInputMessage() {
		LocationInputMessage inputMessage = new LocationInputMessage();
		inputMessage.setLocation_X(Location_X);
		inputMessage.setLocation_Y(Location_Y);
		inputMessage.setLabel(Label);
		inputMessage.setScale(Scale);
		initMessage(inputMessage);
		return inputMessage;
	}

	public LinkInputMessage toLinkInputMessage() {
		LinkInputMessage inputMessage = new LinkInputMessage();
		inputMessage.setTitle(Title);
		inputMessage.setDescription(Description);
		inputMessage.setUrl(Url);
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
		QrsceneScanEventMessage eventMessage = new QrsceneScanEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setTicket(Ticket);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public QrsceneSubscribeEventMessage toQrsceneSubscribeEventMessage() {
		QrsceneSubscribeEventMessage eventMessage = new QrsceneSubscribeEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setTicket(Ticket);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public LocationEventMessage toLocationEventMessage() {
		LocationEventMessage eventMessage = new LocationEventMessage();
		eventMessage.setLatitude(Latitude);
		eventMessage.setLongitude(Longitude);
		eventMessage.setPrecision(Precision);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public ClickEventMessage toClickEventMessage() {
		ClickEventMessage eventMessage = new ClickEventMessage();
		eventMessage.setEventKey(EventKey);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public ViewEventMessage toViewEventMessage() {
		ViewEventMessage eventMessage = new ViewEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setMenuId(MenuId);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public ScanCodePushEventMessage toScanCodePushEventMessage() {
		ScanCodePushEventMessage eventMessage = new ScanCodePushEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setScanCodeInfo(ScanCodeInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public ScanCodeWaitMsgEventMessage toScanCodeWaitMsgEventMessage() {
		ScanCodeWaitMsgEventMessage eventMessage = new ScanCodeWaitMsgEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setScanCodeInfo(ScanCodeInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public PicSysPhotoEventMessage toPicSysPhotoEventMessage() {
		PicSysPhotoEventMessage eventMessage = new PicSysPhotoEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setSendPicsInfo(SendPicsInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public PicPhotoOrAlbumEventMessage toPicPhotoOrAlbumEventMessage() {
		PicPhotoOrAlbumEventMessage eventMessage = new PicPhotoOrAlbumEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setSendPicsInfo(SendPicsInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public PicWeixinEventMessage toPicWeixinEventMessage() {
		PicWeixinEventMessage eventMessage = new PicWeixinEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setSendPicsInfo(SendPicsInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	public LocationSelectEventMessage toLocationSelectEventMessage() {
		LocationSelectEventMessage eventMessage = new LocationSelectEventMessage();
		eventMessage.setEventKey(EventKey);
		eventMessage.setSendLocationInfo(SendLocationInfo);
		initEventMessage(eventMessage);
		return eventMessage;
	}

	private void initMessage(BaseMessage inputMessage) {
		inputMessage.setToUserName(this.getToUserName());
		inputMessage.setFromUserName(this.getFromUserName());
		inputMessage.setMsgId(this.getMsgId());
		inputMessage.setCreateTime(this.getCreateTime());
	}

	private void initEventMessage(EventMessage eventMessage) {
		eventMessage.setToUserName(this.getToUserName());
		eventMessage.setFromUserName(this.getFromUserName());
		eventMessage.setCreateTime(this.getCreateTime());
	}

	public String getMenuId() {
		return MenuId;
	}

	@XmlElement(name = "MenuId")
	public void setMenuId(String MenuId) {
		this.MenuId = MenuId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
