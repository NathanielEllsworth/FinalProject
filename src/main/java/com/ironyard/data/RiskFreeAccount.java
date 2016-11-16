package com.ironyard.data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * each User's Homepage will contain their 'Risk Free' Account. Each user will only have one of these accounts.
 *
 *
 * Created by nathanielellsworth on 11/14/16.
 */
@Entity
public class RiskFreeAccount {


    private String date;
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


    // good ole JPA
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public RiskFreeAccount() {

    }


    public RiskFreeAccount(String date, String type, String description, double debit, double credit, String term, double tBillRate, double bankRate, double rateDifference, double postedBalance, double availableBalance) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
