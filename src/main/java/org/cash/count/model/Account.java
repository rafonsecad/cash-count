/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.model;

import java.math.BigDecimal;
import org.cash.count.constant.AccountType;

/**
 *
 * @author rafael
 */
public class Account {
    
    private int id;
    private String name;
    private String description;
    private BigDecimal balance;
    private AccountType increasedBy;
    private int parentId;
    private boolean disabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getIncreasedBy() {
        return increasedBy;
    }

    public void setIncreasedBy(AccountType increasedBy) {
        this.increasedBy = increasedBy;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
