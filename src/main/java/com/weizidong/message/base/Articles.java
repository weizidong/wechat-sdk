package com.weizidong.message.base;

/**
 * 图文消息
 * 
 * @author WeiZiDong
 *
 */
public class Articles {

	/**
	 * 图文消息标题
	 */
	private String Title;
	/**
	 * 图文消息描述
	 */
	private String Description;
	/**
	 * 发送被动响应时设置的图片url 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	private String PicUrl;
	/**
	 * 发送客服消息时设置的图片URL
	 */
	private String picurl;
	/**
	 * 点击图文消息跳转链接
	 */
	private String Url;

	/**
	 * 获取 图文消息的标题
	 *
	 * @return 图文消息的标题
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * 设置 图文消息的标题
	 *
	 * @param Title
	 *            图文消息的标题
	 */
	public void setTitle(String Title) {
		this.Title = Title;
	}

	/**
	 * 获取 图文消息的描述
	 *
	 * @return 图文消息的描述
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * 设置 图文消息的描述
	 *
	 * @param Description
	 *            图文消息的描述
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}

	/**
	 * 获取 图片链接
	 *
	 * @return 图片链接
	 */
	public String getPicUrl() {
		return PicUrl;
	}

	/**
	 * 设置 图片链接
	 *
	 * @param PicUrl
	 *            图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	public void setPicUrl(String PicUrl) {
		this.PicUrl = PicUrl == null ? this.PicUrl : PicUrl;
		this.picurl = PicUrl == null ? this.picurl : PicUrl;
	}

	/**
	 * 获取 点击图文消息跳转链接
	 *
	 * @return 点击图文消息跳转链接
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * 设置 点击图文消息跳转链接
	 *
	 * @param Url
	 *            点击图文消息跳转链接
	 */
	public void setUrl(String Url) {
		this.Url = Url;
	}

	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl
	 *            the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl == null ? this.picurl : picurl;
		this.PicUrl = picurl == null ? this.PicUrl : picurl;
	}
}
