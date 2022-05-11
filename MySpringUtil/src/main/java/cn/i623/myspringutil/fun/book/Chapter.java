package cn.i623.myspringutil.fun.book;

import cn.i623.myspringutil.fun.book.vo.Page;
import cn.i623.myspringutil.fun.book.vo.ProcessNodeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.LinkedList;
import java.util.List;

import static cn.i623.myspringutil.fun.book.util.IORecord.recordTime;
import static cn.i623.myspringutil.fun.book.util.Web.getResult;

public class Chapter {
    /*获取单章内容*/
    public static Page getPage(String url) {
        Page page = new Page();
        Integer count = 0;
        List rawList = new LinkedList();

        String result = getResult(url);
        recordTime(ProcessNodeEnum.PRISE_START);
        // 使用 jsoup 解析
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
//        标题
        Node node1 = body.childNode(3);
        String s1 = node1.childNode(0).toString();
//        正文
        Node node2 = body.childNode(5);
        for (int i = 0; i < node2.childNodes().size() - 1; i++) {
            String s = node2.childNode(i).childNode(0).toString();
//            System.out.println(s);
            count += s.length();
            rawList.add(s);
        }
//        链接
        Node node3 = body.childNode(6);
//        String herfml = node3.childNode(2).attributes().get("href").toString();
        String herf = node3.childNode(4).attributes().get("href");
        //写入
        if (rawList.size() > 20 && count > 1000) {
            page.setNormal(true);
        }
        page.setNextHref(herf);
        page.setTitle(s1);
        page.setRawList(rawList);
        page.setCount(count);
        page.setRaw(rawList.size());
        return page;
    }
}
