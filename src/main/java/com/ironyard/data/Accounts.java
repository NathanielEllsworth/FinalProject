package com.ironyard.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Each User needs to be able to have their own account(s)
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@Entity
public class Accounts {

    private String accountName;   // father/son joint account
    private String accountType;   //checking/savings


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Transactions> accountHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> abilities;

    public Set<Transactions> getAccountHistory(){return accountHistory;}

    public void setAccountHistory(Set<Transactions> accountHistory){this.accountHistory = accountHistory;}

    public Set<Permission> getAbilities(){return abilities;}

    public void setAbilities(Set<Permission> abilities){this.abilities = abilities;}

    //private customer account holder

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Accounts(){

    }

    public Accounts(String accountName, String accountType) {
        this.accountName = accountName;
        this.accountType = accountType;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
