package com.cg.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.app.pojo.account.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;
import com.cg.app.service.SavingsAccountService;

@Controller
public class SavingsAccountController {

	@Autowired
	private SavingsAccountService service;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/addNewAccount")
	public String addNewAccount(Map map) {
		map.put("account", new SavingsAccount());
		return "addNewAccount";
	}

	@RequestMapping(value = "/createNewAccount", method = RequestMethod.GET)
	public String createNewAccount(@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance, @RequestParam("salaried") String sal, Model model) {
		boolean salary = sal.equalsIgnoreCase("Yes") ? true : false;
		SavingsAccount accounts = service.createNewAccount(accountHolderName, accountBalance, salary);
		model.addAttribute("accounts", accounts);
		return "redirect:getAllAccountDetails";
	}

	@RequestMapping("/getAllAccountDetails")
	public String getAllAcounts(Model model) {
		List<SavingsAccount> accounts = service.getAllSavingsAccount();
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}

	@RequestMapping(value = "/searchForm")
	public String searchAccount() {
		return "searchForm";
	}

	@RequestMapping(value = "/searchByAccountNumber", method = RequestMethod.GET)
	public String searchAccountByNumber() {
		return "SearchByAccountNumber";
	}

	@RequestMapping(value = "/searchByAccountNumber", method = RequestMethod.POST)
	public String searchAccountByAccNumber(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}

	@RequestMapping(value = "/search")
	public String searchAccountByNameOfHolder() {
		return "searchForm";
	}

	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	public String searchAccountByName() {
		return "SearchByName";
	}

	@RequestMapping(value = "/searchByName", method = RequestMethod.POST)
	public String searchAccountByAccountHolderName(@RequestParam("txtAccountHolderName") String accountHolderName,
			Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.searchAccountByAccountHoldername(accountHolderName);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}

	@RequestMapping(value = "/updateAccount")
	public String updateForm() {
		return "SearchForUpdate";
	}

	@RequestMapping(value = "/searchForUpdate")
	public String updateAccount(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "UpdateDetails";
	}

	@RequestMapping(value = "/updateAccountDB")
	public String updateAccount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("accountHolderName") String accountHolderName, @RequestParam("salary") String sal,
			Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		boolean salary = sal.equalsIgnoreCase("Yes") ? true : false;
		savingsAccount.getBankAccount().setAccountHolderName(accountHolderName);
		savingsAccount.setSalary(salary);
		service.updateAccount(savingsAccount);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}

	@RequestMapping(value = "/closeAccount")
	public String closeAccount() {
		return "CloseAccount";
	}

	@RequestMapping(value = "/deleteAccount")
	public String deleteAccount(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.deleteAccount(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "redirect:getAllAccountDetails";
	}

	@RequestMapping(value = "/getCurrentAccount")
	public String checkBalance() {
		return "CheckCurrentBalance";
	}

	@RequestMapping(value = "/checkBalance")
	public String getCurrentBalance(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		double currentBalance = service.checkCurrentBalance(accountNumber);
		model.addAttribute("account", currentBalance);
		return "display";
	}

	@RequestMapping(value = "/withdraw")
	public String withdraw() {
		return "Withdraw";
	}

	@RequestMapping(value = "/withdrawAmount")
	public String withdrawAmount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("amount") double amountToWithdraw, Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.withdraw(savingsAccount, amountToWithdraw);
		model.addAttribute("account", savingsAccount);
		return "DisplayWithdraw";
	}

	@RequestMapping(value = "/deposit")
	public String deposit() {
		return "Deposit";
	}

	@RequestMapping(value = "/depositAmount")
	public String depositAmount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("amount") double amountToDeposit, Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.deposit(savingsAccount, amountToDeposit);
		model.addAttribute("account", savingsAccount);
		return "DisplayDeposit";
	}

	@RequestMapping("fundTransfer")
	public String fundtransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber,
			@RequestParam("amount") double amountToTransfer, Model model) {
		return null;

	}
}
