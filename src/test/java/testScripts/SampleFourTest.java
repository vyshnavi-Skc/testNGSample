package testScripts;

import org.testng.annotations.Test;

public class SampleFourTest {
	@Test(groups="FeatureOne")
	  public void testOne() {
		 System.out.println("Test 41 in SampleThree");
	  }
	  
	  @Test(groups="FeatureTwo")
	  public void testTwo() {
		 System.out.println("Test 42 in SampleThree");
	  }
	  
	  @Test(groups="FeatureThree")
	  public void testThree() {
		 System.out.println("Test 43 in SampleThree");
	  }
	  @Test(groups="FeatureFour")
	  public void testFour() {
		 System.out.println("Test 44 in SampleThree");
	  }

}
