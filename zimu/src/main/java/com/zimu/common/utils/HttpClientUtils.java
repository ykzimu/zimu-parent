package com.zimu.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * httpPost
     *
     * @param url       路径
     * @param jsonParam 参数
     * @return JSONObject
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return JSONObject
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient = null;

        try {
            httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);


            if (jsonParam != null) {
                List<NameValuePair> list = new LinkedList<>();
                for (Map.Entry<String, Object> entry : jsonParam.entrySet()) {
                    BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    list.add(nameValuePair);
                }
                // 使用URL实体转换工具
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, DEFAULT_CHARSET);
                entity.setContentEncoding(DEFAULT_CHARSET);
                entity.setContentType("application/json");
                request.setEntity(entity);
            }

            HttpResponse response = httpClient.execute(request);

            //请求发送失败
            if (response == null || response.getStatusLine() == null
                || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("post请求提交失败:" + url);
                return null;
            }

            //读取服务器返回过来的json字符串数据
            String str = EntityUtils.toString(response.getEntity());
            if (noNeedResponse) {
                return null;
            }
            // 把json字符串转换成json对象
            jsonResult = JSONObject.parseObject(str);

        } catch (IOException e) {
            log.error("post请求提交失败:" + url, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("关闭httpClient失败！", e);
                }
            }
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return JSONObject
     */
    public static JSONObject httpGet(String url, Map<String, String> map) {

        // 缓存信息
        StringBuilder params = new StringBuilder();

        // 拼接获取参数
        if (map != null) {
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (StringUtils.isBlank(entry.getKey()) || StringUtils.isBlank(entry.getValue())) {
                    continue;
                }

                i++;
                if (i == 1) {
                    params.append("?");
                } else {
                    params.append("&");
                }
                params.append(entry.getKey());
                params.append("=");
                params.append(entry.getValue());

            }
        }
        return httpGet(url + params.toString());
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {

        // get请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient = null;
        try {

            httpClient = HttpClients.createDefault();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);

            //请求发送失败
            if (response == null || response.getStatusLine() == null
                || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("get请求提交失败:" + url);
                return null;
            }

            //读取服务器返回过来的json字符串数据
            String strResult = EntityUtils.toString(response.getEntity());
            //把json字符串转换成json对象
            jsonResult = JSONObject.parseObject(strResult);

        } catch (Exception e) {
            log.error("get请求提交失败:" + url, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("关闭httpClient失败！", e);
                }
            }
        }
        return jsonResult;
    }

    public static void main(String[] argv) {

        String url = "http://dev.jfh.com/ysapi/ys/v1/pro/getTypeList";

        JSONObject jsonObject = httpGet(url);
        System.out.println(jsonObject.toString());

    }

}
