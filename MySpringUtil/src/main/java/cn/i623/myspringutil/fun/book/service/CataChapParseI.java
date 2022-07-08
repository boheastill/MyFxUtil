package cn.i623.myspringutil.fun.book.service;

import cn.i623.myspringutil.fun.book.vo.Catalogue;
import cn.i623.myspringutil.fun.book.vo.Chapter;

import java.util.LinkedList;
import java.util.List;

public interface CataChapParseI {

    /**
     * 获取目录列表
     */
    List<Catalogue> getCataList(String url);

    /**
     * 获取完整目录
     */
    void getFullCata(String url, LinkedList<Catalogue> catalogue,String baseUrl);

    /**
     * 获取正文
     */
    Chapter getChapter(String url);

    String getBaseUrl();
    String getSimpUrl();
}
