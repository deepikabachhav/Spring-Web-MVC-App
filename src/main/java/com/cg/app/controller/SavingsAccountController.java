package com.cg.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.app.pojo.account.CurrentAccount;
import com.cg.app.pojo.account.SavingsAccount;
import com.cg.app.pojo.exception.AccountNotFoundException;
import com.cg.app.service.CurrentAccountService;
import com.cg.app.service.SavingsAccountService;

@Controller
public class SavingsAccountController {

	@Autowired
	private SavingsAccountService savingsAccountService;
	@Autowired
	private CurrentAccountService currentAccountService;
	/*
	 * @Autowired private SavingsAccountValidator validator;
	 */
	int result= 0;
	private boolean sort = false;

	@RequestMapping("/addNewAccount")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/home")
	public String homeSavings() {
		return "home";
	}
	
	@RequestMapping(value="/Account", method=RequestMethod.POST)
	public String homeCurrent() {
		return "home";
	}

	@RequestMapping(value="/savingsAccount")
	public String addNewAccount(Map map) {
		map.put("account", new SavingsAccount());
		return "addNewAccount";
	}
	
	@RequestMapping(value="/currentAccount")
	public String addNewCurrentAccount(Map map) {
		map.put("account", new CurrentAccount());
		return "AddNewCurrentAccount";
	}
	
	@RequestMapping(value = "/createNewAccount", method = RequestMethod.GET)
	public String createNewAccount(@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance, @RequestParam("salaried") String sal, Model model/* ,BindingResult result*/) {
		/*validator.validate(accountHolderName,result);
		if(result.hasErrors()) {
			return "addNewAccount";
		}*/
		boolean salary = sal.equalsIgnoreCase("Yes") ? true : false;
		SavingsAccount accounts = savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		model.addAttribute("accounts", accounts);
		return "redirect:getAllAccountDetails";
	}
	
	@RequestMapping(value ="/createNewCurrentAccount", method = RequestMethod.GET)
	public String createNewCurrentAccount(@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance, @RequestParam("odLimit") double odLimit, Model model/* ,BindingResult result*/) {
		CurrentAccount accounts = currentAccountService.createNewAccount(accountHolderName, accountBalance, odLimit);
		model.addAttribute("accounts", accounts);
		return "redirect:getAllAccountDetails";
	}

	@RequestMapping("/getAllAccountDetails")
	public String getAllAcounts(Model model) {
		List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}

	@RequestMapping("/searchForm")
	public String searchAccount() {
		return "searchForm";
	}

	@RequestMapping(value = "/searchByAccountNumber", method = RequestMethod.GET)
	public String searchAccountByNumber() {
		return "SearchByAccountNumber";
	}

	@RequestMapping(value="/searchByAccountNumber", method = RequestMethod.POST)
	public String searchAccountByAccNumber(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}

	@RequestMapping("/search")
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
		SavingsAccount savingsAccount = savingsAccountService.searchAccountByAccountHoldername(accountHolderName);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}

	@RequestMapping("/updateAccount")
	public String updateForm() {
		return "SearchForUpdate";
	}

	@RequestMapping("/searchForUpdate")
	public String updateAccount(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "UpdateDetails";
	}

	@RequestMapping("/updateAccountDB")
	public String updateAccount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("accountHolderName") String accountHolderName, @RequestParam("salary") String sal,
			Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
		boolean salary = sal.equalsIgnoreCase("Yes") ? true : false;
		savingsAccount.getBankAccount().setAccountHolderName(accountHolderName);
		savingsAccount.setSalary(salary);
		savingsAccountService.updateAccount(savingsAccount);
		model.addAttribute("account", savingsAccount);
		return "AccountCurrentDetails";
	}

	@RequestMapping("/closeAccount")
	public String closeAccount() {
		return "CloseAccount";
	}

	@RequestMapping("/deleteAccount")
	public String deleteAccount(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.deleteAccount(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "DisplayDelete";
	}

	@RequestMapping("/getCurrentAccount")
	public String checkBalance() {
		return "CheckCurrentBalance";
	}

	@RequestMapping("/checkBalance")
	public String getCurrentBalance(@RequestParam("txtAccountNumber") int accountNumber, Model model)
			throws AccountNotFoundException {
		double currentBalance = savingsAccountService.checkCurrentBalance(accountNumber);
		model.addAttribute("account", currentBalance);
		return "display";
	}

	@RequestMapping("/withdraw")
	public String withdraw() {
		return "Withdraw";
	}

	@RequestMapping("/withdrawAmount")
	public String withdrawAmount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("amount") double amountToWithdraw, Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.withdraw(savingsAccount, amountToWithdraw);
		model.addAttribute("account", savingsAccount);
		return "DisplayWithdraw";
	}

	@RequestMapping("/deposit")
	public String deposit() {
		return "Deposit";
	}

	@RequestMapping("/depositAmount")
	public String depositAmount(@RequestParam("txtAccountNumber") int accountNumber,
			@RequestParam("amount") double amountToDeposit, Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.deposit(savingsAccount, amountToDeposit);
		model.addAttribute("account", savingsAccount);
		return "DisplayDeposit";
	}
	
	@RequestMapping("/fundTransfer")
	public String fundTransfer() {
		return "FundTransfer";
	}

	@RequestMapping("/fundtransferAmount")
	public String fundtransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber,
			@RequestParam("amount") double amountToTransfer, Model model) throws AccountNotFoundException {
		SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
		SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amountToTransfer);
		return "DisplayDeposit";
	}
	
	@RequestMapping("/sortAccounts")
	public String sort() {
		return "redirect:getAllAccountDetails";
	}
	
	@RequestMapping("/sortByAccountNumber")
	public String sortByAccountNumber(Model model) {
		sort =!sort;
		 result = sort ? 1 : -1;
		 List<SavingsAccount> accountList = new ArrayList<SavingsAccount>();
			accountList = savingsAccountService.getAllSavingsAccount();
			Collections.sort(accountList ,new Comparator<SavingsAccount>(){
					public int compare(SavingsAccount arg0, SavingsAccount arg1) {
					return  result*(arg0.getBankAccount().getAccountNumber()-(arg1.getBankAccount().getAccountNumber()));
					}	
			});
			model.addAttribute("accounts", accountList);
			return "AccountDetails";
	}
		
	@RequestMapping("/sortByName")
	public String sortByAccountHolderName(Model model) {
		sort =!sort;
		 result = sort ? 1 : -1;
		 List<SavingsAccount> accountList = new ArrayList<SavingsAccount>();
			accountList = savingsAccountService.getAllSavingsAccount();
			Collections.sort(accountList ,new Comparator<SavingsAccount>(){
					public int compare(SavingsAccount arg0, SavingsAccount arg1) {
						return  result*arg0.getBankAccount().getAccountHolderName().compareTo
								(arg1.getBankAccount().getAccountHolderName());
					}	
			});
			model.addAttribute("accounts", accountList);
			return "AccountDetails";
	}
	
	@RequestMapping("/sortByAccountBalance")
	public String sortByAccountBalance(Model model) {
		sort =!sort;
		 result = sort ? 1 : -1;
		 List<SavingsAccount> accountList = new ArrayList<SavingsAccount>();
			accountList = savingsAccountService.getAllSavingsAccount();
			Collections.sort(accountList ,new Comparator<SavingsAccount>(){
					public int compare(SavingsAccount arg0, SavingsAccount arg1) {
						return (int) (result*(arg0.getBankAccount().getAccountBalance()-
								(arg1.getBankAccount().getAccountBalance())));
					}	
			});
			model.addAttribute("accounts", accountList);
			return "AccountDetails";
	}
	
	@RequestMapping("/sortBySalary")
	public String sortBySalary(Model model) {
		sort =!sort;
		 result = sort ? 1 : -1;
		 List<SavingsAccount> accountList = new ArrayList<SavingsAccount>();
			accountList = savingsAccountService.getAllSavingsAccount();
			Collections.sort(accountList ,new Comparator<SavingsAccount>(){
					public int compare(SavingsAccount arg0, SavingsAccount arg1) {
						if(arg0.isSalary())
							return 1*result;
						else
							return -1*result;
					}	
			});
			model.addAttribute("accounts", accountList);
			return "AccountDetails";
	}
}

