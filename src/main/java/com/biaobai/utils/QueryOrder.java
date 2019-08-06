package com.biaobai.utils;

/**
 * 查询排序
 * 
 * @author zhaojing
 *
 */
public class QueryOrder {
    /**
     * 排序字段名
     */
    private String column;

    /**
     * 是否正序排列
     */
    private Boolean isAsc;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean isAsc) {
        this.isAsc = isAsc;
    }

}
