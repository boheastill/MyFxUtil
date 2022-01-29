package cn.i623.myspringutil.domain;

import java.util.Date;

public class XsElement {
    private Integer eleSn;

    /**
     * 0未删除 1已删除
     */
    private Integer isDel;

    /**
     * 映射名
     */
    private String mapName;

    /**
     * 模板id
     */
    private String templateSn;

    /**
     * 要素名称
     */
    private String eleName;

    /**
     * 返回要素名称
     */
    private String elementcode;

    /**
     * 要素类型 单项 多项
     */
    private String eleType;

    /**
     * 文件夹id
     */
    private String packageSn;

    /**
     * 组名
     */
    private String group;

    /**
     * 父级组名
     */
    private String parentgroup;

    /**
     * 0false 1true
     */
    private Integer selected;

    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date updatedTime;

    /**
     * 数据类型 字符串 数字 日期 字符串 长文本
     */
    private String eleCategoryCh;

    /**
     * 是否被纳入统计 1是 0否
     */
    private Integer counted;

    /**
     * 关联条件是否需要引入json文本 0不需要 1需要
     */
    private Integer needsJson;

    public Integer getEleSn() {
        return eleSn;
    }

    public void setEleSn(Integer eleSn) {
        this.eleSn = eleSn;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getTemplateSn() {
        return templateSn;
    }

    public void setTemplateSn(String templateSn) {
        this.templateSn = templateSn;
    }

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    public String getElementcode() {
        return elementcode;
    }

    public void setElementcode(String elementcode) {
        this.elementcode = elementcode;
    }

    public String getEleType() {
        return eleType;
    }

    public void setEleType(String eleType) {
        this.eleType = eleType;
    }

    public String getPackageSn() {
        return packageSn;
    }

    public void setPackageSn(String packageSn) {
        this.packageSn = packageSn;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getParentgroup() {
        return parentgroup;
    }

    public void setParentgroup(String parentgroup) {
        this.parentgroup = parentgroup;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getEleCategoryCh() {
        return eleCategoryCh;
    }

    public void setEleCategoryCh(String eleCategoryCh) {
        this.eleCategoryCh = eleCategoryCh;
    }

    public Integer getCounted() {
        return counted;
    }

    public void setCounted(Integer counted) {
        this.counted = counted;
    }

    public Integer getNeedsJson() {
        return needsJson;
    }

    public void setNeedsJson(Integer needsJson) {
        this.needsJson = needsJson;
    }
}