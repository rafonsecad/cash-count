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
    private BigDecimal balance;
    private AccountType type;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
