package com.weizidong.message.event.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片
 * 
 * @author WeiZiDong
 *
 */
@XmlRootElement(name = "item")
public class PicList {
	/**
	 * 图片的MD5值，开发者若需要，可用于验证接收到图片
	 */
	private String PicMd5Sum;

	public String getPicMd5Sum() {
		return PicMd5Sum;
	}

	@XmlElement(name = "PicMd5Sum")
	public void setPicMd5Sum(String PicMd5Sum) {
		this.PicMd5Sum = PicMd5Sum;
	}
}
