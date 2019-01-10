package com.cg.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.app.pojo.account.SavingsAccount;
import com.cg.app.service.SavingsAccountService;

@Controller
public class SavingsAccountController {
	
	  @Autowired 
	  private SavingsAccountService service;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	  @RequestMapping("/getAllAccountDetails") 
	  public String getAllAcounts(Model model) {
	  List<SavingsAccount> accounts = service.getAllSavingsAccount();
	  model.addAttribute("accounts",accounts);
	  return "AccountDetails";
	 }
	 
	  @RequestMapping("/getAllAccountDetails") 
	  public String updateAccount(Model model) {
		return null;
}
}