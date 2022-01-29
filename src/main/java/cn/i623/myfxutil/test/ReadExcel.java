/*
package cn.i623.myfxutil.test;


import cn.i623.myfxutil.util.Print;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

import static cn.i623.myfxutil.util.ExcelUtils.readExcel;

*/
/**
 * @Auther: zjk
 * @Date: 2019/8/30
 * @Description:
 *//*

public class ReadExcel {
    public static void main(String[] args) {
        try {
//            Print.p("666666666");
            extracted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void extracted() throws IOException {
        //创建工作簿
        String pathname = "C:\\Users\\28442\\Desktop\\区号.xlsx";
        Workbook sheets = readExcel(pathname);
        Sheet sheet = sheets.getSheetAt(1); //sheet
//        Print.p("sheet",sheet.getRow());
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Print.p(row.getCell(0));
        }

    }
}*/
