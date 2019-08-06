package com.biaobai.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author zc_luck
 * @date 2016年8月25日下午2:31:20
 */
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * ID 数组
     */
    private String[] ids;

    /**
     * ID 集合
     */
    private List<String> idList;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间-起
     */
    private String createDateFrom;

    /**
     * 创建时间-止
     */
    private String createDateTo;

    public String getId() {
        return id;
    }

    public String[] getIds() {
        return ids;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateTo(String createDateTo) {
        this.createDateTo = createDateTo;
    }

    public String getCreateDateFrom() {
        return createDateFrom;
    }

    public void setCreateDateFrom(String createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

}
