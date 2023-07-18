package parallelScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
	@Test
	  public void testOne() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestOne in SampleThree...."+id);
	  }
	  @Test
	  public void testTwo() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestTwo in SampleThree...."+id);
	  }
	  @Test
	  public void testThree() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestThree in SampleThree...."+id);
	  }
	  @Test
	  public void testFour() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestFour in SampleThree...."+id);
	  }
}
