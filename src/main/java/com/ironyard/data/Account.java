package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;

/**
 * Each User needs to be able to have their own account(s)
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@Entity
public class Account {

    private String title;
    private Date date;
    private String type;
    private String description;
    private double debit;        // (-)
    private double credit;       // (+)
    private String term;                           // only for savings accounts
    private double tBillRate;                  // only for savings accounts
    private double bankRate;
    private double rateDifference; //percent % difference between -treasury rate-  and -bank rate-
    private double postedBalance;            //**posted balance is the same as Balance**
    private double availableBalance;


    BigDecimal bank = new BigDecimal(bankRate, MathContext.DECIMAL64);

    // in test BigDecimal.valueOf(0.00083);


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Account() {
    }

    public Account(String title, Date date, String type, String description, double debit, double credit, String term, double tBillRate, double bankRate, double rateDifference, double postedBalance, double availableBalance) {
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

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public double gettBillRate() {
        return tBillRate;
    }

    public void settBillRate(double tBillRate) {
        this.tBillRate = tBillRate;
    }

    public double getBankRate() {
        return bankRate;
    }

    public void setBankRate(double bankRate) {
        this.bankRate = bankRate;
    }

    public double getRateDifference() {
        return rateDifference;
    }

    public void setRateDifference(double rateDifference) {
        this.rateDifference = rateDifference;
    }

    public double getPostedBalance() {
        return postedBalance;
    }

    public void setPostedBalance(double postedBalance) {
        this.postedBalance = postedBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBank() {
        return bank;
    }

    public void setBank(BigDecimal bank) {
        this.bank = bank;
    }
}
