package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by nathanielellsworth on 11/14/16.
 */

@Entity
public class Transactions {

    private Date date;
    private String type;
    private String description;
    private boolean debit;        // (-)
    private boolean credit;       // (+)
    private String term;                   // only for savings accounts
    private boolean rate;                  // only for savings accounts
    private boolean postedBalance;   //**posted balance is the same as Balance**


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Transactions(){
    }

    public Transactions(Date date, String type, String description, boolean debit, boolean credit, String term, boolean rate, boolean postedBalance) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
        this.term = term;
        this.rate = rate;
        this.postedBalance = postedBalance;
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

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public boolean isPostedBalance() {
        return postedBalance;
    }

    public void setPostedBalance(boolean postedBalance) {
        this.postedBalance = postedBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
