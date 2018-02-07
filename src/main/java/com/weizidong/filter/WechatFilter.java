package com.weizidong.filter;

import com.alibaba.fastjson.JSON;
import com.weizidong.base.MsgType;
import com.weizidong.message.handler.IEventHandler;
import com.weizidong.message.handler.IMessageHandler;
import com.weizidong.message.input.base.BaseMessage;
import com.weizidong.message.input.base.InputMessage;
import com.weizidong.message.output.DefaultOutputMessage;
import com.weizidong.message.output.base.OutputMessage;
import com.weizidong.utils.SignatureUtil;
import com.weizidong.utils.WechatConfigs;
import com.weizidong.utils.XStreamFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

/**
 * 微信公众平台接受消息默认拦截器
 *
 * @author 魏自东
 * @date 2018/2/7 15:09
 */
public class WechatFilter implements Filter {
    private static Logger logger = LogManager.getLogger(WechatFilter.class);
    /**
     * 事件处理器
     */
    @Autowired
    private IEventHandler eventHandler;
    /**
     * 消息处理器
     */
    @Autowired
    private IMessageHandler messageHandler;

    @Override
    public void init(FilterConfig config) throws ServletException {
        if (WechatConfigs.isDebug()) {
            logger.debug("WechatFilter微信消息拦截器启动成功!");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // signature=7355abeb1e23701ced555d913794011c44a9b35b&echostr=6190563798851665524&timestamp=1504848932&nonce=3181320051
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        if (WechatConfigs.isDebug()) {
            logger.debug("------------------------获取到微信请求-------------------------");
            logger.debug("微信请求URL:" + request.getServletPath());
            logger.debug("微信请求方式:" + request.getMethod());
        }
        // 如果是get请求,校验来源
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            // wechat-sdk.properties中配置的Token
            String token = WechatConfigs.getProperty("wechat.token");
            if (WechatConfigs.isDebug()) {
                logger.debug("Query参数:" + request.getQueryString());
                logger.debug("token:" + token);
            }
            if (SignatureUtil.checkSignature(token, signature, timestamp, nonce)) {
                logger.debug("微信签名验证通过!");
                response.getWriter().write(request.getParameter("echostr"));
            } else {
                logger.debug("微信签名验证失败!");
                response.getWriter().write("");
            }
            logger.debug("-------------------------微信请求完成------------------------------");
        }
        // 如果是post请求,处理消息
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            doPost(request, response);
        }
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        // 获取输入流
        ServletInputStream in = request.getInputStream();
        try {
            // 处理输入消息，返回结果
            String xmlMsg = XStreamFactory.inputStream2String(in);
            if (WechatConfigs.isDebug()) {
                logger.debug("获取到的的xml：" + xmlMsg);
            }
            JAXBContext context = JAXBContext.newInstance(InputMessage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputMessage inputMsg = (InputMessage) unmarshaller.unmarshal(new StringReader(xmlMsg));
            if (WechatConfigs.isDebug()) {
                logger.debug("转换后的实体：" + inputMsg);
            }
            OutputMessage msg = pushMsg(inputMsg);
            if (msg == null) {
                msg = new DefaultOutputMessage();
            } else {
                setOutputMsgInfo(msg, inputMsg);
            }
            String outXml = msg.toXML();
            if (WechatConfigs.isDebug()) {
                logger.debug("返回的消息：" + JSON.toJSONString(msg));
                logger.debug("返回的XML：" + outXml);
                logger.debug("-------------------------微信请求完成------------------------------");
            }
            // 返回结果
            response.getWriter().write(outXml);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().write("");
        }
    }

    /**
     * 微信推送的消息分发
     */
    public OutputMessage pushMsg(InputMessage msg) {
        switch (msg.getMsgType().toLowerCase()) {
            case MsgType.TEXT:
                return messageHandler.doText(msg.toTextInputMessage());
            case MsgType.IMAGE:
                return messageHandler.doImage(msg.toImageInputMessage());
            case MsgType.VOICE:
                return messageHandler.doVoice(msg.toVoiceInputMessage());
            case MsgType.VIDEO:
                return messageHandler.doVideo(msg.toVideoInputMessage());
            case MsgType.SHORTVIDEO:
                return messageHandler.doShortvideo(msg.toShortVideoInputMessage());
            case MsgType.LOCATION:
                return messageHandler.doLocation(msg.toLocationInputMessage());
            case MsgType.LINK:
                return messageHandler.doLink(msg.toLinkInputMessage());
            case MsgType.EVENT:
                return pushEvent(msg);
            default:
                return null;
        }
    }

    /**
     * 处理事件
     */
    public OutputMessage pushEvent(InputMessage msg) {
        switch (msg.getEvent().toUpperCase()) {
            case MsgType.Event.SUBSCRIBE:
                return eventHandler.subscribe(msg.toSubscribeEventMessage());
            case MsgType.Event.UNSUBSCRIBE:
                return eventHandler.unSubscribe(msg.toUnSubscribeEventMessage());
            case MsgType.Event.SCAN:
                return eventHandler.qrsceneScan(msg.toQrsceneScanEventMessage());
            case MsgType.Event.LOCATION:
                return eventHandler.location(msg.toLocationEventMessage());
            case MsgType.Event.CLICK:
                return eventHandler.click(msg.toClickEventMessage());
            case MsgType.Event.VIEW:
                return eventHandler.view(msg.toViewEventMessage());
            default:
                return null;
        }
    }

    private static void setOutputMsgInfo(OutputMessage oms, BaseMessage msg) throws Exception {
        // 设置发送信息
        oms.setCreateTime(System.currentTimeMillis());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());
    }

    @Override
    public void destroy() {
    }

    public IEventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(IEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public IMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(IMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

}
