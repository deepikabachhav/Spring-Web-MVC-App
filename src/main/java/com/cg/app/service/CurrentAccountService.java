package com.cg.app.service;

import java.util.List;

import com.cg.app.pojo.account.CurrentAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;

public interface CurrentAccountService {

	CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odLimit);
	
	double checkCurrentBalance(int accountNumber) throws AccountNotFoundException;

	CurrentAccount getAccountById(int accountNumber)throws AccountNotFoundException;

	CurrentAccount searchAccountByAccountHoldername(String accountHolderName)throws AccountNotFoundException;

	CurrentAccount deleteAccount(int accountNumber)throws AccountNotFoundException;

	List<CurrentAccount> getAllCurrentAccount();

	void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount);

	void deposit(CurrentAccount account, double amount);

	void withdraw(CurrentAccount account, double amount);

	List<CurrentAccount> searchAccountByAccountBalance(double minimumBalance, double maximumBalance);

	CurrentAccount updateAccount(CurrentAccount account)
			throws AccountNotFoundException;

	List<CurrentAccount> sort(int choice, int sortBy);

}
