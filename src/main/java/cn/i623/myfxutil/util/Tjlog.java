package cn.i623.myfxutil.util;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Tjlog {
    public static void main(String[] args) throws IOException {
        String path = "D:\\tlpdf\\tjlt1.out";
        String outPath = "D:\\tlpdf\\tjlt1_deal.out";
        String startText = "POST http://wg.zj.tj.jcy/iop-ocr/api/ocr/tyyw/catalogue HTTP/1.1";
        String startText2 = "POST http://wg.zj.tj.jcy/iop-ocr/api/ocr/tyyw/receive-ocr-pdf HTTP/1.1";
        String endText = "END HTTP";
        String starWith = "2021-12-16";
        String containRow = "get request...submit";

        Charset inputCharset = StandardCharsets.UTF_8;
        exeM(path, outPath, starWith, inputCharset, startText, startText2, endText, containRow);
    }

    public static void exeM(String path, String outPath, String partFirstText, Charset inputCharset
            , String startText, String startText2, String endText, String containRow) {
        String logText = null;
        try {
            logText = readDealFile(path, inputCharset, partFirstText, startText, startText2, endText, containRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(logText);
        try (Writer writer = new FileWriter(outPath, StandardCharsets.UTF_8)) {
            writer.write(logText); // 写入String
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readDealFile(String fileName, Charset inputCharset, String starWith
            , String startText, String startText2, String endText, String containRow) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, inputCharset))) {

            String line;
            StringBuilder lines = new StringBuilder();

            Boolean saveNextRow = false;
            while ((line = reader.readLine()) != null) {
                //只要指定范围的行
                if (line.indexOf(starWith) == -1) {
                    continue;
                }
//             get request...submit 有值时保留行
                if (line.indexOf(containRow) != -1) {
                    lines.append(line);
                    continue;
                }

                //得到null 代表处理过了
                saveNextRow = saveStartEnd(line, lines, saveNextRow, startText, startText2, endText);
            }
            String resultText = lines.toString();
//            Print.p(resultText);
            return resultText;

        }
    }

    /*saveNextRow t 保存下一行
     * f 不保存后续‘

     * */
    private static Boolean saveStartEnd(String line, StringBuilder lines, Boolean saveNextRow, String startText, String startText2, String endText) {
        line = line + "\n";
        if (line.indexOf(startText) != -1 || line.indexOf(startText2) != -1) {
            saveNextRow = true;
            lines.append(line);
            return saveNextRow;
        }
//                只保存有开头的结尾
        if (saveNextRow && line.indexOf(endText) != -1) {
            saveNextRow = false;
            lines.append(line);
            return saveNextRow;
        }
//                保存中间
        if (saveNextRow) {
            saveNextRow = true;
            lines.append(line);
            return saveNextRow;
        }
        return saveNextRow;
    }


}
