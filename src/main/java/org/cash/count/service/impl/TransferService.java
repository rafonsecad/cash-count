/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import org.cash.count.model.Account;
import org.cash.count.repository.AccountRepository;
import org.cash.count.service.ITransferService;

/**
 *
 * @author rafael
 */
public class TransferService implements ITransferService{

    private AccountRepository accountRepository;
    
    public TransferService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    
    @Override
    public void transfer(int debitAccountId, int creditAccountId, String value) {
        Optional<Account> debitAccountWrapper = accountRepository.findById(debitAccountId);
        Optional<Account> creditAccountWrapper = accountRepository.findById(creditAccountId);
        
        Account debitAccount = debitAccountWrapper.orElseThrow(IllegalStateException::new);
        Account creditAccount = creditAccountWrapper.orElseThrow(IllegalStateException::new);
        
        BigDecimal debitAccountBalance = debitAccount.getBalance().add(new BigDecimal(value));
        BigDecimal creditAccountBalance = creditAccount.getBalance().add(new BigDecimal(value));
        
        debitAccount.setBalance(debitAccountBalance);
        creditAccount.setBalance(creditAccountBalance);
        
        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);
    }
    
}
