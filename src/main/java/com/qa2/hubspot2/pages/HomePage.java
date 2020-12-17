package com.qa2.hubspot2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa2.hubspot2.base.BasePage;
import com.qa2.hubspot2.utils.Constants;
import com.qa2.hubspot2.utils.ElementUtil;

public class HomePage extends BasePage {
	
	private WebDriver driver;

	
	By header = By.xpath("//i18n-string[@data-key='app.header.title']");
	By accountName = By.xpath("//img[@class=' nav-avatar ']");
	
	public HomePage(WebDriver driver){				//Create Constructor
		this.driver = driver;
		elementUtil = new ElementUtil (this.driver);
	}
	//create Page Action
	public String getHomePageTitle(){
		return elementUtil.WaitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 20);
	}
	//verify Header
	public String getHomePageHeaderText(){
		if(elementUtil.doIsDisplayed(header)){
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	public String getLoggerInUser(){
		if(elementUtil.doIsDisplayed(accountName)){
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
}
