package com.weizidong.message.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 发送的图片信息
 *
 * @author 魏自东
 * @date 2018/2/8 16:54
 */
@XmlRootElement(name = "SendPicsInfo")
public class SendPicsInfo {
    /**
     * 发送的图片数量
     */
    private int Count;
    /**
     * 图片列表
     */
    private List<PicList> PicList;

    public int getCount() {
        return Count;
    }

    @XmlElement(name = "Count")
    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<PicList> getPicList() {
        return PicList;
    }

    @XmlElementWrapper(name = "PicList")
    @XmlElement(name = "item")
    public void setPicList(List<PicList> PicList) {
        this.PicList = PicList;
    }
}
