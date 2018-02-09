package com.weizidong.message.output;

import com.weizidong.message.base.Articles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 回复图文消息
 *
 * @author 魏自东
 * @date 2018/2/9 14:14
 */
@SuppressWarnings("serial")
public class NewsOutputMessage extends OutputMessage {

    /**
     * 消息类型:图文消息
     */
    private final String MsgType = "news";
    /**
     * 图文消息个数，限制为8条以内
     */
    private Integer ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
     */
    private List<Articles> Articles;

    /**
     * 创建一个图文 Output Message.
     * <p>
     * 并且MsgType的值为news.
     *
     * @param articles 图文列表
     */
    public NewsOutputMessage(List<Articles> articles) {
        setArticles(articles);
    }

    /**
     * 创建一个图文 Output Message.
     * <p>
     * 并且MsgType的值为news.
     *
     * @param articles 一个图文信息
     */
    public NewsOutputMessage(Articles articles) {
        this.Articles = Collections.singletonList(articles);
    }

    /**
     * 创建一个图文 Output Message.
     * <p>
     * 并且MsgType的值为news.
     *
     * @param title       图文消息标题
     * @param description 图文消息描述
     * @param picUrl      图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * @param url         点击图文消息跳转链接
     */
    public NewsOutputMessage(String title, String description, String picUrl, String url) {
        this.Articles = Collections.singletonList(new Articles(title, description, picUrl, url));
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
     * @return 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则只读取前8个
     */
    public List<Articles> getArticles() {
        return Articles;
    }

    /**
     * 设置 多条图文消息信息
     *
     * @param articles 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则只读取前8个
     */
    public void setArticles(List<Articles> articles) {
        if (articles != null) {
            if (articles.size() > 8) {
                articles = new ArrayList<>(articles.subList(0, 8));
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
