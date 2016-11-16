//package com.ironyard.dto;
//
//import org.springframework.data.domain.Page;
//
///**
// * Created by nathanielellsworth on 11/15/16.
// */
//public class RiskFreePager {
//    private int currentPage;
//    private int previousPage;
//    private int nextPage;
//    private int totalPages;
//    private int totalTransactions;
//
//
//    public RiskFreePager(Integer page, Page aPageOfTransactions){
//        this.previousPage = page - 1;
//        this.nextPage = page + 1;
//
//
//        if(nextPage >= aPageOfTransactions.getTotalPages()){
//            nextPage = -1;
//        }
//
//    }
//
//    public int getCurrentPage() {return currentPage;}
//
//    public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}
//
//    public int getPreviousPage() {return previousPage;}
//
//    public void setPreviousPage(int previousPage) {this.previousPage = previousPage;}
//
//    public int getNextPage() {return nextPage;}
//
//    public void setNextPage(int nextPage) {this.nextPage = nextPage;}
//
//    public int getTotalPages() {return totalPages;}
//
//    public void setTotalPages(int totalPages) {this.totalPages = totalPages;}
//
//    public int getTotalTransactions() {return totalTransactions;}
//
//    public void setTotalTransactions(int totalTransactions) {this.totalTransactions = totalTransactions;}
//}
