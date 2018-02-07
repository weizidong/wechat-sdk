/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weizidong.message.handler;

import com.weizidong.message.input.*;
import com.weizidong.message.output.base.OutputMessage;

/**
 * 接受消息推送
 *
 * @author WeiZiDong
 */
public interface IMessageHandler {

    /**
     * 文字内容的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doText(TextInputMessage msg);

    /**
     * 图片类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doImage(ImageInputMessage msg);

    /**
     * 语音类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doVoice(VoiceInputMessage msg);

    /**
     * 视频类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doVideo(VideoInputMessage msg);

    /**
     * 小视频类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doShortvideo(ShortVideoInputMessage msg);

    /**
     * 地理位置类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doLocation(LocationInputMessage msg);

    /**
     * 链接类型的消息处理
     *
     * @param msg 接受消息对象
     * @return 输出消息对象
     */
    OutputMessage doLink(LinkInputMessage msg);
}
