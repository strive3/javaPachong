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
public class demo01 {
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

        Elements elements = doc.getElementsByTag("title");   //获取tag是title的所有DOM元素
        Element element = elements.get(0);   //获取第一个元素
        String title = element.text();//返回元素的文本
        System.out.println("网页的标题是："+title);

        Element elementById = doc.getElementById("re_relate");   //获取id是re_relate的所有DOM元素
        String text = elementById.text();  //返回元素的文本
        System.out.println(text);

        Elements elementsByClass = doc.getElementsByClass("title"); //获取class是title的所有DOM元素
        for (Element e: elementsByClass){
            System.out.println(e.html());
            System.out.println("============================================");
        }
        //doc.getElementsByAttribute("");   根据属性名 查询DOM
//        doc.getElementsByAttributeValue("","");    根据属性名和属性值查找DOM
        //response 关闭
        response.close();
        //httpClient 关闭
        httpClient.close();
    }
}
