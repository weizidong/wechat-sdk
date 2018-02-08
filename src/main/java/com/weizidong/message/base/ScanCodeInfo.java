package com.weizidong.message.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 扫描信息
 *
 * @author 魏自东
 * @date 2018/2/8 16:53
 */
@XmlRootElement(name = "ScanCodeInfo")
public class ScanCodeInfo {
    /**
     * 扫描类型，一般是qrcode
     */
    private String ScanType;
    /**
     * 扫描结果，即二维码对应的字符串信息
     */
    private String ScanResult;

    public String getScanType() {
        return ScanType;
    }

    @XmlElement(name = "ScanType")
    public void setScanType(String ScanType) {
        this.ScanType = ScanType;
    }

    public String getScanResult() {
        return ScanResult;
    }

    @XmlElement(name = "ScanResult")
    public void setScanResult(String ScanResult) {
        this.ScanResult = ScanResult;
    }
}
