package com.wyh.pojo;

import java.util.List;

/*
* JavaBean
*
* 以后的分页查询 不止会查Brand实体类一种
* 还会有其他的实体类需要分页查询
* 所以这里为 自定义泛型 <T>
* 即 在创建这个PageBean时 指定要分页查询的实体类即可*/
public class PageBean<T> {
    private int totalCount;
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
