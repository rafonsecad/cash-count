/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service.impl;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.cash.count.constant.AccountType;
import org.cash.count.dto.AccountDto;
import org.cash.count.model.Account;
import org.cash.count.repository.AccountRepository;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * {@link org.cash.count.service.impl.AccountManager} unit tests
 * 
 * @author rafael
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountManagerTest {
    
    private AccountManager accountManager;
    
    @Mock
    private AccountRepository accountRepository;
    
    @Captor
    private ArgumentCaptor<Account> accountCaptor;
    
    /**
     * Initialize class
     */
    @Before
    public void setUp(){
        accountManager = new AccountManager(accountRepository);
    }
    
    /**
     * Successful account creation
     */
    @Test
    public void shouldCreateAccount_hasRequiredFields(){
        AccountDto account = new AccountDto();
        account.setId(23);
        account.setName("New Account");
        account.setDescription("Any Description");
        account.setParentId(1);
        
        Account parentAccount = new Account();
        parentAccount.setId(1);
        parentAccount.setIncreasedBy(AccountType.CREDIT);
        
        Account savedAccount = new Account();
        savedAccount.setId(23);
        savedAccount.setName("New Account");
        savedAccount.setDescription("Any Description");
        savedAccount.setParentId(1);
        savedAccount.setIncreasedBy(AccountType.CREDIT);
        savedAccount.setBalance(BigDecimal.ZERO);
        
        when(accountRepository.findById(1)).thenReturn(Optional.of(parentAccount));
        when(accountRepository.save(any())).thenReturn(savedAccount);
        
        accountManager.create(account);
        
        verify(accountRepository, times(1)).save(accountCaptor.capture());
        
        Account capturedAccount = accountCaptor.getValue();
        assertThat(capturedAccount.getBalance()).isEqualTo(savedAccount.getBalance());
    }
    
    /**
     * should not create the account. the id is missing or cannot be zero
     */
    @Test
    public void shouldNotCreateAccount_missingId(){
        AccountDto account = new AccountDto();
        account.setName("New Account");
        account.setDescription("Any Description");
        account.setParentId(1);
        
        try{
            accountManager.create(account);
            fail();
        } catch(NoSuchElementException e){
            assertThat(e.getMessage()).isEqualTo("Missing Account Id");
        }
        
        verifyZeroInteractions(accountRepository);
    }
    
    /**
     * Should not create account. Missing name
     */
    @Test
    public void shouldNotCreateAccount_missingName(){
        AccountDto account = new AccountDto();
        account.setId(23);
        account.setDescription("Any Description");
        account.setParentId(1);
        
        try{
            accountManager.create(account);
            fail();
        } catch(NoSuchElementException e){
            assertThat(e.getMessage()).isEqualTo("Missing Account Name");
        }
        
        verifyZeroInteractions(accountRepository);
    }
    
    /**
     * Should not create account. Missing parent account id
     */
    @Test
    public void shouldNotCreateAccount_missingParentAccountId(){
        AccountDto account = new AccountDto();
        account.setId(23);
        account.setName("New Account");
        account.setDescription("Any Description");
        
        try{
            accountManager.create(account);
            fail();
        } catch(NoSuchElementException e){
            assertThat(e.getMessage()).isEqualTo("Missing Parent Account Id");
        }
        
        verifyZeroInteractions(accountRepository);
    }
}
