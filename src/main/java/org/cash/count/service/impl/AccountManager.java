/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.cash.count.dto.AccountCreationDto;
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

    /**
     * @see org.cash.count.service.IAccountManager#create(org.cash.count.dto.AccountDto) 
     */
    @Override
    public void create(AccountCreationDto account) {
        
        if (account.getId() == 0){
            throw new NoSuchElementException("Missing Account Id");
        }
        if (!hasAccountName(account)){
            throw new NoSuchElementException("Missing Account Name");
        }
        if (account.getParentId() == 0){
            throw new NoSuchElementException("Missing Parent Account Id");
        }
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

    private boolean hasAccountName(AccountCreationDto account){
        return Optional.of(account)
                .map(AccountCreationDto::getName)
                .isPresent();
    }
    
    /**
     * @see org.cash.count.service.IAccountManager#findById(int)
     */
    @Override
    public AccountDto findById(int accountId) {
        Optional<Account> accountWrapped = accountRepository.findById(accountId);
        Account account = accountWrapped.orElseThrow(NoSuchElementException::new);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setDescription(account.getDescription());
        accountDto.setParentId(account.getParentId());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }

    @Override
    public void update(AccountCreationDto account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disable(int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
