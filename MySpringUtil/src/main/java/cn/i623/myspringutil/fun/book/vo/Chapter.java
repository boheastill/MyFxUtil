package cn.i623.myspringutil.fun.book.vo;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {
    List<String> rawList;
    String title;
//    String nextHref;
    Boolean normal = false;
    Integer count;
    Integer Raw;

    @Override
    public String toString() {
        String sb = "{" + "\"rawList\":" + rawList +
                ",\"title\":\"" + title + '\"' +
                ",\"normal\":" + normal +
                ",\"count\":" + count +
                ",\"Raw\":" + Raw +
                '}';
        return sb;
    }
}