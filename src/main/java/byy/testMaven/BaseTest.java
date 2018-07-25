package main.java.byy.testMaven;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	private ParseProperties prop;
	private ParseProperties xpathProp;
	protected static WebDriver driver = null;
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
	
	/*
	 * 遍历body节点下的所有节点 取其文本值 并判断是否包含文本 what
	 */
	public static boolean isTextPresent(WebDriver driver, String what) {
		try {
			
			String str = driver.findElement(By.tagName("body")).getText();
			
			return str.contains(what);
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 获取屏幕列表数据中的某行，返回第一个列表元素 tableXpath:列表xpath， 到行（.../tr） serial：在第几个元素中查找
	 * what：匹配的字符串
	 */
	public static WebElement getTableFirstElt(WebDriver driver,
			String tableXpath, int serial, String what) {

		if (what == null || tableXpath == null)
			return null;

		List<WebElement> rows = driver.findElements(By.xpath(tableXpath));

		for (WebElement row : rows) {
			// 得到当前tr里td的集合
			List<WebElement> cols = row.findElements(By.tagName("td"));

			String str = cols.get(serial).getText();
			if (cols.get(serial).getText().contains(what)) {
				//return cols.get(0);
				return row;
			}
		}
		return null;
	}

}
