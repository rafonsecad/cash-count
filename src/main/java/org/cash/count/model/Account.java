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
    private BigDecimal balance;
    private AccountType increasedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
