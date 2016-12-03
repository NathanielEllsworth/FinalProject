package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * The permission class is allowing or denying access to the user the ability to purchase Treasury Bills.
 * In order for the user to purchase Treasury Bills they must have, by law, a minimum of $1,000 US Dollars.
 *
 * Created by nathanielellsworth on 11/4/16.
 */

@Entity
public class Permission {
    private String key;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Permission() {
    }

    public Permission(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
