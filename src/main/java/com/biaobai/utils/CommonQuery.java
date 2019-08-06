package com.biaobai.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhaojing
 *
 */
public class CommonQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 4129468634566499082L;

    private StringBuffer orderBy;

    private String[] columns;

    // 分页参数
    private Integer pageSize;
    private Integer pageNo;

    private List<QueryOrder> orderByList = new ArrayList<>();

    public String getOrderBy() {
        return orderBy == null ? null : orderBy.toString();
    }

    public void setOrderBy(StringBuffer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<QueryOrder> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<QueryOrder> orderByList) {
        this.orderByList = orderByList;
    }

    /**
     * 添加排序
     * 
     * @param column
     * @param isAsc
     */
    public void setOrderColumn(String column, Boolean isAsc) {

        QueryOrder queryOrder = new QueryOrder();
        queryOrder.setColumn(column);
        queryOrder.setIsAsc(isAsc);

        this.orderByList.add(queryOrder);
    }

    /**
     * 倒序排列
     * 
     * @param column
     */
    public void setOrderColumn(String column) {

        QueryOrder queryOrder = new QueryOrder();
        queryOrder.setColumn(column);
        queryOrder.setIsAsc(false);

        this.orderByList.add(queryOrder);
    }

}
