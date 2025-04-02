package com.automationDemo.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class MBModelPage {

	WebDriver driver;
	
	private static final Logger log = LogManager.getLogger(MBModelPage.class);
	 
	public MBModelPage(WebDriver driver) {
		this.driver = driver;
	}
		
	//This function is to click on Build your Car button
	public boolean selectBuildYourCarButton() {
		boolean result=false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			//click on build your car button
			WebElement buildYourCarButton = driver.findElement(By.xpath("(//owc-stage[@class='webcomponent aem-GridColumn aem-GridColumn--default--12 owc-image-stage-host'])[1]"))
					.getShadowRoot().findElement(By.cssSelector("div[class='owc-stage-cta-buttons owc-stage-cta-buttons--dark'] > a:first-child"));
			
			if (buildYourCarButton!=null) {
				js.executeScript("arguments[0].click();", buildYourCarButton);
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(buildYourCarButton));
				//verify if configurator page is displayed
				if(driver.findElement(By.tagName("owcc-car-configurator"))!=null){
					System.out.println("Build your car button is clicked");
					return result=true;		
				}
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
