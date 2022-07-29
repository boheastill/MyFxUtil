package cn.i623.myspringutil.service;

import cn.i623.myspringutil.domain.XsElement;
import cn.i623.myspringutil.fun.CodeGennerate;
import cn.i623.myspringutil.mapper.XsElementMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class XsElementService {

    /*
     *  * //    路径：
     * ////    D:\down\pna\tlbook\tlproject\lsw\TianJ_OCR\src\main\java\com\turing\engine\scripts\node0101C50001\filter
     * //
     * //    文件名：maoname_elesn_tempsn_filter.groovy
     * //    包名：tempsn_temp表te_package
     * //    脚本框架：
     * */
    public static String sep = File.separator;
    @Resource
    private XsElementMapper xsElementMapper;
    @Resource
    private XsTemplateService xsTemplateService;

    public void getExcalInfo() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook("C:\\Users\\28442\\Desktop\\A.xlsx");
        System.out.println("1.得到列表");
        int num = 0;
        for (Sheet sheet : sheets) {
            System.out.println("------------------" + num++);
            /*24567 -*/
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && i > 2 && row.getCell(2) != null && (!"".equals(row.getCell(2).toString().trim()))) {
                    Cell nodeCode = row.getCell(2);
                    Cell eleName = row.getCell(5);
                    Cell eleCode = row.getCell(4);
                    Cell group = row.getCell(6);
                    Cell parentGroup = row.getCell(7);
                    List<String> strings = xsTemplateService.selectTemplateSnByNode(nodeCode.toString());
                    String tempSn = strings.get(0);
                    System.out.println("nodeCode" + nodeCode + "转换为" + tempSn);
                    if ("40011".equals(tempSn) || "40022".equals(tempSn)) {
                        System.out.println("跳过历史场景");
                        continue;
                    }
                    System.out.println("执行：" + "\t" +
                            eleCode.toString() + "\t" + (group == null ? null : group.toString()) + "\t" + (parentGroup == null ? null : parentGroup.toString()) + "\t" +
                            tempSn + "\t" + eleName.toString());
                    xsElementMapper.updateElementcodeAndGroupAndParentgroupByTemplateSnAndEleName(
                            eleCode.toString(), group == null ? null : group.toString(), parentGroup == null ? null : parentGroup.toString(), tempSn, eleName.toString());
                }
            }
        }
    }

    public int deleteByPrimaryKey(Integer eleSn, Integer isDel) {
        return xsElementMapper.deleteByPrimaryKey(eleSn, isDel);
    }

    public int insert(XsElement record) {
        return xsElementMapper.insert(record);
    }

    public int insertSelective(XsElement record) {
        return xsElementMapper.insertSelective(record);
    }

    public XsElement selectByPrimaryKey(Integer eleSn, Integer isDel) {
        return xsElementMapper.selectByPrimaryKey(eleSn, isDel);
    }

    public String selectPath() throws IOException {
        List<Integer> strings = selectEleSn();
        StringBuilder sb = new StringBuilder();
        for (Integer elecode : strings) {
            XsElement xsElement = xsElementMapper.selectByPrimaryKey(elecode, 0);
            String mapName = xsElement.getMapName();
            Integer eleSn1 = xsElement.getEleSn();
            String templateSn = xsElement.getTemplateSn();
            if (mapName == null || "".equals(mapName)) {
                System.out.println("mapName为空");
                continue;
            }

            if (eleSn1 == null || 0 == eleSn1) {
                System.out.println("eleSn1为空");
                continue;
            }
            if (templateSn == null || "".equals(templateSn)) {

                System.out.println("templateSn为空");
                continue;
            }
            if (templateSn.equals("40011") || templateSn.equals("40022")) {
                System.out.println("跳过已完成场景");
                continue;
            }

            String templatePackage = xsTemplateService.findTemplatePackageByTemplateSn(templateSn).get(0);
//            String GROOVY_PATH = "D:\\down\\pna\\tlbook\\tlproject\\lsw\\TianJ_OCR\\src\\main\\java\\com\\turing\\engine\\scripts";
//            String GROOVY_PATH = "C:\\Users\\28442\\Desktop\\engine\\autoscripts2";
            String GROOVY_PATH = "D:\\down\\pna\\tlbook\\tlproject\\lsw\\TianJ_OCR\\src\\main\\java\\com\\turing\\engine\\scripts";
            String fileName = mapName + "_" + eleSn1 + "_" + templateSn + "_filter.groovy";
            String filenocutName = mapName + "_" + eleSn1 + "_" + templateSn + "_filter";
            File groovyFilePath = new File(GROOVY_PATH + sep + templatePackage + sep + "filter" + sep + fileName);

            String absolutePath = groovyFilePath.getAbsolutePath();
            System.out.println("------");
            System.out.print(fileName);
            if (groovyFilePath.exists()) {
//                testFile.mkdirs();
                System.out.println("    存在");
            } else {
                System.out.println("    X");
//                if ("yqfydw_10235_50008_filter.groovy".equals(fileName)) {
                if (groovyFilePath.createNewFile()) {
                    System.out.println("文件创建成功！");
                    List<String> strings1 = CodeGennerate.codeTemp(filenocutName, templatePackage);
                    System.out.println("写入：" + strings1);
                    CodeGennerate.writejavaCode(groovyFilePath, strings1);
                }
//                }
            }
            sb.append(absolutePath + "\n");
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    public int updateByPrimaryKeySelective(XsElement record) {
        return xsElementMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(XsElement record) {
        return xsElementMapper.updateByPrimaryKey(record);
    }

    public List<Integer> selectEleSn() {
        return xsElementMapper.selectEleSn();
    }

    public void updateElementcodeAndGroupAndParentgroupByTemplateSnAndEleName() {
        try {
            getExcalInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }//60
        //2.数据准备

        //3.执行

    }
}



