package com.cg.app.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.pojo.account.CurrentAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;
import com.cg.app.rowmapper.CurrentAccountRowMapper;

@Repository
@Primary
public class CurrentAccountSJDAOImpl implements CurrentAccountDAO {

	@Autowired
	private JdbcTemplate template; 
	private Logger logger = Logger.getLogger(SavingsAccountSJDAOImpl.class.getName());
	
	@Override
	public CurrentAccount createNewAccount(CurrentAccount account) {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[]{account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(),
				false,
				account.getOdLimit(),
				"CA"});
		return account;
	}


	@Override
	public CurrentAccount getAccountById(int accountNumber) throws AccountNotFoundException {
		return template.queryForObject("SELECT * FROM account where account_id=?", new Object[] {accountNumber},new CurrentAccountRowMapper());
	}


	@Override
	public CurrentAccount deleteAccount(int accountNumber) {
		 template.update("DELETE FROM account where account_id=?",new Object[] {accountNumber});
		 return null;
	}


	@Override
	public CurrentAccount searchAccountByAccountHoldername(String accountHolderName) throws AccountNotFoundException {
		return template.queryForObject("SELECT * FROM account where account_hn=?",new Object[] {accountHolderName},new CurrentAccountRowMapper());
	}

	@Override
	public List<CurrentAccount> getAllCurrentAccount() {
		return template.query("SELECT * FROM ACCOUNT",new CurrentAccountRowMapper());
	}

	
	public double checkCurrentBalance(int accountNumber)throws AccountNotFoundException{
		return template.queryForObject("SELECT account_bal FROM account where account_id=?", new Object[] {accountNumber},Double.class);
	}
	
	public void updateBalance(int accountNumber, double currentBalance)  {
		 template.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",new Object[] {currentBalance,accountNumber});

	}

	public List<CurrentAccount> searchAccountByAccountBalance(double minimumBalance, double maximumBalance){
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN ? AND ?",new Object[] {minimumBalance,maximumBalance},new CurrentAccountRowMapper());
	}

	public CurrentAccount updateAccount(CurrentAccount account)throws  AccountNotFoundException {
	 template.update("UPDATE account SET odLimit=?,account_hn=?  WHERE account_id=?",new Object[] {account.getOdLimit(),
			 account.getBankAccount().getAccountHolderName(),
			 account.getBankAccount().getAccountNumber()}
			);
	 return getAccountById(account.getBankAccount().getAccountNumber());
	}

	
	public List<CurrentAccount> sort(int choice, int sortBy)  {
		String query = "";
		switch (choice) {
		case 1:
			if (sortBy == 1)
				query = "SELECT * FROM account ORDER BY account_id";
			else
				query = "SELECT * FROM account ORDER BY account_id DESC";
			break;
		case 2:
			if (sortBy == 1)
				query = "SELECT * FROM account ORDER BY account_hn";
			else
				query = "SELECT * FROM account ORDER BY account_hn DESC";
			break;
		case 3:
			if (sortBy == 1)
				query = "SELECT * FROM account ORDER BY account_bal";
			else
				query = "SELECT * FROM account ORDER BY account_bal DESC";
			break;
		}
		
		return template.query(query, new CurrentAccountRowMapper());
	}


	
	
}
