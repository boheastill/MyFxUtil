package cn.i623.myspringutil.fun.book;

import cn.i623.myspringutil.fun.CodeGennerate;
import cn.i623.myspringutil.fun.book.vo.ChapterTitle;
import cn.i623.myspringutil.fun.book.vo.ProcessNodeEnum;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static cn.i623.myspringutil.fun.book.Catalogue.*;
import static cn.i623.myspringutil.fun.book.util.ChangeToPinYinJP.getPyName;
import static cn.i623.myspringutil.fun.book.util.IORecord.recordTime;

public class SearchIndex {
    public final static String SAVE_BASE_FILE = "D:\\down\\webGet\\";
    public final static String TRAIL = ".txt";
    public static List<String> TIME_RECORD = new LinkedList<>();
    public static String ignoreEndStr = "韩信的口信";


    public static void main(String[] args) throws IOException {
        BookAction("家父汉高祖");
    }

    public static void BookAction(String fileName) throws IOException {
        List<String> pyName = getPyName(fileName);//TODO 多音字校验
        String filePyName = pyName.get(0);
        BookAction("",filePyName);
    }

        public static void BookAction(String fileName,String filePyName) throws IOException {
//        List<String> pyName = getPyName(fileName);//TODO 多音字校验
//        String filePyName = pyName.get(0);
        findinit(filePyName);
        // 访问
        // 搜索页
        // 分页
        List<ChapterTitle> splitCataList = getSplitCataList(WEB_BASE_SITE + filePyName);
        // 目录
        LinkedList<ChapterTitle> chapterTitleList = new LinkedList();
        //TODO 异步
        for (ChapterTitle splitCata : splitCataList) {
            String url = WEB_BASE_SITE + splitCata.getPageUrl();
            chapterTitleList = saveCatalogue(url, chapterTitleList);
            recordTime(ProcessNodeEnum.CATA_END);
        }
        // 正文
        StringBuilder sb = eachPageSave(chapterTitleList, filePyName);
        findEnd(sb, filePyName);
    }


    private static void findEnd(StringBuilder sb, String filePyName) throws IOException {
        recordTime(ProcessNodeEnum.TASK_END);
        //保存报告文件
        String pathname = SAVE_BASE_FILE + filePyName + "_报告" + TRAIL;
        File file = new File(pathname);
        if (file.createNewFile()) {
            CodeGennerate.writejavaCode(file, TIME_RECORD, "问题：" + sb + "\n报告：\n", false);
        }
    }

    /*前置处理*/
    private static void findinit(String filePyName) {
        recordTime(ProcessNodeEnum.TASK_START);
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
