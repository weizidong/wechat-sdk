package com.weizidong.exception;

/**
 * 微信操作全局异常
 *
 * @author 魏自东
 * @date 2018/2/7 15:25
 */
public class WeChatException extends RuntimeException {

    public WeChatException(String msg) {
        super(msg);
    }

    public WeChatException(Exception cause) {
        super(cause);
    }

    public WeChatException(String msg, Exception cause) {
        super(msg, cause);
    }
}
