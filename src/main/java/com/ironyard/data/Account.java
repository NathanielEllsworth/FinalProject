package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Each User needs to be able to have their own account(s)
 *
 * Created by nathanielellsworth on 11/4/16.
 */

@Entity
public class Account {

    private long userId;
    private String title;
    private Date date;
    private String type;
    private String description;
    private String debit;        // (-)
    private String credit;       // (+)
    private String term;                           // only for savings accounts
    private String tBillRate;                  // only for savings accounts
    private String bankRate;
    private String rateDifference; //percent % difference between -treasury rate-  and -bank rate-
    private String postedBalance;            //**posted balance is the same as Balance**
    private String availableBalance;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Account() {
    }

    public Account(String title, Date date, String type, String description, String debit, String credit, String term, String tBillRate, String bankRate, String rateDifference, String postedBalance, String availableBalance) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
        this.term = term;
        this.tBillRate = tBillRate;
        this.bankRate = bankRate;
        this.rateDifference = rateDifference;
        this.postedBalance = postedBalance;
        this.availableBalance = availableBalance;
    }

    //_________________________________________________________________________


    public long getUserId() {return userId;}

    public void setUserId(long userId) {this.userId = userId;}


//_________________________________________________________________________


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String gettBillRate() {
        return tBillRate;
    }

    public void settBillRate(String tBillRate) {
        this.tBillRate = tBillRate;
    }

    public String getBankRate() {
        return bankRate;
    }

    public void setBankRate(String bankRate) {
        this.bankRate = bankRate;
    }

    public String getRateDifference() {
        return rateDifference;
    }

    public void setRateDifference(String rateDifference) {
        this.rateDifference = rateDifference;
    }

    public String getPostedBalance() {
        return postedBalance;
    }

    public void setPostedBalance(String postedBalance) {
        this.postedBalance = postedBalance;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
