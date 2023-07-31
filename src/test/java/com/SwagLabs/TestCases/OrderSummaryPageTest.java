package com.SwagLabs.TestCases;

import org.testng.annotations.Test;

import com.SwagLabs.Utility.Baseclass;

public class OrderSummaryPageTest extends Baseclass {
	  @Test(priority=1)
	  public void verifySummary()
	  {
		  os.getSummary();
	  }
	  
	  @Test(priority=2)
	  public void verifyOrder()
	  {
		  os.completeProcess();
	  }

}
