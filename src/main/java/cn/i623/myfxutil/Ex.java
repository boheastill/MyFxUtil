package cn.i623.myfxutil;


import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ex implements Serializable {
    public static List<XsEleAddInfo> getExcalInfo() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook("C:\\Users\\28442\\Desktop\\A.xlsx");
        System.out.println(sheets);
        List<XsEleAddInfo> objects = new ArrayList<>();
        for (Sheet sheet : sheets) {
            /*24567 -*/
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && i > 2 && row.getCell(2) != null && (!"".equals(row.getCell(2).toString().trim()))) {
                    Cell nodeCode = row.getCell(2);
                    Cell eleName = row.getCell(5);
                    Cell eleCode = row.getCell(4);
                    Cell group = row.getCell(6);
                    Cell parentGroup = row.getCell(7);
                    XsEleAddInfo xsEleAddInfo = new XsEleAddInfo();
                    xsEleAddInfo.setEleCode(eleCode.toString());
                    xsEleAddInfo.setEleName(eleName.toString());
                    xsEleAddInfo.setNodeCode(nodeCode.toString());
                    xsEleAddInfo.setGroup(group == null ? null : group.toString());
                    xsEleAddInfo.setParentGroup(parentGroup == null ? null : parentGroup.toString());
                    objects.add(xsEleAddInfo);
                }
            }
        }
        return objects;
    }

    @Data
    public static class XsEleAddInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        String nodeCode;
        String eleName;
        String eleCode;
        String group;
        String parentGroup;
    }
}
