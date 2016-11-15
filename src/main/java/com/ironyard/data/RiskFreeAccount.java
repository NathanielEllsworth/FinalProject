package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;





}
