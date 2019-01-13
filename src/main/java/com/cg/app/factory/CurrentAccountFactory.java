package com.cg.app.factory;

import com.cg.app.pojo.account.CurrentAccount;

public final class CurrentAccountFactory {
	
		private static CurrentAccountFactory factory = new CurrentAccountFactory();

		private CurrentAccountFactory() {
			
		}
		
		public static CurrentAccountFactory getInstance() {
			return factory;
		}

		public CurrentAccount createNewCurrentAccount(String accountHolderName, double accountBalance, double odLimit) {
			return new CurrentAccount(accountHolderName, accountBalance, odLimit);
		}
	}

