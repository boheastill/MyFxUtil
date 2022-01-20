public class A {
    //幂等    函数一致三角函数，参数恒定

    int arg1, arg2, fun;


    /*得到参数，得到函数，运行1为了屏蔽运行2，参数变更，运行2，得到结果，运行3演运行2*/
    //不可逆， 函数提高嵌套复杂度，参数增加数量，影响函数的执行。信息损失可以增加不可逆性，信息损失过多导致填充占比大。
    //性能，使用位运算

    public static void main(String[] args) {
        String zs = mix("0 1 2 3 4 5");
        System.out.println(zs);

    }
//    寻找常量：奇偶，质数

    /*输入格式化：均匀分布*（单参数*，影响参数*，影响函数*）*/
    /*todo 不能防止记号破解*/
    static String mix(String a) {
        StringBuilder sb = new StringBuilder();
        for (char oldNum : a.toCharArray()) {
            //高频的打散,需要一个打散算法
            int i;
            double addNum = 1.07;
            int XNum = 6131;
            int subNum = 1;
            if (13 < oldNum & oldNum < 83) {
                addNum = 83;
                XNum = 797;
                subNum = 14249;
            } else if (127 < oldNum & oldNum < 151) {
                addNum = 557;
                XNum = 83;
                subNum = 15139;
            } else if (163 < oldNum & oldNum < 211) {
                addNum = 443;
                XNum = 983;
                subNum = 29983;
            } else if (227 < oldNum & oldNum < 251) {
                addNum = 401;
                XNum = 929;
                subNum = 13997;
            } else if (263 < oldNum & oldNum < 600) {
                addNum = 409;
                XNum = 619;
                subNum = 16907;
            } else {
                addNum = 14537;
                XNum = 653;
                subNum = 109719;
            }
            i = oldNum + XNum;
            double v = i * addNum;
            int d = (int) (v - subNum);
//            System.out.println(i);
//            System.out.println(v);
//            System.out.println(d);
//            System.out.println("-------");
            sb.append(d);
        }
        return sb.toString();
    }

    /*运算：扩大随机范围，并均匀分布值*/
    int getB(int a) {
        return a;
    }

    /*输出格式化：摘要*/
    int getC(int a) {
        return a;
    }
}
