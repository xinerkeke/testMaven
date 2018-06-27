package byy.testMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;

public class Demo01 {
	@Test
	public void f0() {
		System.out.println("Demo 01_0");
	}
	
	@Test(groups="a")
	public void f1() {
		System.out.println("Demo 01_1");
	}
	
	@Test
	public void f2() {
		System.out.println("Demo 01_2");
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

}
