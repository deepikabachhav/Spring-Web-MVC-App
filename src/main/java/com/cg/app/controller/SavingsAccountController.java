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
	
	@RequestMapping(value="/addNewAccount") 
	public String addNewAccount(Map map) {
		map.put("account",new SavingsAccount());
		return "addNewAccount";	
	}
	 
	 @RequestMapping(value="/createNewAccount" , method=RequestMethod.GET) 
		public String createNewAccount(@RequestParam("accountHolderName") String accountHolderName,
										@RequestParam("accountBalance") double accountBalance,
										@RequestParam("salaried") String sal,Model model) {
		 boolean salary=sal.equalsIgnoreCase("Yes")?true:false;
		 SavingsAccount accounts=service.createNewAccount(accountHolderName, accountBalance, salary);
		 model.addAttribute("accounts",accounts);
		return "redirect:getAllAccountDetails";
	}
	// @RequestMapping(value="/createNewAccount" , method=RequestMethod.POST) 
	 
	  @RequestMapping("/getAllAccountDetails") 
	  public String getAllAcounts(Model model) {
	  List<SavingsAccount> accounts = service.getAllSavingsAccount();
	  model.addAttribute("accounts",accounts);
	  return "AccountDetails";
	 }
	 
	  @RequestMapping(value="/searchForm") 
	  public String searchAccount() {
		 return "searchForm"; 
	  }  
	  
	  @RequestMapping(value="/search") 
	  public String searchAccountById(@RequestParam("txtAccountNumber") int accountNumber,Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount=service.getAccountById(accountNumber);
		model.addAttribute("account",savingsAccount);
		return "AccountDetails";
	  }
	  
	 @RequestMapping(value="/updateAccount") 
	  public String updateForm(){
		  return "searchForm"; 
	  }
	 
	  @RequestMapping(value="/updateAccount", method=RequestMethod.POST) 
	  public String updateAccount(@RequestParam("accountHolderName") String accountHolderName,@RequestParam("salaried") String sal,Model model) {
		  
		  
		  return "updateSAccount";
	}
}
	 
}