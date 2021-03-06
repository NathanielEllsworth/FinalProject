package com.ironyard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is where specific data pertaining to purchasing US Treasury Bills will be presented to the user inside their account.
 *
 * Created by nathanielellsworth on 11/4/16.
 */

public class TreasuryBills {

    /**
     * Mapped the Treasury bill class to the Json mapping properties.
     * I can call my properties whatever I want and map them to whatever I want but for simplicity
     * the names are the same.
     */
    @JsonProperty(value = "securityTerm")
    private String securityTerm;


    @JsonProperty(value = "issueDate")
    private String issueDate;


    @JsonProperty(value = "highInvestmentRate")         //rate of return
    private String highInvestmentRate;


    public String getSecurityTerm() {
        return securityTerm;
    }

    public void setSecurityTerm(String securityTerm) {
        this.securityTerm = securityTerm;
    }

    public String getIssueDate() {
        return issueDate.replaceAll("T00:00:00","");
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }


    public String getHighInvestmentRate() {return highInvestmentRate.replaceAll("000","");}

    public void setHighInvestmentRate(String highInvestmentRate) {this.highInvestmentRate = highInvestmentRate;}


    //added a toString method to help with logging
    @Override
    public String toString(){return String.format("( %s : %s : %s)", getIssueDate(), getSecurityTerm(), getHighInvestmentRate());}

}
