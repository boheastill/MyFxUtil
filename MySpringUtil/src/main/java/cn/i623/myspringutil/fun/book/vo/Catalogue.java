package cn.i623.myspringutil.fun.book.vo;

import lombok.Data;

@Data
public class Catalogue {
    String name;
    String pageUrl;

    public Catalogue(String name, String pageUrl) {
        this.name = name;
        this.pageUrl = pageUrl;
    }
    public Catalogue() {
    }
    @Override
    public String toString() {
        return "{" + "\"name\":\"" + name + '\"' +
                ",\"cataUrl\":\"" + pageUrl + '\"' +
                '}';
    }
}