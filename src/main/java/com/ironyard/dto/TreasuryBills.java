package com.ironyard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is where specific data pertaining to purchasing US Treasury Bills will be presented to the user inside their account.
 *
 * Created by nathanielellsworth on 11/4/16.
 */
public class TreasuryBills {

    @JsonProperty(value = "securityTerm")
    private String securityTerm;


    @JsonProperty(value = "issueDate")
    private String issueDate;                   // MM/dd/yyyy

    @JsonProperty(value = "maturityDate")
    private String maturityDate;

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

    public String getMaturityDate() {
        return maturityDate.replaceAll("T00:00:00","");
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getHighInvestmentRate() {return highInvestmentRate.replaceAll("000","%");}

    public void setHighInvestmentRate(String highInvestmentRate) {
        this.highInvestmentRate = highInvestmentRate;
    }
}
