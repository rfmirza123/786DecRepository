package com.qa2.hubspot2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa2.hubspot2.base.BasePage;
import com.qa2.hubspot2.utils.Constants;
import com.qa2.hubspot2.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	
	//1.First thing to remember: Maintain the By locators--called object Repository (OR)
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//2.Create constructor... of page class	
	public LoginPage(WebDriver driver){
		this.driver = driver; 					//You have to add WebDriver driver above for this to work
		elementUtil = new ElementUtil (this.driver);
	}
	
	//3.have to create Page Action 
	
	public String getLoginPageTitle(){
		return elementUtil.WaitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 20);
	}	
	public boolean verifySignUpLink(){
		return elementUtil.doIsDisplayed(signUpLink);
	}
	public HomePage doLogin(String username, String password){
		elementUtil.waitforElementtoBeVisible(this.username, 15);
		
		elementUtil.doSendKeys(this.username, username);
		
		elementUtil.waitforElementtoBeVisible(this.password, 10);		
		elementUtil.doSendKeys(this.password, password);
		
		elementUtil.doClick(this.loginButton);
		
		
		
		
		return new HomePage(driver);	//While doing HomePageTest we replace void with return new & Pass a driver 
											 	
		
	}	
	
}
