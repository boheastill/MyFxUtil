package cn.i623.myfxutil.test;


import cn.i623.myfxutil.myinterface.IDCardInterface;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

import static cn.i623.myfxutil.util.ExcelUtils.getCellFormatValue;
import static cn.i623.myfxutil.util.ExcelUtils.readExcel;


public class IDCard implements IDCardInterface {
    //字段
    String city;
    String birthday;
    int num;
    String check;
    Boolean isMan;//奇数

    //构造器
    public IDCard(IDCardBulider idCardBulider) {
        this.city = idCardBulider.city;
        this.birthday = idCardBulider.birthday;
        this.num = idCardBulider.num;
        this.check = idCardBulider.check;
        this.isMan = idCardBulider.isMan;
    }

    public IDCard() {
    }

    //校验算法
    private static Integer getWi(int i) {
        assert i > 0;
        double pow = Math.pow(2, --i);
        return (int) pow % 11;
    }

    public static void main(String[] args) {
        IDCard idCard = new IDCardBulider(false)
                .city(null)
                .birthday("19940315").num(10).check("").bulid();
        String id = idCard.getId();
//        System.out.println("id" + id);
    }

    public String getId() {
        return (num == -1 || check == null) ? null : city + birthday + num + check;
    }

    @Override
    public Boolean cheakIdCard(String code) {
        return IDCardBulider.verfify(code);
    }

    @Override
    public String getRadomIdCard() {
        IDCard idCard = new IDCardBulider(true)
                .city(null)
                .birthday("19940315").num(101).check("").bulid();
        String id = idCard.getId();
//        System.out.println("id" + id);
        return id;
    }

    @Override
    public String getIdCardWithoutVerfy() {
        return null;
    }

    @Override
    public List<String> getInfoByIdCard() {
        return null;
    }

    public static class IDCardBulider {
        String city;
        String birthday;
        int num;
        String check;
        Boolean isMan;//奇数

        public IDCardBulider(Boolean isMan) {
            this.isMan = isMan;
        }

        //方法
        public static List<String> findTopList() {
            List<String> tl = new ArrayList<>();
            //创建工作簿
            String pathname = "C:\\Users\\28442\\Desktop\\区号.xlsx";
            Workbook sheets = readExcel(pathname);
            Sheet sheet = sheets.getSheetAt(0); //sheet
//        Print.p("sheet",sheet.getRow());
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String tempsnFormat = (String) getCellFormatValue(row.getCell(0));
                String tempsnFormat2 = (String) getCellFormatValue(row.getCell(1));
//            Print.p(tempsnFormat);
                if (tempsnFormat != null && tempsnFormat.trim().length() > 0) {
                    tl.add(tempsnFormat);
                }
//            Print.p(tempsnFormat2.trim());
            }
            return tl;
        }

        /*校验是否符合身份证规则
         * 输入：长度为18的字符串，非数字的默认按罗马X 处理
         * 输出：符合规则（是/否）
         * */
        static Boolean verfify(String code) {
            int valueCount = 0;
            for (int i = 0; i < code.length(); i++) {
                int rIdx = 18 - i;
                int curNum;
                try {
                    curNum = Integer.parseInt(code.substring(i, i + 1));
                } catch (NumberFormatException e) {
                    curNum = 10;
                }
                Integer Widx = IDCard.getWi(rIdx);
                int value = curNum * Widx;
                valueCount += value;
            }
            boolean b = valueCount % 11 == 1;
//            System.out.println(b);
            return b;
        }

        public IDCardBulider city(String city) {
            if (city == null) {
                List<String> topList = findTopList();
                int ridx = (int) (Math.random() * topList.size());
//                System.out.println(ridx);
                city = topList.get(ridx);
//                System.out.println();
            }
            this.city = city;
            return this;
        }

        public IDCardBulider birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public IDCardBulider num(int num) {
            this.num = this.isMan ^ num % 2 == 0 ? num : -1;
            return this;
        }

        public IDCard bulid() {
//            System.out.println(this.toString());
            return new IDCard(this);
        }

        public IDCardBulider check(String check) {
            if (this.num > -1) {
//                System.out.println("检查");
                String[] checkArray = new String[11];
                for (int j = 0; j < 10; j++) {
                    checkArray[j] = String.valueOf(j);
                }
                checkArray[10] = "Ⅹ";
                for (String cheakCode : checkArray) {
                    String code = this.city + this.birthday + this.num + cheakCode;
                    if (verfify(code)) {
                        this.check = cheakCode;
//                        return this;
                    }
                }
            }
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"city\":\"")
                    .append(city).append('\"');
            sb.append(",\"birthday\":\"")
                    .append(birthday).append('\"');
            sb.append(",\"num\":")
                    .append(num);
            sb.append(",\"check\":\"")
                    .append(check).append('\"');
            sb.append(",\"isMan\":")
                    .append(isMan);
            sb.append('}');
            return sb.toString();
        }
    }
}
