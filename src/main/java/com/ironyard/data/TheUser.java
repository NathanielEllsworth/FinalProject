package com.ironyard.data;


import javax.persistence.*;
import java.util.Set;

/**
 * The User Class is needed to link each unique individual to their respective account(s)
 *
 * Created by nathanielellsworth on 11/4/16.
 */

@Entity
public class TheUser {
    private String username;
    private String password;
    private String displayName;




    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Account> otherAccounts;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> abilities;

    public Set<Account> getOtherAccounts() {
        return otherAccounts;
    }

    public void setOtherAccounts(Set<Account> otherAccounts) {
        this.otherAccounts = otherAccounts;
    }

    public Set<Permission> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Permission> abilities) {
        this.abilities = abilities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public TheUser() {
    }

    public TheUser(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





}
