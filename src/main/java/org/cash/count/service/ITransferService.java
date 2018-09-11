/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cash.count.service;

/**
 *
 * @author rafael
 */
public interface ITransferService {
    
    void transfer (int debitAccountId, int creditAccountId, String value);
}
