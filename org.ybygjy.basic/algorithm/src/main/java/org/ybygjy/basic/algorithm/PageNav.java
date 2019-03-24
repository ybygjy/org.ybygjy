package org.ybygjy.basic.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PageNav {
    /** 总行数*/
    private int rows = 0;
    /** 每页数据行数*/
    private int pageCount = 0;
    /** 当前页*/
    private int currPage = 1;
    /** 分页数据起始位置*/
    private int start = 0;
    /** 分页数据结束位置*/
    private int end = 0;
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return (rows + pageCount - 1) / pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getStart() {
        return (this.getCurrPage() - 1) * getPageCount();
    }

    public int getEnd() {
        return (this.getCurrPage() - 1) * getPageCount() + getPageCount();
    }

    public static void main(String[] args) {
        Set<String> sett = new HashSet<>();
        sett.add("A");
        sett.add("B");
        System.out.println(sett.toString());
        List<Object> dataList = new ArrayList<Object>();
        for (int i = 0; i < 101; i++) {
            dataList.add("D_" + i);
        }
        PageNav pageNav = new PageNav();
        pageNav.setRows(dataList.size());
        pageNav.setPageCount(20);
        for (int i = 1; i <= pageNav.getPageSize(); i++) {
            pageNav.setCurrPage(i);
            System.out.printf("共%s条数据，分%s页，每页%s条，当前%s页，%s,%s \n", pageNav.getRows(), pageNav.getPageSize(), pageNav.getPageCount(), pageNav.getCurrPage(), pageNav.getStart(), pageNav.getEnd());
            splitData(dataList, pageNav.getStart(), pageNav.getEnd()).forEach(System.out::println);
        }
    }
    public static List splitData(List<Object> dataList, int start, int limit) {
        if (start < 0 || start > dataList.size()) {
            return Collections.EMPTY_LIST;
        }
        limit = limit > dataList.size() ? dataList.size() : limit;
        return dataList.subList(start, limit);
    }
}
