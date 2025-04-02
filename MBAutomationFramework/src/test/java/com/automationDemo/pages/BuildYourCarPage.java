package com.automationDemo.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

public class BuildYourCarPage {

	WebDriver driver;
	
	private static final Logger log = LogManager.getLogger(BuildYourCarPage.class);
	 
	public BuildYourCarPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//This function is to select the given Fuel type and take screenshot
	public boolean selectFuelType(String[] testDataInput) {
		boolean result=false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			//click on build your car button
			WebElement fuelTypeButton = driver.findElement(By.tagName("owcc-car-configurator"))
					.getShadowRoot().findElement(By.cssSelector("ccwb-multi-select[class='cc-motorization-filters-primary-filters--multi-select wb-multi-select hydrated']"))
					.getShadowRoot().findElement(By.cssSelector("button  > div[class='label']"));
					
			js.executeScript("arguments[0].scrollIntoView();", fuelTypeButton);
			if (fuelTypeButton!=null) {
				js.executeScript("arguments[0].click();", fuelTypeButton);
				
				WebElement fuelTypeValue = driver.findElement(By.tagName("owcc-car-configurator"))
						.getShadowRoot().findElement(By.cssSelector("ccwb-checkbox"))
						.getShadowRoot().findElement(By.cssSelector("input[name='"+testDataInput[2]+"']"));//parametrization of test data in CSS
				js.executeScript("arguments[0].click();", fuelTypeValue);
				
				Actions action = new Actions(driver);
				action.moveByOffset(0, 0).click().build().perform();
								
				if (fuelTypeValue!=null) {
					result=true;
					captureScreen("SelectFuelTypeScreen");
				}
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//This function is to record the lowest price displayed 
	public String recordLowestPriceCarValue() {			
			String lowestPrice=null;
			
			try{
				WebElement lowestPriceElement = driver.findElement(By.tagName("owcc-car-configurator"))
						.getShadowRoot().findElement(By.cssSelector("div[class='cc-motorization-header__flags ng-star-inserted'] + div + ccwb-heading + ccwb-text"));
				
				if (lowestPriceElement.getText()!=null) {
					lowestPrice=lowestPriceElement.getText();
					System.out.println("Lowest price of vehicle displayed: "+lowestPrice);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return lowestPrice;	
	}
	
	//This function is to record the highest price displayed
	public String recordHighestPriceCarValue() {			
				String highestPrice=null;
				
				try{
					//clicking on Sort by button
					WebElement sortByDD = driver.findElement(By.tagName("owcc-car-configurator"))
							.getShadowRoot().findElement(By.id("motorization-filters-sorting-options"));
					
					//using Select class to sort the car results in descending order
					Select selectSortByDD = new Select(sortByDD);
					selectSortByDD.selectByValue("17: price|DESCENDING");
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					
					//taking the price of first car appears on screen
					WebElement highestPriceElement = driver.findElement(By.tagName("owcc-car-configurator"))
							.getShadowRoot().findElement(By.cssSelector("div[class='cc-motorization-comparison-wrapper']>div:first-child>ccwb-card>div:nth-child(1)>cc-motorization-header>div>div>ccwb-heading+ccwb-text"));
					
					if (highestPriceElement.getText()!=null) {
						highestPrice=highestPriceElement.getText();
						System.out.println("Highest price of vehicle displayed: "+highestPrice);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return highestPrice;	
	}

	//this function is to take the Screenshot
	private void captureScreen(String screenName) {
		
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("screenShots\\" + screenName +  ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}

	
}
