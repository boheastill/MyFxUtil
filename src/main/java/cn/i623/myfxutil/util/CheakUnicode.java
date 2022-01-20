package cn.i623.myfxutil.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CheakUnicode {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String y = "Ｋ", x = "K";
        getUnicode(x);
    }


    public static String translate(String str) {
        String tempStr = "";
        try {
            tempStr = new String(str.getBytes(StandardCharsets.UTF_8), "unicode");
            tempStr = tempStr.trim();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tempStr;
    }


    public static void getUnicode(String s) throws UnsupportedEncodingException {

        //第二种
//        getUniCode2(s);
        // 第三种
        getniCode3(s);
    }

    private static void getniCode3(String s) {
        System.err.println("第三种-----toCharArray");
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            System.err.println("char字符:" + aChar);
            String x = Integer.toHexString(aChar);
            if (x.length() <= 2) {
                x = "\\u00" + x;
            } else {
                x = "\\u" + x;
            }
            System.err.println("unicode码" + x);
        }
    }

    private static void getUniCode2(String s) throws UnsupportedEncodingException {
        System.err.println("第二种-----getBytes");

        byte[] bytes = s.getBytes("unicode");

        List<Object> list = new ArrayList<>();
        for (byte aByte : bytes) {
            System.err.println("byte字节:" + aByte);
            String x = Integer.toHexString(aByte);
            if ((-2 != aByte) && (-1 != aByte)) {
                list.add(x);
            }
        }
        for (int i = 0; i < list.size(); i = i + 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(list.get(i));
            stringBuilder.append(list.get(i + 1));
            if (stringBuilder.length() < 4) {
                for (int j = 0; j < 4 - stringBuilder.length(); j++) {
                    stringBuilder.insert(0, "0");
                }
            }
            System.err.println("unicode码" + "\\u".concat(stringBuilder.toString()));
        }
    }
}
