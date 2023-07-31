package com.SwagLabs.TestCases;

import org.testng.annotations.Test;

import com.SwagLabs.Utility.Baseclass;

public class AddToCartPageTest extends Baseclass {
	@Test(priority=1)
	  public void OpenAddToCartPage() 
	  {
		  ap.openCartPage();
	  }
	  
	  @Test(priority=2)
	  public void verifyCheckoutPage() 
	  {
		  ap.doCheckOut();
	  }
	  

}
