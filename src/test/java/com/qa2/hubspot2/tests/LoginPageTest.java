package com.qa2.hubspot2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa2.hubspot2.base.BaseTest;
import com.qa2.hubspot2.utils.Constants;

public class LoginPageTest extends BaseTest {	
	
	
	@Test(priority=2) 
	public void verifyLoginPageTitleTest(){		//we already have getTitle in LoginPage so we donot create another  
		String title = loginPage.getLoginPageTitle();		//so we just call by using object reference created: loginPage		
		System.out.println("loginPage title is : " + title);
	
		//As Constants method is static use Constants.LOGIN_PAGE_TITLE (class name.method		
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "login page is not matching...");		
		
	}		
	@Test(priority=1)
	public void verifySignUpLink(){
		Assert.assertTrue(loginPage.verifySignUpLink(),"Sign up link is NOT displayed...");
	}
	@Test(priority=3)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}	
	
}