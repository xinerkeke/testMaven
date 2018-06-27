package byy.testMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Demo02 {
	@Test
	public void f0() {
		System.out.println("Demo 02_0");
	}
	
	@Test
	public void f1() {
		System.out.println("Demo 02_1");
	}
	
	@Test
	public void f2() {
		System.out.println("Demo 02_2");
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

}
