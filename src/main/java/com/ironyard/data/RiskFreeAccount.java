package com.ironyard.data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.persistence.Entity;
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
    private double treasuryRate;                  // only for savings accounts
    private double bankRate;
    private double rateDifference; //percent % difference between -treasury rate-  and -bank rate-
    private double postedBalance;            //**posted balance is the same as Balance**



    // good ole JPA
    @Id
    @GeneratedValue(generator = "customForeignGenerator")
    @GenericGenerator(
            name = "customForeignGenerator",
            strategy = "foreign",
            parameters = @Parameter( name = "property", value = "User")
    )
    private long id;

    @OneToOne(mappedBy = "riskFreeAccount")
    @PrimaryKeyJoinColumn
    public User user;



    public RiskFreeAccount() {

    }


    public RiskFreeAccount(String date, String type, String description, double debit, double credit, String term, double treasuryRate, double bankRate, double rateDifference, double postedBalance) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
        this.term = term;
        this.treasuryRate = treasuryRate;
        this.bankRate = bankRate;
        this.rateDifference = rateDifference;
        this.postedBalance = postedBalance;
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

    public double getTreasuryRate() {
        return treasuryRate;
    }

    public void setTreasuryRate(double treasuryRate) {
        this.treasuryRate = treasuryRate;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
