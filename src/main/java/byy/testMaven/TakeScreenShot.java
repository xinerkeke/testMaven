package main.java.byy.testMaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class TakeScreenShot {
	
	private Properties pps;
	String time = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());

	public void ScreenShot(ITestResult result) throws IOException {
		
		pps = new Properties();
		pps.load(new FileInputStream(System.getProperty("user.dir")
				+ "/src/main/java/byy/testMaven/test.properties"));
		String screenPath = pps.getProperty("screenPath");
		
		File dir = new File(screenPath);
		if (!dir.exists())
			dir.mkdirs();
		String methodName = result.getMethod().toString();
		
		File outfile = new File(screenPath + "/"+methodName.substring(0, methodName.indexOf('(')) +"-" + time +".jpg");
		
		try {
			File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, outfile);
		} catch (IOException e) {
			System.out.println("Screen shot error: " + screenPath);
		}
	}
	
}
