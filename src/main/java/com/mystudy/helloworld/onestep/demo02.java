package com.mystudy.helloworld.onestep;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 杜晓鹏
 * @create 2019-03-12 19:43
 */
public class demo02 {
    public static void main(String[] args) throws IOException {
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例    -->要爬虫的网站
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/");

        //设置User-Agent    模仿浏览器发送
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        //使用动态代理  ip
        HttpHost proxy = new HttpHost("60.2.44.182", 30963);
        RequestConfig config = RequestConfig.custom().setProxy(proxy)
                .setConnectTimeout(10000)   //设置连接超时时间10秒
                .setSocketTimeout(10000)  //设置读取超时时间10秒   超过10秒会报异常
                .build();
        httpGet.setConfig(config);
        //执行http  get请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取响应状态码
        System.out.println(response.getStatusLine());
        //获取返回实体
        HttpEntity entity = response.getEntity();
        System.out.println("content-type:"+entity.getContentType().getValue());;
        //获取网页内容
        //System.out.println(EntityUtils.toString(entity, "utf-8"));
        //response 关闭
        response.close();
        //httpClient 关闭
        httpClient.close();
    }
}