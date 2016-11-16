package com.ironyard.data;


import javax.persistence.*;
import java.util.Set;

/**
 * The User Class is needed to link each unique individual to their respective account(s)
 *
 * Created by nathanielellsworth on 11/4/16.
 */

@Entity
public class User {
    private String username;
    private String password;
    private String displayName;




    @OneToOne
    @PrimaryKeyJoinColumn
    private RiskFreeAccount riskFreeAccount;




    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> approval;

    public Set<Permission> getApproval(){return approval;}

    public void setApproval(Set<Permission> approval){this.approval = approval;}




//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Accounts> personalAccounts;

//    public Set<Accounts> getPersonalAccounts(){return personalAccounts;} // ******************   Favs   =   PersonalAccounts   *******************

//    public void setPersonalAccounts(Set<Accounts> personalAccounts){this.personalAccounts = personalAccounts;}






    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public User(){

    }


    public User(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getDisplayName() {return displayName;}

    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}
}
