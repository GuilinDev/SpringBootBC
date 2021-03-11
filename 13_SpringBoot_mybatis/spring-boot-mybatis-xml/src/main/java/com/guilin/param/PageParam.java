package com.guilin.param;

/**
 * 分页的基础类
 */
public class PageParam {
    private int beginLine;//起始行
    private Integer pageSize = 3;
    private Integer currentPage = 0;// 当前页

    public int getBeginLine() {
        //自动计算起始行, 默认每页 3 条记录，可以根据前端传参进行修改。
        return pageSize * currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "beginLine=" + beginLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
