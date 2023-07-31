package com.SwagLabs.Utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.SwagLabs.Pages.AddToCartPage;
import com.SwagLabs.Pages.CheckOutPage;
import com.SwagLabs.Pages.InventoryPage;
import com.SwagLabs.Pages.LoginPage;
import com.SwagLabs.Pages.OrderSummaryPage;

public class Baseclass {

	public WebDriver driver;
	public LoginPage lp;
	public InventoryPage ip;
	public AddToCartPage ap;
	public CheckOutPage cp;
	public OrderSummaryPage os;
public PropertiesFile p1;
	
public static void captureScreenshot(WebDriver driver) {
//	TakesScreenshot ts=(TakesScreenshot) driver;
//	  File temp=ts.getScreenshotAs(OutputType.FILE);
//	  File dest=new File("./"+"\\ScreenShots\\pic.png");
	  try {
		FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./"+"\\ScreenShots\\pic"+System.currentTimeMillis()+".png"));
	} catch (WebDriverException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	@BeforeTest//initialization
	public void browserSetup()
	{
		p1=new PropertiesFile();
		Reporter.log("Browser is opening",true);
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("SwagLabs application is open",true);
		driver.get("https://www.saucedemo.com/");
		lp=new LoginPage(driver);
		ip=new InventoryPage(driver);
		ap=new AddToCartPage(driver);
		cp=new CheckOutPage(driver);
		os=new OrderSummaryPage(driver);
		
	}

	
	@BeforeClass
	public void pageSetup()
	{ Reporter.log("User login process will begin",true);
		System.out.println("Title is: "+lp.getAppTitle());
		System.out.println("Currenturl is: "+lp.getAppUrl());
//		lp.doLogin("standard_user", "secret_sauce");
		lp.doLogin(p1.getData("uname"), p1.getData("psw"));
		waitFunction();
		Baseclass.captureScreenshot(driver);
		Reporter.log("User login is completed",true);
		System.out.println("**************User Login is completed*******");
		Reporter.log("Inventory page Testing started",true);
		System.out.println("Total Product count is: " +ip.getProductCount());
		ip.getProductNames();
		waitFunction();
		Baseclass.captureScreenshot(driver);
		ip.addProductToCart(p1.getData("pname"));
//		 ip.addProductToCart("Sauce Labs Fleece Jacket");
		System.out.println("Inventory Page Testing is completed!");
		Reporter.log("Add to cart page testing started!",true);
		
		ap.openCartPage();
		waitFunction();
		Baseclass.captureScreenshot(driver);
		ap.doCheckOut();
		waitFunction();
		System.out.println("Checkout page will Open!");
		Reporter.log("CheckOut process begin",true);
		waitFunction();
		Baseclass.captureScreenshot(driver);
		cp.doContinue(p1.getData("fname"),p1.getData("lname"),p1.getData("zcode"));
//		cp.doContinue("Priyanka", "Nigade", "411014");
		waitFunction();
		Baseclass.captureScreenshot(driver);
		System.out.println("Checkout process is completed!");
		Reporter.log("Process is completed!",true);
		Baseclass.captureScreenshot(driver);
		
		
	}
	
	
	public static void waitFunction()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
