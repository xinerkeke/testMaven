package main.java.byy.testMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author pc
 *分页测试
 */
public class testDivPage extends BaseTest{
	private int totalPageNum = 0;
	private int PageSize = 0;
	private int locPageNum = 0;
	
	public testDivPage() {
	}
	
	public int getTotalPage(String xpath) {
		String totalPageText =  driver.findElement(By.xpath(xpath)).getText();
		return Integer.parseInt(totalPageText.substring(1, 2));
	}
	
	public int getPageSize() {
		return PageSize;
	}
	
	public int getLocalPage(String xpath) {
		String strVal = driver.findElement(By.xpath(xpath)).getAttribute("value");
		return Integer.parseInt(strVal);
	}
	
	/**
	 * 分页中获取被操作的“元素”
	 * 
	 * @param EnglishName
	 * @return
	 */
	public WebElement getOperatedElement(WebDriver driver, String EnglishName, String model) {
		// 总页数
		totalPageNum = getTotalPage(
				"//div[@id='"+model+"']/div[contains(@class,'layout-panel-center')]/div/div/div/div[@class='datagrid-pager pagination']/table/tbody/tr/td[8]/span");

		// 当前页
		locPageNum = getLocalPage("//input[@class='pagination-num']");

		WebElement chkbox;
		chkbox = getTableFirstElt(driver,
				"//div[@class='datagrid-body-inner']/" + "table[@class='datagrid-btable']/tbody/tr", 2, EnglishName);

		while ((locPageNum >= 1) && (locPageNum <= totalPageNum)) {
			if (chkbox != null && !EnglishName.equals("")) {
				break;
			} else {
				// 下一页
				driver.findElement(By.xpath("//div[@id='"+model+"']"
						+ "/div[contains(@class,'layout-panel-center')]/div/div/div/"
						+ "div[@class='datagrid-pager pagination']/table/tbody/tr/td[10]/a")).click();

				// 点击某个链接的时候，新加载的页面需要等待时间，如果没有加等待,可能会出现
				// Element is no longer attached to the DOM问题
				// Thread.sleep(500);
				locPageNum++;
				chkbox = getTableFirstElt(driver,
						"//div[@class='datagrid-body-inner']/" + "table[@class='datagrid-btable']/tbody/tr", 2,
						EnglishName);
			}
		}
		return chkbox;
	}
	
}
