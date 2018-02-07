package com.weizidong.message.output.base;

/**
 * 回复语音消息中的语音对象
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Voice implements java.io.Serializable {
	/**
	 * 通过上传多媒体文件，得到的id
	 */
	private String MediaId;

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
	 * @param mediaId
	 *            通过上传多媒体文件，得到的id
	 */
	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}
}