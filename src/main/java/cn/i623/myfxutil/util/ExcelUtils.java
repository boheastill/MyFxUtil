/*
package cn.i623.myfxutil.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;


//Excel导入导出工具类
public class ExcelUtils {

    //	public static XSSFWorkbook createWorkBook(){
    //
    //	}
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();

    static {
        numberFormat.setGroupingUsed(false);
    }

    */
/**
     * 读取给定行列格子的值
     *
     * @param wb       读取excel的文档对象
     * @param column   列序号
     * @param rowIndex 行序号
     * @return 读取到的值
     *//*

    public static String getColumn(Workbook wb, int column, int rowIndex) {
        //Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        Row row = sheet.getRow(rowIndex);
        //        log.info(">>>>>>>>rowIndex:{}",rowIndex);
        if (row != null && row.getCell(column) != null) {
            String cellData = (String) getCellFormatValue(row.getCell(column));
            if (!StringUtils.isEmpty(cellData)) {
                return cellData.replaceAll(" ", "");
            }
        } else {
            //            if (row==null){
            //                log.warn("row is null");
            //            }else {
            //                log.warn("column of row is null.rowIndex is {}, column is {}",rowIndex,column);
            //            }
        }
        return "";
    }

    */
/**
     * 读取给定值，给定列上行的序号
     *
     * @param wb     读取excel的文档对象
     * @param column 列序号
     * @param text   文本
     * @return 读取到的序号
     *//*

    public static int getRowIndex(Workbook wb, int column, String text) {
        //Workbook wb = readExcel(filePath); //文件
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0); //sheet
            for (Row row : sheet) {
                Cell cell = row.getCell(column - 1);
                String cellData = (String) getCellFormatValue(cell);
//                Log.info("###### >>>>>>>>> cell data is " + cellData + ", need text is " + text + ", " + text.equals(cellData));
                if (!StringUtils.isEmpty(cellData)) {
                    if (cellData.equals(text)) {
                        return cell.getRowIndex();
                    }
                }
            }
        }
        return -1;
    }

    */
/**
     * @param wb       读取excel的文档对象
     * @param rowIndex 行序号
     * @param text     文本
     * @return 读取到的列序号
     *//*

    public static int getColumnIndex(Workbook wb, int rowIndex, String text) {
        //Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        Row row = sheet.getRow(rowIndex - 1);
        for (Cell cell : row) {
            String cellData = (String) getCellFormatValue(cell);
            if (!StringUtils.isEmpty(cellData) && cellData.equals(text)) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }

    private static String getColumnSet(Workbook wb, int column, int startRow, int endRow) {
        //Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        Row row = null;
        String result = "";
        String cellData = null;
        for (int i = startRow - 1; i < endRow; i++) {
            System.out.println(i);
            row = sheet.getRow(i);
            if (row != null) {
                cellData = (String) getCellFormatValue(row.getCell(column - 1));
                result = cellData.replaceAll(" ", "");
                break;
            }
            //System.out.println(cellData);
        }
        return result;
    }

    */
/**
     * @param wb       需要读取的文件excel对象
     * @param column   指定需要获取的列数，例如第一列 1
     * @param startRow 指定从第几行开始读取数据
     * @return 返回读取列数据的set
     *//*

    public static String getColumnSet(Workbook wb, int column, int startRow) {
        //Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        int rownum = sheet.getPhysicalNumberOfRows(); //行数
        System.out.println("sumrows " + rownum);

        return getColumnSet(wb, column, startRow, rownum - 1);
    }

    */
/**
     * @param filePath 需要读取的文件路径
     * @return 返回的excel对象
     *//*

    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
//        Print.p("extString",extString);
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    */
/*
     *
     * Stage0 部分
     * Hex0：起点，Binary 不超过 1KB，用来从带注释的 hex dump 生成 binary。
     * Hex1、Hex2：Hex0 + 偏移量计算，用来简化后续过程；每一级由上一级实现和编译。
     * M0：宏汇编器，由 Hex2 实现和编译。cc_*：用汇编写的 C 子集编译器（支持 x86、x644、ARMv7 和 v8）；由 M0 编译。M2-Planet + mescc-tools：用先前的 C 子集制作的用于生成 Mes 的基础工具。Mes 部分Mes + MesCC → 一个修改过的 TCC → GCC 4.7 → 现代的 GCC

     * *//*


    */
/**
     * @param cell excel格子对象
     * @return 读到的值
     *//*

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    double d = cell.getNumericCellValue();
                    Object value = numberFormat.format(d);
                    cellValue = (value == null ? "" : value.toString());
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    public static void outputExcelByWorkbook(XSSFWorkbook workbook, OutputStream outputStream) {
        if (workbook != null && outputStream != null) {
            try {
                workbook.write(outputStream);
                outputStream.flush();

                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    */
/**
     * 读取Office 2007 excel
     *//*

    private static List<List<Object>> read2007Excel(InputStream stream) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook(stream);
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;

        for (int i = sheet.getFirstRowNum(); i <= sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                    continue;
                }
                value = cell.toString();

                if (value == null || "".equals(value)) {
                    linked.add("");
                    continue;
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }


}
*/
