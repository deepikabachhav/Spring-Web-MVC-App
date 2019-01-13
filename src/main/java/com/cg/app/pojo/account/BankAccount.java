package com.cg.app.pojo.account;

public class BankAccount {
	private  int accountNumber;
	private double accountBalance;
	private String accountHolderName;
	private String type;
	//private static int accountId;

	/*static {
		accountId = 100;
	}
*/
	public BankAccount(String accountHolderName, double accountBalance) {
		//accountNumber = ++accountId;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}

	public BankAccount(String accountHolderName) {
		//accountNumber = ++accountId;
		this.accountHolderName = accountHolderName;
	}

	public BankAccount(int accountNumber, String accountHolderName, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}
	
	public BankAccount(int accountNumber, String accountHolderName, double accountBalance,String type) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
		this.type=type;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String setAccountHolderName(String accountHolderName) {
		return this.accountHolderName = accountHolderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}
	
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance
				+ ", accountHolderName=" + accountHolderName + "]";
	}


}
