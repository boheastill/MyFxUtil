package cn.i623.myspringutil.huibian;

public class AS {
    public static void main(String[] args) {
        //传入float，保留小数的位数
        Double num = 1.24400001;
        Float aFloat = subFloatByLenthWithAccount(num, 2);
        System.out.println(aFloat);

    }

    /*
     * 传入小数和保留位数
     * 返回指定小数长度（按四舍六入五成双）
     * 逻辑：小于5舍，大于5进，等于5看高一位奇数进，偶数舍。
     *
     * */
    public static Float subFloatByLenthWithAccount(Double num, int site) {
        String numStr = num.toString();
        int pointIdx = numStr.indexOf(".");
        String zhengshu = numStr.substring(0, pointIdx);
        String xiaoshu = numStr.substring(pointIdx + 1);
        StringBuilder format = new StringBuilder();
        for (int i = xiaoshu.length(); i <= site; i++) {
            format.append("0");
        }
        String xiaoshuFormat = xiaoshu + format;
        String xiaoshubaoliu = xiaoshuFormat.substring(0, site);
        String xiaoshulast = xiaoshuFormat.substring(site);
        Integer xiaoshubaoliuInt = Integer.valueOf(xiaoshubaoliu);
        Integer xiaoshulastInt = Integer.valueOf(xiaoshulast);

        Integer xiaoshulastintFirst = Integer.valueOf(xiaoshulastInt.toString().substring(0, 1));
        if (xiaoshulastintFirst == 5) {
            if (xiaoshulastInt > 10) {
                xiaoshubaoliuInt++;
            } else if ((xiaoshubaoliuInt % 2) == 1) {
                xiaoshubaoliuInt++;
            }
        } else if (xiaoshulastintFirst > 5) {
            xiaoshubaoliuInt++;
        }
        //格式转换回
        String anwser = zhengshu + "." + xiaoshubaoliuInt;
        Float aFloat = Float.valueOf(anwser);
        return aFloat;
    }

}
