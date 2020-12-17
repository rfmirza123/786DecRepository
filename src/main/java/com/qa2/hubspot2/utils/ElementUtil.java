package com.qa2.hubspot2.utils;
	
	import java.util.ArrayList;
	import java.util.List;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;	
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class ElementUtil {
		
	//from video 13 this is similar to WebUtil where the methods can be used in other script . LoginPage is again attached to ElementUtil 
	// locator is a command that tells Selenium IDE which elements (say Text Box, Buttons, Check Boxes etc) it needs to operate on.
	//There are 8 explicit locators: id, name, identifier, dom, XPath, link, CSS and UI that Selenium's commands support.	
		
	WebDriver driver;
		
	public ElementUtil(WebDriver driver) {			//We create a constructor with a param, and provide driver that 
		this.driver = driver;						//was built in BrowserUtil see video (32:11)
	}	
		
	public List <WebElement> getElements(By locator) {						//Will return MULTIPLE "Elements"
		List <WebElement> elementList = driver.findElements(locator);
		return elementList;		
	}		
		
	public WebElement getElement(By locator) {								
	WebElement element= null;		
	try {								//try-catch Block
		System.out.println("Locator is : " + locator );
		element = driver.findElement(locator);								//Will return SINGLE "Element"
		System.out.println("WebElement is created Succefully " + locator);
	}
	catch(Exception e) {	
		System.out.println("Some exception occured with this locator : " + locator);
	}
	return element;
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public String doGetText(By locator){
	return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
	return getElement(locator).isDisplayed();
	}	
	
//********DropDown Utils***********Added below three method to be used for DropDown -- per Naveen***************
			
	public void doSelectByVisibleText(By locator,  String value) {		//pass locator(id) instead of WebElelment("txtusername") as element util is working on locator
			Select select = new Select(getElement(locator));			//Select class is expecting element so we pass getElement (locator) also remove static 				
			select.selectByVisibleText(value);	
	}
	public void doSelectByIndex(By locator,  int index) {
			Select select = new Select(getElement(locator));				//Read above note
			select.selectByIndex(index);	
	}
	public void doSelectByValue(By locator,  String value) {
			Select select = new Select(getElement(locator));				//Read above note
			select.selectByValue(value);	
					}
	//*********************Methods below were tranferred from DropDown2 Class and Pasted Above****************
//			public static void doSelectByVisibleText(WebElement element,  String value) {
//				Select select = new Select(element);				
//				select.selectByVisibleText(value);	
//			}			
//			public static void doSelectByIndex(WebElement element,  int index) {
//				Select select = new Select(element);				
//				select.selectByIndex(index);	
//			}
//			public static void doSelectByValue(WebElement element,  String value) {
//				Select select = new Select(element);				
//				select.selectByValue(value);	
//			}
			
		//Added Below Additional code for DropDown:
			
			public int DropDownOptions(WebDriver driver, By locator) {
				return doGetDropDownOptions(driver, locator).size();
	}
		
		 public ArrayList<String> doGetDropDownOptions(WebDriver driver, By locator) {
			 ArrayList <String> ar = new ArrayList<String>();
			 Select select = new Select (getElement(locator));
				List <WebElement> OptionsList = select.getOptions();
				//System.out.println(OptionsList.size());			//commented this line as total count was printing twice. Here and line 69, 73, 77
				
				 for(int i=0; i<OptionsList.size(); i++) {
					 String text = OptionsList.get(i).getText();
					 ar.add(text);
					 //System.out.println(text);	 					//This prints vertical
	 }
				 
		 return ar;
	 }
	
//************Another Method added for DropDown****************************************************88
		 
		 public void doSelectDropDownValue(By locator, String value) {
				Select selectday = new Select (getElement(locator));				//SINGLE "Element"
				List <WebElement> OptionsList = selectday.getOptions();
				
				 for(int i=0; i<OptionsList.size(); i++) {
					 String text = OptionsList.get(i).getText();
					 if (text.equals(value)) {
						 OptionsList.get(i).click();					 
						 break;
	}
		 }
	 }		 
	//Below we are NOT using select class but getting option list using findElements/Xpath  without using select class
	//compared to getOptions and inbuilt method of select class in and we have to create select object in FacebookDropDown class.

	public void doSelectDropDownValueWithoutSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);						//MULTIPLE "Elements"

		for (int i = 0; i < optionsList.size(); i++) {
			String text = optionsList.get(i).getText();
			if (text.equals(value)) {
				optionsList.get(i).click();
				break;
			}
		}
	}
	
	//Below is from video 8 Jquery Dropdown (1)Single value Selection (2)Multiple value Selection:
	
	public void selectChoiceValues(By locator, String... value) {			//String value changed to three dot parameter,will behave like array
		//List<WebElement> choiceList = driver.findElements(By.cssSelector("span.comboTreeItemTitle"));
		
		List<WebElement> choiceList = getElements(locator);
		
		if(! value[0].equalsIgnoreCase("ALL")){
			
			
	
	for(int i=0; i<choiceList.size(); i++) {
	String	text = choiceList.get(i).getText();
		System.out.println(text );
		
		for(int k=0; k<value.length; k++) {		//Added for loop to use the three dot string array above.
			if(text.equals(value[k])) {
			choiceList.get(i).click();
				break;
				
			}
		  }
		}
	  }
	
	//select ALL the values
			else{
				try{
				for(int all=0; all<choiceList.size(); all++){
					choiceList.get(all).click();
				}
				}
				catch (Exception e){
					
				}
				
			}
	}
	
//**************************Actions/Drag & Drop Class Utils*************************************************************
			
	public void doDragAndDrop(By source, By target) {

		Actions action = new Actions(driver);
		WebElement sourceEle = getElement(source);
		WebElement targetEle = getElement(target);
		action.dragAndDrop(sourceEle, targetEle).build().perform();
	}
	
//***************Below are Actions class method from Selenium class9***when normal sendkey and click keys not working use these******* 

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}
					
		//**************************************************Wait Utils*****************************************************
					
											//VisibilityForAllElements
	public List<WebElement> VisibilityForAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// waitforElementPresent
	public WebElement waitforElementPresent(By locator, int timeout) { 	// Use--waitforElementPresent--method
		WebDriverWait wait = new WebDriverWait(driver, timeout); 	   	// --If you want to use--
																	  	// presenceOfElementLocated
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	// waitforElementtoBeVisible
	public WebElement waitforElementtoBeVisible(By locator, int timeout) { // Use--ElementtoBeVisible--method
		WebElement element = getElement(locator); // --if you want to use--visibilityOf(element));
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	// waitforElementtoBeClickable
	public WebElement waitforElementtoBeClickable(By locator, int timeout) { // Use--elementToBeClickable(locator) method
																				
		WebDriverWait wait = new WebDriverWait(driver, timeout); // --if you want to use--waitforElementtoBeClickable
																	
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	// waitforUrl (It is Non-Weblement)
	public boolean waitforUrl(String url, int timeout) { 			// Use-- waitforUrl method
															
		WebDriverWait wait = new WebDriverWait(driver, timeout); 	// --if you want to use--urlContains(url))																	
																	
		return wait.until(ExpectedConditions.urlContains(url));

	}
											// waitforAlertToBePresent
	public Alert waitforAlertToBePresent(int timeout) {					// Use-- waitforAlertToBePresent method 
		WebDriverWait wait = new WebDriverWait(driver, timeout );		//--if you want to use--alertIsPresent
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());	
		return alert;
						
	}
												//ClickWhenReady
	public void ClickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
											//WaitForTitleToBePresent
	public String WaitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
}
