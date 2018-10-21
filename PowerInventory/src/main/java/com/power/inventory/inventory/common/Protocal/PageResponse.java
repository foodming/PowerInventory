package com.power.inventory.inventory.common.Protocal;

import com.power.inventory.inventory.common.constant.SortEnum;

public class PageResponse extends BaseMessageResponse{
    private Long totalRecords = 0L;   //总数据条数
    private Long totalPages = 0L;     //总页数
    private Long pageSize = 20L;      //每页的数据条数
    private Long currentPage = 1L;    //当前页
    private Long fromPage = 1L;     //分页显示从第几页开始
    private Long toPage = 1L;       //分页显示到第几页结束
    private String url;             //URL
    private boolean isSort = false; //是否排序
    private String sortColumnName;  //排序的列名
    private SortEnum sortType;      //正序/反序

    public PageResponse(){


    }
    public PageResponse(PageRequest req, String url){
        this.currentPage = req.getCurrentPage();
        this.pageSize = req.getPageSize();
        this.isSort = req.isSort();
        this.sortColumnName = req.getSortColumnName();
        this.sortType = req.getSortType();

        this.url = url;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
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

    public Long getFromPage() {
        return fromPage;
    }

    public void setFromPage(Long fromPage) {
        this.fromPage = fromPage;
    }

    public Long getToPage() {
        return toPage;
    }

    public void setToPage(Long toPage) {
        this.toPage = toPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
