package main.java.byy.testMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Demo01 {
	@Test
	public boolean f() {
		System.out.println("Demo01 TestCase");
		return false;
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
