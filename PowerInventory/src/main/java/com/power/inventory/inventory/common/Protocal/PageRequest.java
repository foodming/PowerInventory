package com.power.inventory.inventory.common.Protocal;

import com.power.inventory.inventory.common.constant.SortEnum;

public class PageRequest {
    private int dataType;       //请求的数据种类，暂时未用
    private Long pageSize;       //请求参数，每页显示的数据
    private Long currentPage;    //请求参数，当前的页码

    private boolean isSort = false; //请求参数，是否排序
    private String sortColumnName;  //请求参数，排序的列名
    private SortEnum sortType;      //请求参数，正序/反序


    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isSort() {
        return isSort;
    }

    public void setSort(boolean sort) {
        isSort = sort;
    }

    public String getSortColumnName() {
        return sortColumnName;
    }

    public void setSortColumnName(String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }

    public SortEnum getSortType() {
        return sortType;
    }

    public void setSortType(SortEnum sortType) {
        this.sortType = sortType;
    }

}
