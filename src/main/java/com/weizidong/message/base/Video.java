package com.weizidong.message.base;

/**
 * 回复视频消息中的视频对象
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Video implements java.io.Serializable {
	/**
	 * 通过上传多媒体文件，得到的id
	 */
	private String MediaId;
	/**
	 * 视频消息的标题
	 */
	private String Title;
	/**
	 * 视频消息的描述
	 */
	private String Description;

	/**
	 * 获取 通过上传多媒体文件，得到的id
	 *
	 * @return 通过上传多媒体文件，得到的id
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * 设置 通过上传多媒体文件，得到的id
	 *
	 * @param image
	 *            通过上传多媒体文件，得到的id
	 */
	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}

	/**
	 * @return the Title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param Title
	 *            the Title to set
	 */
	public void setTitle(String Title) {
		this.Title = Title;
	}

	/**
	 * @return the Description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param Description
	 *            the Description to set
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
}
