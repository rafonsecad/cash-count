/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import org.cash.count.constant.AccountType;
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
    
    /**
     * @see org.cash.count.service.ITransferService#transfer(int, int, java.lang.String) 
     */
    @Override
    public void transfer(int debitAccountId, int creditAccountId, String amount) {
        Optional<Account> debitedAccountWrapper = accountRepository.findById(debitAccountId);
        Optional<Account> creditedAccountWrapper = accountRepository.findById(creditAccountId);
        
        Account debitedAccount = debitedAccountWrapper.orElseThrow(IllegalStateException::new);
        Account creditedAccount = creditedAccountWrapper.orElseThrow(IllegalStateException::new);
        
        BigDecimal debitedAccountBalance = debitedAccount.getBalance();
        if (debitedAccount.getIncreasedBy() == AccountType.DEBIT){
            debitedAccountBalance = debitedAccountBalance.add(new BigDecimal(amount));
        } else{
            debitedAccountBalance = debitedAccountBalance.subtract(new BigDecimal(amount));
        }
        
        BigDecimal creditedAccountBalance = creditedAccount.getBalance();
        if (creditedAccount.getIncreasedBy() == AccountType.CREDIT){
            creditedAccountBalance = creditedAccountBalance.add(new BigDecimal(amount));
        } else{
            creditedAccountBalance = creditedAccountBalance.subtract(new BigDecimal(amount));
        }
        
        debitedAccount.setBalance(debitedAccountBalance);
        creditedAccount.setBalance(creditedAccountBalance);
        
        accountRepository.save(debitedAccount);
        accountRepository.save(creditedAccount);
    }
    
}
