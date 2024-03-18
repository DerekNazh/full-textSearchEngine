package com.strategyopr.core;

import com.strategyopr.Bean.BookBean;
import com.strategyopr.util.FileUtils;
import com.strategyopr.util.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;

public class doubanDemo {
    public static void main(String[] args) {
        int bookid = 0;
        String url = null;
        ArrayList Data = null;

        for (int i = 3660000; i < 4000000; i++) {
            try { url = "https://book.douban.com/subject/" + i;
                bookid = getBookId(url);
                BookBean bookBean = new BookBean(bookid, getBookName(url),
                    getInfo(url), getScore(url), getDescription(url),
                    getComments(url));
                 FileUtils.downToFile(bookBean.toString()+"\r\n","E:\\work\\work\\test\\data\\bookdata.csv");
                System.out.println("豆瓣页面bookid:"+bookid+"，已爬取");
        }catch (Exception e){

            }
        }
    }
    public static void downloadData(){}
    public static ArrayList<String> getComments(String url){
        ArrayList<String> comments = null;
        String html = HtmlUtils.getHtmlFromURL(url);
        Document doc = Jsoup.parse(html);
        comments =new ArrayList<String>();
        Elements cli = doc.select("li.comment-item");
        for (Element element : cli) {
            comments.add(element.text());
        }
        return comments;
    }
    public static int getBookId(String url){
        String[] urlarr = url.split("/");
        String bookid = urlarr[urlarr.length - 1];
        return Integer.parseInt(bookid);
    }
    public static String getBookName(String url){
        String html = HtmlUtils.getHtmlFromURL(url);
        Document doc = Jsoup.parse(html);
        Elements bookName = doc.select("span[property=v:itemreviewed]");
        String bookname = bookName.first().text();
        return bookname;
    }
    public static String getDescription(String url){
        String html = HtmlUtils.getHtmlFromURL(url);
        Document doc = Jsoup.parse(html);
        Elements select = doc.select("div[class=intro]");
        String description = select.text();

        return description;
    }
    public static String getInfo(String url){
        String html = HtmlUtils.getHtmlFromURL(url);
        Document doc = Jsoup.parse(html);
        Elements all = doc.select("div[id=info]");
        return all.text();
    }
    public static Double getScore(String url){
        String html = HtmlUtils.getHtmlFromURL(url);
        Document doc = Jsoup.parse(html);
        Element scoreElement = doc.select("strong.ll.rating_num[property=v:average]").first();
        return  Double.parseDouble(scoreElement.text());
    }
}
