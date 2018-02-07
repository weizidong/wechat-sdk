package com.weizidong.message.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.weizidong.message.output.base.Articles;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 回复图文消息
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class NewsOutputMessage extends OutputMessage {

	/**
	 * 消息类型:图文消息
	 */
	private final String MsgType = "news";
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private Integer ArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 */
	private List<Articles> Articles;

	public NewsOutputMessage(List<Articles> articles) {
		this.Articles = articles;
	}

	public NewsOutputMessage(Articles articles) {
		this.Articles = Arrays.asList(articles);
	}

	/**
	 * 获取 消息类型
	 *
	 * @return 消息类型
	 */
	@Override
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * 获取 图文消息个数
	 *
	 * @return 图文消息个数
	 */
	public Integer getArticleCount() {
		return ArticleCount;
	}

	/**
	 * 获取 多条图文消息信息
	 *
	 * @return 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则只读取前10个
	 */
	public List<Articles> getArticles() {
		return Articles;
	}

	/**
	 * 设置 多条图文消息信息
	 *
	 * @param articles
	 *            多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则只读取前10个
	 */
	public void setArticles(List<Articles> articles) {
		if (articles != null) {
			if (articles.size() > 10) {
				articles = new ArrayList<Articles>(articles.subList(0, 10));
			}
			this.ArticleCount = articles.size();
		}
		this.Articles = articles;
	}

	@Override
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[" + this.MsgType + "]]></MsgType>");
		sb.append("<ArticleCount>").append(this.ArticleCount).append("</ArticleCount>");
		sb.append("<Articles>");
		for (Articles article : Articles) {
			sb.append("<item>");
			sb.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
			sb.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
			sb.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
			sb.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
			sb.append("</item>");
		}
		sb.append("</Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
}
