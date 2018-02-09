package com.weizidong.utils;

import com.alibaba.fastjson.JSON;
import com.weizidong.exception.WeChatException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具
 *
 * @author 魏自东
 * @date 2018/2/7 10:05
 */
public class HttpClientUtil {
    private static Logger logger = LogManager.getLogger(HttpClientUtil.class);
    private static final int SUCCESS_CODE = 200;

    /**
     * get请求
     *
     * @param url   请求地址
     * @param param 茶树
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:05
     */
    public static <T> T doGet(String url, Map<String, Object> param, Class<T> clazz) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString;
        CloseableHttpResponse response;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key) != null ? param.get(key).toString() : "");
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            debug(url, param == null ? "" : JSON.toJSONString(param), response.getStatusLine().getStatusCode(), resultString);
            if (response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                return JSON.parseObject(resultString, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpclient);
        }
        throw new WeChatException(url + "\t请求失败！");
    }

    /**
     * get请求
     *
     * @param url   请求地址
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:05
     */
    public static <T> T doGet(String url, Class<T> clazz) {
        return doGet(url, null, clazz);
    }

    /**
     * post表单请求
     *
     * @param url   请求地址
     * @param param 表单参数
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:08
     */
    public static <T> T doPost(String url, Map<String, Object> param, Class<T> clazz) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        String resultString;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key) != null ? param.get(key).toString() : null));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            debug(url, param == null ? "" : JSON.toJSONString(param), response.getStatusLine().getStatusCode(), resultString);
            if (response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                return JSON.parseObject(resultString, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
        throw new WeChatException(url + "\t请求失败！");
    }

    /**
     * post表单请求
     *
     * @param url   请求地址
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:08
     */
    public static <T> T doPost(String url, Class<T> clazz) {
        return doPost(url, null, clazz);
    }

    /**
     * post json请求
     *
     * @param url   请求地址
     * @param param 参数
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:09
     */
    public static <T> T doPostJson(String url, Object param, Class<T> clazz) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        String resultString;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            String json = param == null ? "" : JSON.toJSONString(param);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            debug(url, json, response.getStatusLine().getStatusCode(), resultString);
            if (response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                return JSON.parseObject(resultString, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
        throw new WeChatException(url + "\t请求失败！");
    }

    /**
     * post json 请求
     *
     * @param url   请求地址
     * @param clazz 返回类型
     * @author 魏自东
     * @date 2018/2/7 10:11
     */
    public static <T> T doPostJson(String url, Class<T> clazz) {
        return doPostJson(url, null, clazz);
    }

    /**
     * debug输出
     *
     * @param url    请求地址
     * @param param  参数
     * @param status 返回状态
     * @param result 返回结果
     * @author 魏自东
     * @date 2018/2/7 10:10
     */
    private static void debug(String url, String param, int status, String result) {
        if (WechatConfigs.isDebug()) {
            logger.debug("请求地址：" + url);
            logger.debug("请求参数：" + param);
            logger.debug("返回状态：" + status);
            logger.debug("返回结果：" + result);
        }
    }


}
