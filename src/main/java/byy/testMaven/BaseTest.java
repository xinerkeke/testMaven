package main.java.byy.testMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	private ParseProperties prop;
	private ParseProperties xpathProp;
	protected WebDriver driver = null;
	private Do find;

	private static String testdata = "\\src\\main\\java\\byy\\testMaven\\test.properties";
	private static String testXpath = "\\src\\main\\java\\byy\\testMaven\\xpath.properties";

	@BeforeTest
	public void beforeTest() {
		prop = new ParseProperties(System.getProperty("user.dir") + testdata);
		xpathProp = new ParseProperties(System.getProperty("user.dir") + testXpath);
		Browsers broswer = new Browsers(BrowserType.FIREFOX, prop);
		driver = broswer.driver;
		find = new Do(driver);
		doLogin();
	}

	public void doLogin() {
		// driver.get(prop.getProperty("url") + "/usda/adminLogin.jsp");
		driver.get(prop.getProperty("url") + "/usda/home.do");

		driver.findElement(By.id("loginName")).clear();
		driver.findElement(By.id("loginName")).sendKeys("usda");
		driver.findElement(By.id("loginPassword")).clear();
		driver.findElement(By.id("loginPassword")).sendKeys("usda");
		driver.findElement(By.id("loginBtn")).click();
	}

}
