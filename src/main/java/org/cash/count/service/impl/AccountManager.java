/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.cash.count.constant.AccountType;
import org.cash.count.dto.AccountDto;
import org.cash.count.model.Account;
import org.cash.count.repository.AccountRepository;
import org.cash.count.service.IAccountManager;

/**
 *
 * @author rafael
 */
public class AccountManager implements IAccountManager {
    
    private final AccountRepository accountRepository;
    
    /**
     * Public constructor
     * 
     * @param accountRepository 
     */
    public AccountManager(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(AccountDto account) {
        Optional<Account> parentAccountWrapper = accountRepository
                            .findById(account.getParentId());
        
        Account parentAccount = parentAccountWrapper.orElseThrow(NoSuchElementException::new);
        
        Account accountEntity = new Account();
        accountEntity.setId(account.getId());
        accountEntity.setName(account.getName());
        accountEntity.setDescription(account.getDescription());
        accountEntity.setIncreasedBy(parentAccount.getIncreasedBy());
        accountEntity.setParentId(parentAccount.getId());
        accountEntity.setBalance(BigDecimal.ZERO);
        
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountDto findById(int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(AccountDto account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disable(int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
