package com.cg.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.dao.CurrentAccountDAO;
import com.cg.app.dao.SavingsAccountDAO;
import com.cg.app.factory.SavingsAccountFactory;
import com.cg.app.factory.CurrentAccountFactory;
import com.cg.app.pojo.account.CurrentAccount;
import com.cg.app.pojo.account.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;
import com.cg.app.pojo.exception.InsufficientFundsException;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{
	
	private CurrentAccountFactory factory;
	@Autowired
	private CurrentAccountDAO currentAccountDAO;
	
	public CurrentAccountServiceImpl( CurrentAccountDAO currentAccountDAO) {
		factory = CurrentAccountFactory.getInstance();
	}

	public CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odLimit ) {
		CurrentAccount account = factory.createNewCurrentAccount(accountHolderName, accountBalance, odLimit);
		return currentAccountDAO.createNewAccount(account);
	}

	public double checkCurrentBalance(int accountNumber) throws AccountNotFoundException {
		return currentAccountDAO.checkCurrentBalance(accountNumber);
	}

	public CurrentAccount getAccountById(int accountNumber) throws AccountNotFoundException {
		return currentAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public CurrentAccount searchAccountByAccountHoldername(String accountHolderName) throws AccountNotFoundException {
		return currentAccountDAO.searchAccountByAccountHoldername(accountHolderName);
	}

	@Override
	public CurrentAccount deleteAccount(int accountNumber) throws AccountNotFoundException {
		return currentAccountDAO.deleteAccount(accountNumber);
	}

	public List<CurrentAccount> getAllCurrentAccount() {
		return currentAccountDAO.getAllCurrentAccount();
	}

	@Transactional
	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount) {
		withdraw(sender, amount);
		deposit(receiver, amount);	
	}

	@Transactional
	public void deposit(CurrentAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();
		currentBalance += amount;
		currentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		
	}

	@Transactional
	public void withdraw(CurrentAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if(currentBalance >=amount) {
			currentBalance -= amount;
			currentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		}
		else
			throw new InsufficientFundsException("Insufficient Funds!");
		
	}

	@Override
	public List<CurrentAccount> searchAccountByAccountBalance(double minimumBalance, double maximumBalance) {
		return currentAccountDAO.searchAccountByAccountBalance(minimumBalance, maximumBalance);
	}

	@Override
	public CurrentAccount updateAccount(CurrentAccount account) throws AccountNotFoundException {
		return currentAccountDAO.updateAccount(account);

	}

	@Override
	public List<CurrentAccount> sort(int choice, int sortBy) {
		return currentAccountDAO.sort(choice, sortBy);
	}

}
