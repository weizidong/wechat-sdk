package com.weizidong.message.event;


import com.weizidong.message.base.EventMessage;
import com.weizidong.message.base.ScanCodeInfo;

/**
 * 自定义菜单事件
 * <p>
 * 扫码推事件且弹出“消息接收中”提示框的事件推送
 *
 * @author 魏自东
 * @date 2018/2/8 18:04
 */
public class ScanCodeWaitMsgEventMessage extends EventMessage {
    /**
     * 扫描信息
     */
    private ScanCodeInfo ScanCodeInfo;

    public ScanCodeWaitMsgEventMessage(ScanCodeInfo scanCodeInfo) {
        ScanCodeInfo = scanCodeInfo;
    }

    public ScanCodeInfo getScanCodeInfo() {
        return ScanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo ScanCodeInfo) {
        this.ScanCodeInfo = ScanCodeInfo;
    }

}
