package com.automationDemo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Generic {
	
	protected static WebDriver driver;
	//below function will create a text file and write the desired values.
	public static void writeStrings(String fileDir, String fileName ,String string1, String string2) throws IOException {
	    File fetchedValue = new File(System.getProperty("user.dir")+"\\test-output\\" + fileName);

	    FileWriter fw = new FileWriter(fetchedValue);
	    fw.write(string1);
	    fw.write("\n");
	    fw.write(string2);
	    fw.close();
	}
		
	//this function is to take the Screenshot
	public static void captureScreen(String screenName) {
			
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
				FileUtils.copyFile(scrFile, new File("screenShots\\" + screenName +  ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	
	
}
