package com.xnhb.et.bean.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 oneway on 2018/9/29
 * 描述: 分页加载
 * 参考链接:
 */
public class LimitPage<T> {

    /**
     * pageNum : 1
     * pageSize : 20
     * size : 3
     * startRow : 1
     * endRow : 3
     * total : 3
     * pages : 1
     * list : [{"id":3,"createTime":"2018-08-29 10:55:11","updateTime":"2018-08-29 10:55:11","remark":"","version":null,"title":"三生三世","content":"<p>阿达大神<\/p>\r\n\r\n<p>奥术大师多爱仕达爱仕达爱仕达<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<p>阿达框架的卡上爱仕达 asd sad&nbsp;<\/p>\r\n"},{"id":2,"createTime":"2018-08-29 10:54:53","updateTime":"2018-08-29 10:54:53","remark":"","version":null,"title":"测试111","content":"<p>阿达撒大所多as大道as爱仕达<\/p>\r\n\r\n<p>asd&nbsp;<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<p>爱仕达sadsadas<\/p>\r\n"},{"id":1,"createTime":"2018-07-23 16:49:28","updateTime":"2018-07-23 16:49:28","remark":"","version":null,"title":"E网已全新改版2.0版本,即将开放主流币的挖矿！","content":"E网已全新改版2.0版本,即将开放主流币的挖矿！\r\n给您带来不便，敬请谅解。如有任何疑问请登录您的账户，扫描添加客服微信，我们将竭诚为您服务。\r\n\r\n风险提示：\r\n1. E网仅提供一个自由的数字货币交易平台，不对其发行价值做任何的审核或担保，亦不承担赔偿责任，如果您不能接 受，请勿交易。 购买数字货币有较高的风险，请在您个人的风险承担能力范围内，谨慎购买。\r\n2. 美国、中国、新加坡等国家与地区用户，鉴于全球监管环境的可能变化，请在参与交易之前咨询本国律师，以便严格遵 守本国法律。E网保留随时终止向可能违反所在国相关法律的用户提供服务的权利。"}]
     * prePage : 0
     * nextPage : 0
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * firstPage : 1
     * lastPage : 1
     */
    private ArrayList<T> list;
    private int total; //总条数
    private int pages; //总页数
    private int pageSize;  //每页数量
    private int pageNum;
    private int size;
    private int startRow;
    private int endRow;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
}
