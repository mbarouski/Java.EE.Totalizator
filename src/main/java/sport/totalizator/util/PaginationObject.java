package sport.totalizator.util;

import java.util.List;

public class PaginationObject<T> {
    public static final int PER_PAGE = 5;
    public static final int DEFAULT_PAGE = 1;

    private int page;
    private int pageCount;
    private List<T> elementList;

    public PaginationObject(){}

    public PaginationObject(int page, int pageCount, List<T> elementList) {
        this.page = page;
        this.pageCount = pageCount;
        this.elementList = elementList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getElementList() {
        return elementList;
    }

    public void setElementList(List<T> elementList) {
        this.elementList = elementList;
    }
}
