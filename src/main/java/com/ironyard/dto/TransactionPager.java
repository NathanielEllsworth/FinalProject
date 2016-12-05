package com.ironyard.dto;


/**
 * for sorting through accounts wired in and opened in the app.
 *
 * account and transaction pagers are redundant, will consolidate later
 *
 * Created by nathanielellsworth on 11/14/16.
 */

public class TransactionPager {
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int totalTransactions;
    private int totalPages;

    //Design change, moved paging logic to MvcAccountController (Lines 94 to 103)

    public TransactionPager(int currentPage, int previousPage, int nextPage, int totalTransactions, int totalPages) {
        this.currentPage = currentPage;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.totalTransactions = totalTransactions;
        this.totalPages = totalPages;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {return currentPage + 1;}

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


//________________________________________________________________________________________________
//             Below is what's used in the home.jsp's paging (lines 104 and 110)


    public boolean isNext(){
        return nextPage > 0;
    }

    public boolean isPrevious(){
        return previousPage >= 0;
    }

}
