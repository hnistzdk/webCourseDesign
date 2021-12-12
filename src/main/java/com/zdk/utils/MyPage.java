package com.zdk.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author zdk
 * @date 2021/12/12 16:12
 * 分页page
 */
public class MyPage<T> {
    private List<T> list;

    private int totalRows = 0;

    private int pageSize = 10;

    private int totalPage = 1;

    private int pageIndex = 1;

    private boolean isFirstPage = false;

    private boolean isLastPage = false;

    private boolean isPreviousPage = false;

    private boolean isNextPage = false;

    /**
     * 导航页码数
     */
    private int navigatePages = 2;

    /**
     * 所有导航页号
     */
    private int[] navigatePageNumbers;

    public MyPage(int totalRows, int pageIndex) {
        init(totalRows, pageIndex, pageSize);

    }

    public MyPage(int totalRows, int pageIndex, int pageSize) {
        init(totalRows, pageIndex, pageSize);

    }

    public MyPage(List<T> list, int totalRows, int pageIndex) {
        super();

        init(totalRows, pageIndex, pageSize);

        this.list = list;

        this.totalRows = totalRows;

        this.pageIndex = pageIndex;

    }

    public MyPage(List<T> list, int totalRows, int pageSize, int pageIndex) {
        super();

        init(totalRows, pageIndex, pageSize);

        this.list = list;

        this.totalRows = totalRows;

        this.pageSize = pageSize;

        this.pageIndex = pageIndex;

    }

    private void init(int totalRows, int pageIndex, int pageSize) {
        // 设置基本参数

        this.totalRows = totalRows;

        this.pageSize = pageSize;

        this.totalPage = (this.totalRows - 1) / this.pageSize + 1;

        // 根据输入可能错误的当前号码进行自动纠正

        if (pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = Math.min(pageIndex, this.totalPage);
        }

        // 基本参数设定之后进行导航页面的计算

        calcNavigatePageNumbers();

        // 以及页面边界的判定

        judgePageBoudary();

    }

    /**
     * 计算导航页
     */

    private void calcNavigatePageNumbers() {
        // 当总页数小于或等于导航页码数时

        if (totalPage <= navigatePages) {
            navigatePageNumbers = new int[totalPage];

            for (int i = 0; i < totalPage; i++) {
                navigatePageNumbers[i] = i + 1;

            }

        } else { // 当总页数大于导航页码数时

            navigatePageNumbers = new int[navigatePages];

            int startNum = pageIndex - navigatePages / 2;

            int endNum = pageIndex + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;

                // (最前navPageCount页

                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;

                }

            } else if (endNum > totalPage) {
                endNum = totalPage;

                // 最后navPageCount页

                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNumbers[i] = endNum--;

                }

            } else {
                // 所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;

                }

            }

        }

    }

    /**
     * 判定页面边界
     */

    private void judgePageBoudary() {
        isFirstPage = (1 == pageIndex);

        isLastPage = ((pageIndex == totalPage && 1 != pageIndex) || (pageIndex == 1 && totalPage == 1));

        isPreviousPage = (1 != pageIndex);

        isNextPage = (pageIndex != totalPage);

    }

    /**
     * 得到当前页的内容
     *
     * @return {List}
     */

    public List<T> getList() {
        return list;

    }

    public void setList(List<T> list) {
        this.list = list;

    }

    /**
     * 得到记录总数
     *
     * @return {int}
     */

    public int getTotalRows() {
        return totalRows;

    }

    /**
     * 得到每页显示多少条记录
     *
     * @return int
     */

    public int getPageSize() {
        return pageSize;

    }

    /**
     * 得到页面总数
     *
     * @return {int}
     */

    public int getTotalPage() {
        return totalPage;

    }

    /**
     * 得到当前页号
     *
     * @return {int}
     */

    public int getPageIndex() {
        return pageIndex;

    }

    /**
     * 得到所有导航页号
     *
     * @return {int[]}
     */

    public int[] getNavigatePageNumbers() {
        return navigatePageNumbers;

    }

    public boolean isFirstPage() {
        return isFirstPage;

    }

    public boolean isLastPage() {
        return isLastPage;

    }

    public boolean isPreviousPage() {
        return isPreviousPage;

    }

    public boolean isNextPage() {
        return isNextPage;

    }

    @Override
    public String toString() {
        return "MyPage{" +
                "list=" + list +
                ", totalRows=" + totalRows +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", pageIndex=" + pageIndex +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", isPreviousPage=" + isPreviousPage +
                ", isNextPage=" + isNextPage +
                ", navigatePages=" + navigatePages +
                ", navigatePageNumbers=" + Arrays.toString(navigatePageNumbers) +
                '}';
    }
}
