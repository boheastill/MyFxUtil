package cn.i623.myspringutil.fun.book;


import cn.i623.myspringutil.fun.book.vo.ChapterTitle;
import cn.i623.myspringutil.fun.book.vo.Page;
import cn.i623.myspringutil.fun.book.vo.ProcessNodeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static cn.i623.myspringutil.fun.book.Chapter.getPage;
import static cn.i623.myspringutil.fun.book.util.IORecord.recordTime;
import static cn.i623.myspringutil.fun.book.util.IORecord.save2File;
import static cn.i623.myspringutil.fun.book.util.Web.getResult;

public class Catalogue {

    public final static String WEB_BASE_SITE = "https://m.soxs.cc/";
    public static Boolean ignore = true;
    public static String ignoreEndStr = "我怎么做起小说来";

    /*

            for (WebResponse2TXT.ChapterTitle chapterTitle : chapterTitleList) {
            System.out.println("chapterTitle:" + chapterTitle.getName());
            if (chapterTitle.getName().indexOf(ignoreEndSte) != -1) {
                ignore = false;
            }
            if (ignore) {
                continue;
            }

    *
    * */
    //TODO 一次保存
    public static StringBuilder eachPageSave(List<ChapterTitle> chapterTitleList, String filePyName) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (ChapterTitle chapterTitle : chapterTitleList) {
            recordTime(ProcessNodeEnum.PAGE_START);
            if (chapterTitle.getName().indexOf(ignoreEndStr) != -1) {
                System.out.println("搜索成功");
                ignore = false;
            }
            if (ignore) {
                System.out.println("跳过:" + chapterTitle.getName());
                continue;
            }
            System.out.println("保存:" + chapterTitle.getName());
            //记录问题页
            Page page = getPage(WEB_BASE_SITE + chapterTitle.getPageUrl());
            if (!page.getNormal()) {
                sb.append(page + "\n");
            }
            recordTime(ProcessNodeEnum.SAVE_START);
            save2File(page, filePyName);
        }
        return sb;
    }


    /*补充目录*/
    public static LinkedList<ChapterTitle> saveCatalogue(String url, LinkedList catalogue) {

        String result = getResult(url);
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        Node node1 = body.childNode(1);
        Node node = node1.childNode(10);
//        System.out.println(body);
//        正文
        for (int i = 0; i < node.childNodes().size(); i++) {
            Node node2 = node.childNode(i);
            if (!"".equals(node2.toString().trim())) {
                Node node3 = node2.childNode(0).childNode(0);
                String href = node2.childNode(0).attributes().get("href");
                ChapterTitle chapterTitle = new ChapterTitle();
                chapterTitle.setPageUrl(href);
                chapterTitle.setName(node3.toString());
                catalogue.add(chapterTitle);
            }
        }
        return catalogue;
    }
//    aim wlb,gtb 7 1h
//    学习以检验为真理，个人时间第一优先级。
//    外出以英语和参与为主要目标
//    工作以下次要求为本次目标

    /*获取目录的所有分页*/
    static List<ChapterTitle> getSplitCataList(String url) {
        List<ChapterTitle> rawList = new LinkedList();

//        System.out.println(url);
        String result = getResult(url);
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        Node node1 = body.childNode(1);
//        System.out.println(node1);
        Node node = node1.childNode(12).childNode(2).childNode(1);
//        正文
        for (int i = 0; i < node.childNodes().size(); i++) {
            Node node2 = node.childNode(i);
//            System.out.println(node2);
            Node node3 = node2.childNode(0);
            String href = node2.attributes().get("value");
            ChapterTitle chapterTitle = new ChapterTitle();
            chapterTitle.setPageUrl(href);
            chapterTitle.setName(node3.toString());
            rawList.add(chapterTitle);
        }
        return rawList;
    }
}
