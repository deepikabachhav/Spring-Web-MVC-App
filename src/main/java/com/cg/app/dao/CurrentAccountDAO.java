package com.cg.app.dao;

import java.util.List;

import com.cg.app.pojo.account.CurrentAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;

public interface CurrentAccountDAO {

	CurrentAccount createNewAccount(CurrentAccount account);

	double checkCurrentBalance(int accountNumber) throws AccountNotFoundException;

	CurrentAccount getAccountById(int accountNumber)throws AccountNotFoundException;

	CurrentAccount deleteAccount(int accountNumber);

	List<CurrentAccount> getAllCurrentAccount() ;

	void updateBalance(int accountNumber, double currentBalance);

	CurrentAccount searchAccountByAccountHoldername(String accountHolderName)throws AccountNotFoundException;

	List<CurrentAccount> searchAccountByAccountBalance(double minimumBalance, double maximumBalance);

	CurrentAccount updateAccount(CurrentAccount account)throws AccountNotFoundException;

	List<CurrentAccount> sort(int choice, int sortBy);
}
