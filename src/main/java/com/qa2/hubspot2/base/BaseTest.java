package com.qa2.hubspot2.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa2.hubspot2.pages.LoginPage;

public class BaseTest {
	
	public WebDriver driver;			//WebDriver class Reference created for the Object
	
	public BasePage basePage;			//BasePage class Reference created and Object created inside setUp method
	public LoginPage loginPage;		//LoginPage class Reference created for the Object
	public Properties prop;
	
	@BeforeTest 
	public void setUp(){			
		basePage = new BasePage();					//Object created
		prop =basePage.init_prop();					//Added this and add prop=	
		driver = basePage.init_driver(prop);		//using Ref(driver) from above we call init_driver
		loginPage = new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
