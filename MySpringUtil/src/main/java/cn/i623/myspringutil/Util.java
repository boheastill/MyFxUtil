package cn.i623.myspringutil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static boolean writejavaCode(File file, List<String> rawList) {
        boolean flag = false;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));) {
            file.createNewFile();
            for (String raw : rawList) {
                bufferedWriter.write(raw);
                bufferedWriter.newLine();
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    public static boolean writejavaCode(File file, List<String> rawList, String title, Boolean append) {
        boolean flag = false;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "UTF-8"));) {
            file.createNewFile();
            bufferedWriter.write(title);
            bufferedWriter.newLine();
            if (rawList == null) {
                return false;
            }
            for (String raw : rawList) {
                bufferedWriter.write(raw);
                bufferedWriter.newLine();
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }


    public static List<String> codeTemp(String nocutfilename, String packName) {
        java.util.List<String> rawList = new ArrayList<>();
        rawList.add("package com.turing.engine.scripts." + packName + ".filter");
        rawList.add("");
        rawList.add("import com.turing.engine.scripts.node0101C50001.filter.RegPostProcess");
        rawList.add("");
        rawList.add("/*默认走父类逻辑，并用子类静态代码块里给父类的正则，返回给java正则捞到的第一个值*/");
        rawList.add("");
        rawList.add("class " + nocutfilename + " extends RegPostProcess {");
        rawList.add("    static {");
        rawList.add("        new RegPostProcess(~/(?<=前置)[^,.，。]*?(?=后置)/)");
        rawList.add("    }");
        rawList.add("}");
        return rawList;
    }

}
