/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.cash.count.constant.AccountType;
import org.cash.count.model.Account;
import org.cash.count.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author rafael
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

    private TransferService transferService;
    
    @Mock
    private AccountRepository accountRepository;
    
    @Captor
    private ArgumentCaptor<Account> accountCaptor;
    
    @Test
    public void shouldTransferBtwAccounts() {
        
        Account debitAccount = new Account();
        debitAccount.setBalance(new BigDecimal("15000"));
        debitAccount.setType(AccountType.DEBIT);
        
        Account creditAccount = new Account();
        creditAccount.setBalance(new BigDecimal("3000"));
        creditAccount.setType(AccountType.CREDIT);
        
        Account resultingDebitAccount = new Account();
        resultingDebitAccount.setBalance(new BigDecimal("25000"));
        resultingDebitAccount.setType(AccountType.DEBIT);
        
        Account resultingCreditAccount = new Account();
        resultingCreditAccount.setBalance(new BigDecimal("13000"));
        resultingCreditAccount.setType(AccountType.CREDIT);
        
        when(accountRepository.findById(1)).thenReturn(Optional.of(debitAccount));
        when(accountRepository.findById(3)).thenReturn(Optional.of(creditAccount));
        
        when(accountRepository.save(any()))
                .thenReturn(resultingDebitAccount)
                .thenReturn(resultingCreditAccount);
        
        transferService = new TransferService(accountRepository);
        transferService.transfer(1, 3, "10000");
        
        verify(accountRepository, times(2)).save(accountCaptor.capture());
        
        List<Account> accountsCaptured = accountCaptor.getAllValues();
        Account debitAccountCaptured = accountsCaptured.get(0);
        Account creditAccountCaptured = accountsCaptured.get(1);
        
        assertThat(debitAccountCaptured).isNotNull();
        assertThat(debitAccountCaptured.getType()).isEqualTo(resultingDebitAccount.getType());
        assertThat(debitAccountCaptured.getBalance()).isEqualTo(resultingDebitAccount.getBalance());
        
        assertThat(creditAccountCaptured).isNotNull();
        assertThat(creditAccountCaptured.getType()).isEqualTo(resultingCreditAccount.getType());
        assertThat(creditAccountCaptured.getBalance()).isEqualTo(resultingCreditAccount.getBalance());
    }
    
}
