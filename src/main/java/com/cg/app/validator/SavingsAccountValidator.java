package com.cg.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cg.app.pojo.account.SavingsAccount;
@Component
public class SavingsAccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
	SavingsAccount savingsAccount=(SavingsAccount) target;
	if(savingsAccount.getBankAccount().getAccountHolderName().length() <2) {
		errors.rejectValue("accountHolderName", "accountHolderName.length", "Account Holder Name Name must have 2 characters");
	}
		
	}

}
