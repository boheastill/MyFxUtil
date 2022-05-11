package cn.i623.myspringutil.fun.book.vo;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    List<String> rawList;
    String title;
    String nextHref;
    Boolean normal = false;
    Integer count;
    Integer Raw;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"rawList\":").append(rawList);
        sb.append(",\"title\":\"").append(title).append('\"');
        sb.append(",\"nextHref\":\"").append(nextHref).append('\"');
        sb.append(",\"normal\":").append(normal);
        sb.append(",\"count\":").append(count);
        sb.append(",\"Raw\":").append(Raw);
        sb.append('}');
        return sb.toString();
    }
}