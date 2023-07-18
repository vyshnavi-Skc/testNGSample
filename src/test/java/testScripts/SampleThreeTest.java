package testScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
  @Test(groups="FeatureOne")
  public void testOne() {
	 System.out.println("Test 31 in SampleThree");
  }
  
  @Test(groups="FeatureTwo")
  public void testTwo() {
	 System.out.println("Test 32 in SampleThree");
  }
  
  @Test(groups="FeatureThree")
  public void testThree() {
	 System.out.println("Test 33 in SampleThree");
  }
  @Test(groups="FeatureFour")
  public void testFour() {
	 System.out.println("Test 34 in SampleThree");
  }
}
