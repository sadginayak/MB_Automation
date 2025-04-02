package com.automationDemo.pages;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MBHomePage {

	WebDriver driver;
	
	private static final Logger log = LogManager.getLogger(MBHomePage.class);
	 
	public MBHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//this is a function to select Model and Model type from home page screen 
	public boolean selectModelClass(String[] testDataInput) {
		boolean result=false;
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		handleCookiesPopUp();
		try {
			//click on Our Model button
			WebElement ourModelButton = driver.findElement(By.xpath("//owc-header[@class='webcomponent aem-GridColumn aem-GridColumn--default--12']"))
					.getShadowRoot().findElement(By.cssSelector("li[class='owc-header-navigation-topic owc-header-navigation-topic--desktop-nav owc-header-navigation-topic__model-flyout']"));					
					ourModelButton.click();
					
			//select the given Model type in test data
			List<WebElement> totatModelType = driver.findElement(By.xpath("//vmos-flyout[@class='webcomponent webcomponent-nested']"))
							.getShadowRoot().findElements(By.cssSelector("li[class='@vmos-vmos-flyout-flyout-group__QE2pr '] > ul > li  > div"));
			if(totatModelType.size() != 0) 
				{
					System.out.println("Total number of models type :"+totatModelType.size());
							
					for(WebElement inputElement : totatModelType) 
					{
						if(inputElement.getText().equalsIgnoreCase(testDataInput[0])) {
							js.executeScript("arguments[0].scrollIntoView(true);", inputElement);
							new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(inputElement));
							js.executeScript("arguments[0].click();", inputElement);
							result=true;
							break;
						}
					}
				}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//This function will select Model class and Type
	public boolean selectModelTypeAndVerify(String[] testDataInput) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement aClassHatchbackModelTitleElement=null;
		boolean result=false;

		try {
			
				List<WebElement> totatModelClass = driver.findElement(By.xpath("//vmos-flyout[@class='webcomponent webcomponent-nested']"))
						.getShadowRoot().findElements(By.cssSelector("li[class='@vmos-vmos-flyout-flyout-group__QE2pr @vmos-vmos-flyout-flyout-group--subgroup__EVJA6'] >ul > li  > a > p"));
				if(totatModelClass.size() != 0) 
				{
					   System.out.println("Total number of models class :"+totatModelClass.size());
					   Thread.sleep(2000);
					   for(WebElement modelClass : totatModelClass) 
					   {						   
						   if(modelClass.getText().equalsIgnoreCase(testDataInput[1])) {
							   System.out.println("select model class :" +modelClass.getText());
							    Thread.sleep(3000);
							    js.executeScript("arguments[0].scrollIntoView(true);", modelClass);
							    Thread.sleep(3000);							   
								new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(modelClass));			
								js.executeScript("arguments[0].click();", modelClass);					
								//verify if the required page is open
								aClassHatchbackModelTitleElement = driver.findElement(By.xpath("(//owc-stage[@class='webcomponent aem-GridColumn aem-GridColumn--default--12 owc-image-stage-host'])[1]"))
										.getShadowRoot().findElement(By.cssSelector("div[class='owc-stage__text wb-grid-row']> div > h1 > p"));
								String pageTitle = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions. visibilityOf(aClassHatchbackModelTitleElement)).getText();
										
								if (pageTitle.toUpperCase().contains(testDataInput[1].toUpperCase())) {
									result= true;
									System.out.println("Selected page title: "+pageTitle);
								}								
								break;
							}													   
					   }
				}
			}

		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
		
	//function to handle cookies screen
	private void handleCookiesPopUp() {
		log.info("Select Agree to all cookie button");
	
		WebElement agreeToAllButton = driver.findElement(By.xpath("//cmm-cookie-banner[@class='hydrated']"))				
				.getShadowRoot().findElement(By.cssSelector("div > div > div.cmm-cookie-banner__content > cmm-buttons-wrapper > div > div > button.wb-button.wb-button--primary.wb-button--small.wb-button--accept-all"));

		if (agreeToAllButton != null) {
			agreeToAllButton.click();
			try {
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(agreeToAllButton));
			} catch (Exception e) {
				agreeToAllButton.click();
			}
		}
	}	
}
