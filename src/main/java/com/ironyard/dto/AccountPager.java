package com.ironyard.dto;

import org.springframework.data.domain.Page;

/**
 * for sorting through accounts wired in and opened in the app.
 *
 * account and transaction pagers are redundant, will consolidate later
 *
 * Created by nathanielellsworth on 11/14/16.
 */

public class AccountPager {
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int totalPages;
    private int totalAccounts;


    public AccountPager(Integer page, Page aPageOfAccounts) {
        this.previousPage = page - 1;
        this.nextPage = page + 1;

        // check to see if there really is a next page
        if(nextPage >= aPageOfAccounts.getTotalPages()){
            nextPage = -1;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(int totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isNext(){
        return nextPage > 0;
    }

    public boolean isPrevious(){
        return previousPage >= 0;
    }

}
