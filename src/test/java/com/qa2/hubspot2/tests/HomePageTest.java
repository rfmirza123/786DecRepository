package com.qa2.hubspot2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa2.hubspot2.base.BaseTest;
import com.qa2.hubspot2.pages.HomePage;
import com.qa2.hubspot2.utils.Constants;

public class HomePageTest extends BaseTest {
	
	HomePage homePage;
	
	@BeforeClass
	public void homeSetup(){
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));			
	}	

	@Test(priority=3)
	public void verifyHomePageTitle(){
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page title is : " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "Home Page title is not matching...");
	}
	@Test(priority=1)
	public void verifyHomePageHeader(){
		String header = homePage.getHomePageHeaderText();
		System.out.println("HomePage header is : " + header );
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);		
		
	}
	@Test(priority=2)
	public void verifyLoggedInUserTest(){
		String loggedInUser = homePage.getLoggerInUser();
		System.out.println("LoggedIn user is : " + loggedInUser);
		//Assert.assertEquals(loggedInUser, "", "logged in user Not matched...");
		Assert.assertEquals(loggedInUser, prop.getProperty("accountname"), "logged in user Not matched...");
		
	}	
}