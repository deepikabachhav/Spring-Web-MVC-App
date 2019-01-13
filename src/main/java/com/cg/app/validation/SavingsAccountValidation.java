
package com.cg.app.validation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.app.pojo.account.SavingsAccount;
import com.cg.app.pojo.exception.InsufficientFundsException;
import com.cg.app.pojo.exception.InvalidInputException;

@Component
@Aspect
public class SavingsAccountValidation {
	private Logger logger = Logger.getLogger(SavingsAccountValidation.class.getName());

	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))")
	public void validateWithdraw(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameters = pjp.getArgs();
		// SavingsAccount savingsAccount = (SavingsAccount) parameters[0];
		// double currentBalance = savingsAccount.getBankAccount().getAccountBalance();
		if ((Double) parameters[1] > 0) {
			pjp.proceed();
			logger.info("Transaction successfull!");
		} else {
			logger.info("Transaction failed!");
			throw new InvalidInputException("Invalid Input !");
		}
	}

	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.deposit(..))")
	public void validateDeposite(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameters = pjp.getArgs();
		if ((Double) parameters[1] > 0) {
			pjp.proceed();
			logger.info("Deposit successfull!");
		}
		else {
			logger.info("Deposit failed!");
		throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	
	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.fundTransfer(..))")
	public void validateFundTransfer(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameters = pjp.getArgs();
		if ((Double) parameters[1] > 0) {
			pjp.proceed();
			logger.info("fundTransfer successfull!");
		}
		else {
			logger.info("fundTransfer failed!");
		throw new InvalidInputException("Invalid Input Amount!");
		}
	}

	/*
	 * 
	 * @AfterThrowing(
	 * pointcut="execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))"
	 * ,throwing="exception") public void validateException(Throwable exception) {
	 * logger.info("Insufficient Funds!"); }
	 */
}
