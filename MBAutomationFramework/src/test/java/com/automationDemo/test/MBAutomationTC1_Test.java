package com.automationDemo.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automationDemo.base.TestBase;
import com.automationDemo.pages.BuildYourCarPage;
import com.automationDemo.pages.MBHomePage;
import com.automationDemo.pages.MBModelPage;
import com.automationDemo.util.ExcelUtil;
import com.automationDemo.util.Generic;

//This test case is to validate the Home screen
public class MBAutomationTC1_Test extends TestBase{

	MBHomePage homePage;
	MBModelPage modelPage;
	BuildYourCarPage buildYourCarPage;

	//Log4j configuration
	private static final Logger log = LogManager.getLogger(MBAutomationTC1_Test.class);
	
	@Test(dataProvider = "tempTestData")
	public void selectModelTest(String[] fromxl){
	
		
		
		  log.info("Select Our model and model class given in test data"); homePage=
		  new MBHomePage(driver); 
		  boolean result1 = homePage.selectModelClass(fromxl);
		  Assert.assertTrue(result1, "Model class is not selected");
		  
		  log.info("Select model type and verify if given page is displayed"); boolean
		  result2 = homePage.selectModelTypeAndVerify(fromxl);
		  Assert.assertTrue(result2,
		  "Model type is not selected and page is not displayed");
		  
		  log.info("Select Build your car button"); modelPage= new MBModelPage(driver);
		  boolean result3 = modelPage.selectBuildYourCarButton();
		  Assert.assertTrue(result3, "Failure");
		  
		  log.info("Select Fuel type"); buildYourCarPage= new BuildYourCarPage(driver);
		  boolean result4 = buildYourCarPage.selectFuelType(fromxl);
		  Assert.assertTrue(result4, "Failure");
		  
		  log.info("Record Minimum consumption price"); 
		  String lowestpriceValue = buildYourCarPage.recordLowestPriceCarValue();
		  Assert.assertTrue(!lowestpriceValue.isEmpty());
		  
		  log.info("Record Maximum consumption price"); 
		  String highestPriceValue=buildYourCarPage.recordHighestPriceCarValue();
		  Assert.assertTrue(!highestPriceValue.isEmpty());
		
		  try {
			Generic.writeStrings("//test-output", "Fetched Price" ,"Lowest price :"+lowestpriceValue, "Highest price:"+highestPriceValue);
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
	
	@DataProvider(name = "tempTestData")
	public Object[][] tempTestData() throws Exception {

		String[][] testData = ExcelUtil.getExcelDataIn2DArray("testData//AutomationTestData.xlsx", "Model_Details");
		return testData;
	}
		
}
