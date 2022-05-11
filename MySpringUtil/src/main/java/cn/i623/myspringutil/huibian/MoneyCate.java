package cn.i623.myspringutil.huibian;

import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;

import static cn.i623.myspringutil.huibian.MoneyCate.MyLocalDate.eq;

/*建模
 * 1.时间，年/年龄+月
 * 2.收入计算-》年统计
 * 3.支出计算-》年统计
 * 4.系数参数化，结论图表化
 * 5.配套时间管理
 * */
public class MoneyCate {
    private int year;
    private int saveAmount;

    public static void main(String[] args) {
        //1.调整信息
        int[][] jupList = {{2022, 7, 16}, {2024, 7, 330}, {2026, 7, -50}};
        MyLocalDate start = MyLocalDate.getStart();
        MyLocalDate assign = new MyLocalDate(2026, 11);
//        MyLocalDate assign = MyLocalDate.now();
        //传入调整信息，传入起始值，传入指定日期，返回调整系数。
        MyLocalDate.foreachDate(start, assign, jupList);

        //2.逐月收益

        //3.展示

        //2.得到月列表
        LinkedList<MonthStatu> monthStatuList = new LinkedList<>();
        MonthStatu monthStatu = new MonthStatu();

        //1.1获取指定月基本数据。
    }


    //每个月的逻辑
    private static void extracted(int[][] jupList, int i, int j) {


        for (int x = 0; x < jupList.length; x++) {
            if (eq(new MyLocalDate(i, j), new MyLocalDate(jupList[x][0], jupList[x][1]))) {
                extracted(jupList[x]);
            }
        }
    }

    //增长月的逻辑
    private static void extracted(int[] jupList) {
        System.out.println("增长" + jupList);
    }

//    static class YearStatu {
//
//    }

    static class MonthStatu {
        MyLocalDate curdate;
        int base;
        int age;
        int historyAccumulative;
        int year;
        int jupNum;
        int workMounth;
        int residue;
    }


    @Data
    static class Earnings {
        private double payRise;
        private int salary;
    }

    @Data
    static class Disburse {

        private int house;
        private int relace;
        private int push;
        private int eat;
        private int annuity;
        private int free;
        private int exclusive;
    }

    //日期类
    static class MyLocalDate {
        int year;
        int month;

        public MyLocalDate(int year, int month) {
            this.year = year;
            this.month = month;
        }

        public static MyLocalDate of(int year, int month, int day_nouse) {
            return new MyLocalDate(year, month);
        }

        public static MyLocalDate getStart() {
            return new MyLocalDate(2022, 5);
        }

        public static MyLocalDate now() {
            LocalDate now = LocalDate.now();
            return new MyLocalDate(now.getYear(), now.getMonthValue());
        }

        public static void foreachDate(MyLocalDate startdate, MyLocalDate enddate, int[][] jupList) {
            foreachDate(startdate.year, startdate.month, enddate.year, enddate.month, jupList);
        }

        public static Boolean eq(MyLocalDate dateA, MyLocalDate dateB) {
            if (dateA == null && dateB == null) {
                return true;
            }
            if (dateA == null ^ dateB == null) {
                return false;
            }
            if (dateA.year == dateB.year) {
                return dateA.month == dateB.month;
            } else {
                return false;
            }
        }

        /*闭区间*/
        public static void foreachDate(int startyear, int startmonth, int endyear, int endmonth, int[][] jupList) {
            if ((startyear > endyear) || (startyear == endyear && startmonth > endmonth)) {
                return;
            }
            for (int i = startyear; i < endyear + 1; i++) {
                int maxMonth = 12;
                if (i == endyear) {//只有尾年才按尾月计
                    maxMonth = endmonth;
                }
                for (int j = startmonth; j < maxMonth + 1; j++) {
//                    System.out.println(i + "年\t" + j + "月");
                    extracted(jupList, i, j);
//                    System.out.println("---------");

                }
                startmonth = 1;//第二年开始从1月计
            }
        }


    }
}
