package com.cg.app.factory;

import com.cg.app.pojo.account.SavingsAccount;

public final class SavingsAccountFactory {
	
	private static SavingsAccountFactory factory = new SavingsAccountFactory();

	private SavingsAccountFactory() {
		
	}
	
	public static SavingsAccountFactory getInstance() {
		return factory;
	}

	public SavingsAccount createNewSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		return new SavingsAccount(accountHolderName, accountBalance, salary);
	}
}
