package cn.i623.myspringutil;


import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*传入目录页，保存该书
*
* //todo 找到目录，按目录逐章保存，最后io
*
* */
public class GetResponse {
    //-参数-------------------------
    private final static String WEB_SITE = "https://m.soxs.cc";
    private final static String CATA_URL = "/WoHeChongZhenChengLiaoHeHuoRen";
    private final static String SAVE_BASE_FILE = "D:\\down\\webGet\\";
    private final static String FILE_NAME = "测试1";
    private final static String TRAIL = ".txt";
    private static List<String> timeList = new LinkedList<>();
    private static List<AloneTitle> catalogue;


    //-测试-------------------------
    public static void main(String[] arg) throws IOException {
        uBookIsMine();
        System.out.println("执行完毕");
    }


    //-公有-------------------------
    public static void uBookIsMine() throws IOException {
        recordTime(ProcessNodeEnum.TASK_START);
        List<AloneTitle> catalogue = getCatalogue();
        StringBuilder sb = new StringBuilder();
        for (AloneTitle at : catalogue) {
            recordTime(ProcessNodeEnum.PAGE_START);
            Page page = getPage(WEB_SITE, at.getPageUrl());
            if (!page.getNormal()) {
                sb.append(page + "\n");
            }
            recordTime(ProcessNodeEnum.SAVE_START);
            save2File(page);
        }
        recordTime(ProcessNodeEnum.TASK_END);
//        System.out.println("问题：" + sb);
        //保存报告文件
        String pathname = SAVE_BASE_FILE + FILE_NAME + "_报告" + TRAIL;
        File file = new File(pathname);
        if (file.createNewFile()) {
            Util.writejavaCode(file, timeList, "问题：" + sb + "\n报告：\n", false);
        }
    }

    /*获取单章内容*/
    public static Page getPage(String website, String pageurl) {
        Page page = new Page();
        Integer count = 0;
        List rawList = new LinkedList();
        String url = website + pageurl;

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
        String herf = node3.childNode(4).attributes().get("href").toString();
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

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
//            System.out.println("读取响应：" + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /*获取完整目录*/
    public static List<AloneTitle> getCatalogue() {
        List<AloneTitle> allSplitCate = getAllSplitCate(WEB_SITE, CATA_URL);
        for (AloneTitle splitCate : allSplitCate) {
            String cataUrl = splitCate.getPageUrl();
            addCatalogue(WEB_SITE, cataUrl);
        }
        return catalogue;
    }

    /*
     * 传入：流程节点,时间（指定）
     * **/
    public static void recordTime(ProcessNodeEnum processNode) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String nowTime = df.format(LocalDateTime.now());
//        timeMap.put(processNode, nowTime);
        timeList.add(processNode.getName() + "\t" + nowTime);
    }

    //-私有-------------------------
    private static void save2File(Page page) throws IOException {
        String pathname = SAVE_BASE_FILE + FILE_NAME + ".txt";
//        String pathname = BASE_FILE + page.getTitle() + ".txt";
        File file = new File(pathname);
        if (file.exists()) {
//            System.out.println("    存在");
            Util.writejavaCode(file, page.getRawList(), page.getTitle(), true);
        } else {
            System.out.println("    X");
            if (file.createNewFile()) {
                Util.writejavaCode(file, page.getRawList(), page.getTitle(), false);
            }
        }
    }

    /*补充目录*/
    private static List<AloneTitle> addCatalogue(String website, String pageurl) {
        if (catalogue == null) {
            catalogue = new LinkedList();
        }
        String url = website + pageurl;
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
                AloneTitle aloneTitle = new AloneTitle();
                aloneTitle.setPageUrl(href);
                aloneTitle.setName(node3.toString());
                catalogue.add(aloneTitle);
            }
        }
        return catalogue;
    }

    /*获取目录的所有分页*/
    private static List<AloneTitle> getAllSplitCate(String website, String pageurl) {
        List<AloneTitle> rawList = new LinkedList();
        String url = website + pageurl;
        String result = getResult(url);
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        Node node1 = body.childNode(1);
        Node node = node1.childNode(12).childNode(2).childNode(1);
//        正文
        for (int i = 0; i < node.childNodes().size(); i++) {
            Node node2 = node.childNode(i);
//            System.out.println(node2);
            Node node3 = node2.childNode(0);
            String href = node2.attributes().get("value");
            AloneTitle aloneTitle = new AloneTitle();
            aloneTitle.setPageUrl(href);
            aloneTitle.setName(node3.toString());
            rawList.add(aloneTitle);
        }
        return rawList;
    }

    private static String getResult(String url) {
        String result = "";
        int i = 0;
        //如果返回长度0，重试10次 todo 可以加时间戳来避免缓存
        do {
            result = sendGet(url, "");
            i++;
        } while (result.length() == 0 || i < 10);
        return result;
    }

    public enum ProcessNodeEnum {
        TASK_START("任务开始"), TASK_END("任务结束"),
        DOWNLOAD_START("下载开始"), DOWNLOAD_END("下载结束"),
        PRISE_START("解析处理开始"), PRISE_END("解析处理结束"),
        TEST1("测试节点1"), TEST2("测试节点2"),
        PAGE_START("章节开始"), PAGE_END("章节结束"),
        SAVE_START("保存开始"), SAVE_END("保存结束");
//        _START("开始"), _END("结束"),

        private final String name;

        private ProcessNodeEnum(String name) {
            this.name = name;

        }

        public String getName() {
            return name;
        }

    }

    //-对象-------------------------
    @Data
    static class Page {
        List<String> rawList;
        String title;
        String nextHref;
        Boolean normal = false;
        Integer count;
        Integer Raw;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"rawList\":").append(rawList);
            sb.append(",\"title\":\"").append(title).append('\"');
            sb.append(",\"nextHref\":\"").append(nextHref).append('\"');
            sb.append(",\"normal\":").append(normal);
            sb.append(",\"count\":").append(count);
            sb.append(",\"Raw\":").append(Raw);
            sb.append('}');
            return sb.toString();
        }
    }

    @Data

    static class AloneTitle {
        String name;
        String pageUrl;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"").append(name).append('\"');
            sb.append(",\"cataUrl\":\"").append(pageUrl).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }

}