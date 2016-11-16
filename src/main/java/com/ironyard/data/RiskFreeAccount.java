package com.ironyard.data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * each User's Homepage will contain their 'Risk Free' Account. Each user will only have one of these accounts.
 *
 *
 * Created by nathanielellsworth on 11/14/16.
 */
@Entity
public class RiskFreeAccount {


    private Date date;
    private String type;
    private String description;
    private boolean debit;        // (-)
    private boolean credit;       // (+)
    private String term;                           // only for savings accounts
    private boolean treasuryRate;                  // only for savings accounts
    private boolean bankRate;
    private boolean rateDifference; //percent % difference between -treasury rate-  and -bank rate-
    private boolean postedBalance;            //**posted balance is the same as Balance**



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



    public RiskFreeAccount(Date date, String type, String description, boolean debit, boolean credit, String term, boolean treasuryRate, boolean bankRate, boolean rateDifference, boolean postedBalance) {
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


    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public boolean isDebit() {return debit;}

    public void setDebit(boolean debit) {this.debit = debit;}

    public boolean isCredit() {return credit;}

    public void setCredit(boolean credit) {this.credit = credit;}

    public String getTerm() {return term;}

    public void setTerm(String term) {this.term = term;}

    public boolean isTreasuryRate() {return treasuryRate;}

    public void setTreasuryRate(boolean treasuryRate) {this.treasuryRate = treasuryRate;}

    public boolean isBankRate() {return bankRate;}

    public void setBankRate(boolean bankRate) {this.bankRate = bankRate;}

    public boolean isRateDifference() {return rateDifference;}

    public void setRateDifference(boolean rateDifference) {this.rateDifference = rateDifference;}

    public boolean isPostedBalance() {return postedBalance;}

    public void setPostedBalance(boolean postedBalance) {this.postedBalance = postedBalance;}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    
}
