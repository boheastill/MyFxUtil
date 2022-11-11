package cn.i623.myspringutil.fun;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;

public class HMSorry {
    public static void main(String[] args) {

        for (int i = 1; i <= 100000; i++) {
            String curNumStr = String.valueOf(i);
            int shiwan = 0;
            int wan = 0;
            int qian = 0;
            int bai = 0;
            int shi = 0;
            int ge = 0;
            int length = curNumStr.length();
            String highStr;
            switch (length) {
                case 6:
                    highStr = curNumStr.substring(0, 1);
                    shiwan = Integer.valueOf(highStr);
                    curNumStr = curNumStr.substring(1);

                case 5:
                    highStr = curNumStr.substring(0, 1);
                    wan = Integer.valueOf(highStr);
                    curNumStr = curNumStr.substring(1);
                case 4:
                    highStr = curNumStr.substring(0, 1);
                    qian = Integer.valueOf(highStr);
                    curNumStr = curNumStr.substring(1);
                case 3:
                    highStr = curNumStr.substring(0, 1);
                    bai = Integer.valueOf(highStr);
                    curNumStr = curNumStr.substring(1);
                case 2:
                    highStr = curNumStr.substring(0, 1);
                    shi = Integer.valueOf(highStr);
                    curNumStr = curNumStr.substring(1);
                case 1:
                    highStr = curNumStr.substring(0, 1);
                    ge = Integer.valueOf(highStr);
            }
            StringBuilder chinaNUM = new StringBuilder();

            if (shiwan == 0) {

            } else {
                chinaNUM.append(getChinaNUM(shiwan) + "十");
            }
            if (wan == 0) {
                if (shiwan != 0) {
                    chinaNUM.append("万");
                }
            } else {
                chinaNUM.append(getChinaNUM(wan) + "万");
            }
            if (qian == 0) {
                chinaNUM.append("零");
            } else {
                chinaNUM.append(getChinaNUM(qian) + "千");
            }
            if (bai == 0) {
                chinaNUM.append("零");
            } else {
                chinaNUM.append(getChinaNUM(bai) + "百");
            }
            if (shi == 0) {
                chinaNUM.append("零");
            } else {
                chinaNUM.append(getChinaNUM(shi) + "十");
            }
            if (ge == 0) {

            } else {
                chinaNUM.append(getChinaNUM(ge));
            }
            String finalChinaese = chinaNUM.toString().replaceAll("零+", "零").replaceAll("(^零)|(零$)", "").replaceAll("((?<=零)(一十))|(^一十)", "十");
            System.out.println(finalChinaese+"：sorry");
        }


    }

    static HashMap<Integer, String> map;

    private static String getNUMChine(int i) {
        if (map == null) {
            map = new HashMap<>();
            map.put(1, "一");
            map.put(2, "二");
            map.put(3, "三");
            map.put(4, "四");
            map.put(5, "五");
            map.put(6, "六");
            map.put(7, "七");
            map.put(8, "八");
            map.put(9, "九");
        }
        String s = map.get(i);
        return s;
    }

    @NotNull
    private static String getChinaNUM(int shiwan) {
        return getNUMChine(shiwan);
    }
}
