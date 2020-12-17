package com.qa2.hubspot2.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa2.hubspot2.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop; 						//reference of properties here and object of properties class created below
	public ElementUtil elementUtil;

	/*
	 * this method is us to initialize the WebDriver on basis of the browser
	 * @param browserName
	 * @return driver
	 * 
	 */
	
	public WebDriver init_driver(Properties prop){
		
		String browserName = prop.getProperty("browser");			//made it auto to pick from config.prop
		
	if(browserName.equalsIgnoreCase("chrome")){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
	}
	else if(browserName.equalsIgnoreCase("firefox")){
		WebDriverManager.chromedriver().setup();
		driver = new FirefoxDriver(); 
	}
	else if(browserName.equalsIgnoreCase("internetxexplorer")){
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver(); 
	}
	
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url"));								//made it auto to pick from config.prop
	
	return driver;
	
	}
	/**
	 * this method is use to initialize the properties from config.properties 
	 * file
	 * 
	 * @return
	 */
		public Properties init_prop(){	
		prop = new Properties();				//1.create object of properties class and then 
												//2.create object of FileInputStream & add config location for browser
												//3.prop.load.  Add try/catch block twice.Remove message.
			//Had to ADD (".\\src\\main..) as .\\ is your current directory!
		try {
			FileInputStream ip = new FileInputStream(".\\src\\main\\java\\com\\qa2\\hubspot2\\config\\config.properties");
			prop.load(ip);				
			} catch (FileNotFoundException e) {
				e.printStackTrace();	
			} catch (IOException e) {
				e.printStackTrace();	
			}	
			return prop;
		}
	}