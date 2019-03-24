package com.mystudy.helloworld.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author 杜晓鹏
 * @create 2019-03-12 19:43
 */
public class demo03 {
    public static void main(String[] args) throws IOException {
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例    -->要爬虫的网站
        HttpGet httpGet = new HttpGet("http://max.book118.com/html/2017/0812/127561983.shtm");

        //设置User-Agent    模仿浏览器发送
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        //执行http  get请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取返回实体
        HttpEntity entity = response.getEntity();
        //获取网页内容
        String content = EntityUtils.toString(entity, "utf-8");
        Document doc = Jsoup.parse(content);   //解析网页得到文档对象



        Elements elements1 = doc.select("a[href]");   //带有href的a标签
        for (Element e: elements1){
            System.out.println(e.toString());
            System.out.println("=============");
            System.out.println(e.attr("href"));
        }

        //response 关闭
        response.close();
        //httpClient 关闭
        httpClient.close();
    }
}
