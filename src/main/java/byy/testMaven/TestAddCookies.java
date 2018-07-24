package main.java.byy.testMaven;

import org.testng.annotations.Test;

public class TestAddCookies extends BaseTest {
	

	public TestAddCookies() {
	}

	@Test
	public void f() {
		driver.get("http://10.10.20.55:9090/usda/adminHome.do");
	}
}
