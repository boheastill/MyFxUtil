package cn.i623.myspringutil.fun.book;

import cn.i623.myspringutil.fun.CodeGennerate;
import cn.i623.myspringutil.fun.book.service.CataChapParseI;
import cn.i623.myspringutil.fun.book.service.PCA;
import cn.i623.myspringutil.fun.book.vo.Catalogue;
import cn.i623.myspringutil.fun.book.vo.Chapter;
import cn.i623.myspringutil.fun.book.vo.ProcessStatu;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static cn.i623.myspringutil.fun.book.util.ChangeToPinYinJP.getPyName;
import static cn.i623.myspringutil.fun.book.util.IORecord.recordTime;
import static cn.i623.myspringutil.fun.book.util.IORecord.save2File;

public class BookDownAPP {
    public static final String SAVE_BASE_FILE = "D:\\down\\webGet\\";
    public static final String TRAIL = ".txt";
    public static List<String> TIME_RECORD = new LinkedList<>();
    public String ignoreEndStr = "";

    CataChapParseI cataChapParseI;

    public void main(String[] args) throws IOException {
        cataChapParseI = new PCA();
        BookAction("神王权杖");
    }

    //    public final  String WEB_BASE_SITE = "https://m.soxs.cc/";
//    public final  String WEB_BASE_SITE = "https://m.23xstxt.com/";
    public final String WEB_BASE_SITE = "https://www.qu-la.com";
    public Boolean ignore = true;

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
    public StringBuilder eachPageSave(List<Catalogue> catalogueList, String filePyName) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Catalogue catalogue : catalogueList) {
            recordTime(ProcessStatu.PAGE_START);
            if ("".equals(ignoreEndStr) || catalogue.getName().contains(ignoreEndStr)) {
                System.out.println("匹配到:" + catalogue.getName());
                ignore = false;
            }
            if (ignore) {
                System.out.println("跳过:" + catalogue.getName());
                continue;
            }
            System.out.println("保存:" + catalogue.getName());
            //保存单页逻辑
            Chapter chapter = cataChapParseI.getChapter(WEB_BASE_SITE + catalogue.getPageUrl());
            //记录问题页
            if (!chapter.getNormal()) {
                sb.append(chapter).append("\n");
            }
            recordTime(ProcessStatu.SAVE_START);
            save2File(chapter, filePyName);
        }
        return sb;
    }

    public void BookAction(String fileName) throws IOException {
        List<String> pyName = getPyName(fileName);//TODO 多音字校验
        String filePyName = pyName.get(0);
        BookAction("", filePyName);
    }

    public void BookAction(String fileName, String filePyName) throws IOException {
//        List<String> pyName = getPyName(fileName);//TODO 多音字校验
//        String filePyName = pyName.get(0);
        findinit(filePyName);
        // 获得目录列表
        List<Catalogue> splitCataList = cataChapParseI.getCataList(WEB_BASE_SITE + filePyName);
        // 获得完整目录
        LinkedList<Catalogue> catalogueList = new LinkedList<>();
        //TODO 异步
        for (Catalogue splitCata : splitCataList) {
            String url = WEB_BASE_SITE + splitCata.getPageUrl();
//            System.out.println(chapterTitleList);
            cataChapParseI.getFullCata(url, catalogueList);
            recordTime(ProcessStatu.CATA_END);
        }
        // 正文
        StringBuilder sb = eachPageSave(catalogueList, filePyName);
        findEnd(sb, filePyName);
    }


    private void findEnd(StringBuilder sb, String filePyName) throws IOException {
        recordTime(ProcessStatu.TASK_END);
        //保存报告文件
        String pathname = SAVE_BASE_FILE + filePyName + "_报告" + TRAIL;
        File file = new File(pathname);
        if (file.createNewFile()) {
            CodeGennerate.writejavaCode(file, TIME_RECORD, "问题：" + sb + "\n报告：\n", false);
        }
    }

    /*前置处理*/
    private void findinit(String filePyName) {
        recordTime(ProcessStatu.TASK_START);
        File oldFile = new File(SAVE_BASE_FILE + filePyName + TRAIL);
        if (oldFile.exists()) {
            boolean delete = oldFile.delete();
            System.out.println("delete" + delete);
        }
        File oldFileT = new File(SAVE_BASE_FILE + filePyName + "_报告" + TRAIL);
        if (oldFileT.exists()) {
            boolean delete = oldFileT.delete();
            System.out.println("delete" + delete);
        }
    }
}
