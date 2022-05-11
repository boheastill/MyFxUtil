package cn.i623.myspringutil.fun.book.vo;

import lombok.Data;

@Data
public class ChapterTitle {
    String name;
    String pageUrl;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"").append(name).append('\"');
        sb.append(",\"cataUrl\":\"").append(pageUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }
}