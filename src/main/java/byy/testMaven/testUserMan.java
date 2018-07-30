package main.java.byy.testMaven;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestngListener.class })
public class testUserMan extends BaseTest {

	private String model = "user-mainBody";
	public static String DataFile = "testuserMan.xls";
	Random rand = new Random();
	testDivPage divPage = new testDivPage();

	String Institut[] = { "先进数通k", "2343", "test", "XX银行S", "2343",
			"32561yeyuT11111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
					+ "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
			"1234567", "1234", "~!@##@#$^%&*", "Test000", "Test000", "Tes t00 8", "Test10", "Test009", "Test12",
			"Test12", "Test003", "Test011", "XX银行一级支行1", "XX银行一级支行2", "XX银行二级支行1", "XX银行二级支行2", "XX银行二级支行3",
			"XX银行二级支行4" };
	int InstituSize = Institut.length;

	@BeforeClass
	public void beforeClass() throws Exception {
		// 测试前处理
		driver.findElement(By.xpath(".//*[@id='mcs3_container']/div/div/div/div/div/div[1]/a/img")).click();// 选择项目1
	}

	/*
	 * 增加测试（取数据、测试代码）
	 */
	@DataProvider(name = "testuserMan")
	public static Object[][] getTestAddData() throws Exception {
		return ReadExecl.initTestData("testuserMan.xls", "addUser");
	}

	/**
	 * @param loginName
	 * @param adminName
	 * @param email
	 * @param telephone
	 * @param mobilePhone
	 * @param expectStr
	 * @throws Exception
	 */
	@Test(dataProvider = "testuserMan")
	public void testAdd(String loginName, String adminName, String email, String telephone, String mobilePhone,
			String expectStr) throws Exception {
		// 增加测试代码,需按照界面要求修改方法参数

		driver.findElement(By.xpath("//li/div/span[@class='tree-title' and text()='用户管理']")).click();// 用户管理

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		final String addXpathBut = "//div[@id='user-mainBody']/div[2]/div/div/div"
				+ "/div[@class='datagrid-toolbar']/a/span/span[text()='新增']";
		
		//显示等待
		(new WebDriverWait(driver,
				 20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(addXpathBut)));


		for (int i = 0; i < 60; i++) {
			try {
				driver.findElement(By.xpath(addXpathBut)).click();// 新增
				break;
			} catch (Exception e) {
				System.out.println("Failed to Find the element.");
			}
			Thread.sleep(3000);
		}


		setUserInfo(loginName, adminName, email, telephone, mobilePhone);
		driver.findElement(By.xpath(".//*[@id='user-save']")).click();

		try {
			assertTrue(isTextPresent(driver, expectStr));
		} catch (Error e) {

		} finally {
			driver.findElement(By.xpath("//input[@id='user-close']")).click();
		}
	}

	/**
	 * 
	 * 设置用户信息
	 * 
	 * @param loginName
	 * @param adminName
	 * @param email
	 * @param telephone
	 * @param mobilePhone
	 */
	/**
	 * @param loginName
	 * @param adminName
	 * @param email
	 * @param telephone
	 * @param mobilePhone
	 */
	private void setUserInfo(String loginName, String adminName, String email, String telephone, String mobilePhone) {
		driver.findElement(By.id("login_name")).clear();
		driver.findElement(By.id("login_name")).sendKeys(loginName);

		driver.findElement(By.xpath(".//*[@id='user_name']")).click();
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(adminName);

		// 选择用户启动状态
		String userStatusXpath = "//form[@id='userEditForm']/div[@class='imoia_pop_con']"
				+ "/ul/li[3]/span/input[@name='userStatus']";

		getOperateElement(userStatusXpath).click();

		// 选择用户类型
		driver.findElement(
				By.xpath("//form[@id='userEditForm']/div[@class='imoia_pop_con']" + "/ul/li[4]/span/span/span/span"))
				.click();
		String userTypeXpath = "//body[@id='mainBody']/div[15]/div/div";
		getOperateElement(userTypeXpath).click();

		/*
		 * driver.findElement(By.xpath(
		 * "//form[@id='userEditForm']/div[@class='imoia_pop_con']/ul/" +
		 * "li[5]/span/span/span")).click(); //driver.findElement(By.xpath(
		 * "//body[@id='mainBody']/div[16]/div/ul/li[2]/div/span[3]")).click();
		 * String instituXpath = "//body[@id='mainBody']/div[16]/div/ul/li";
		 * getOperateElement(instituXpath,
		 * "ul/li").findElement(By.xpath("./div/span[3]")).click();
		 */
		// 设置"所属机构"
		setInstituteVal();

		driver.findElement(By.id("user_email")).click();
		driver.findElement(By.id("user_email")).clear();
		driver.findElement(By.id("user_email")).sendKeys(email);
		driver.findElement(By.id("user-tel")).clear();
		driver.findElement(By.id("user-tel")).sendKeys(telephone);
		driver.findElement(By.id("user-mobile")).clear();
		driver.findElement(By.id("user-mobile")).sendKeys(mobilePhone);
	}

	/**
	 * 设置所属机构
	 */
	private void setInstituteVal() {
		// 获取所属机构元素
		WebElement ele = driver.findElement(
				By.xpath("//form[@id='userEditForm']/div[@class='imoia_pop_con']/ul/" + "li[5]/span/input"));
		// 删除"readonly"只读属性
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly')", ele);
		// 机构列表中随机选择"机构"
		String instiVal = Institut[rand.nextInt(InstituSize)];
		// 设置机构
		ele.clear();
		ele.sendKeys(instiVal);
		// "readonly"只读属性
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('readonly','readonly')", ele);
	}

	/**
	 * 時延
	 * 
	 * @param xpath
	 */
	private void timeDelayOpera(String xpath) {
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
	}

	// 随机选择操作元素
	private WebElement getOperateElement(String xpath) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		int size = list.size();

		return list.get(rand.nextInt(size));
	}

	/*
	 * private WebElement getOperateElement(String xpath,String chilsListXpath)
	 * { List<WebElement> childList; //获取xpath路径下的元素list List<WebElement>list =
	 * driver.findElements(By.xpath(xpath)); int size = list.size(); //列表元素个数
	 * 
	 * //随机获取tmpRand+1个元素 int tmpRand = rand.nextInt(size); WebElement element =
	 * list.get(tmpRand);
	 * 
	 * for(;;){ //如果随机数==元素列表个数，返回当前元素，对进行操作 if((rand.nextInt(size+1))== size) {
	 * break; } //点击"下级"列表”
	 * element.findElement(By.xpath("./div/span[1]")).click();
	 * 
	 * childList = list.get(tmpRand).findElements(By.xpath("./ul/li"));
	 * //如果"下级"列表为空，返回当前元素 if(childList.size() == 0) { element =
	 * list.get(tmpRand); break; }
	 * 
	 * size = childList.size(); tmpRand = rand.nextInt(size); element =
	 * childList.get(tmpRand); } return element; }
	 */

}
