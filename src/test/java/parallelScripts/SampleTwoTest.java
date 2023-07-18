package parallelScripts;

import org.testng.annotations.Test;

public class SampleTwoTest {
	@Test
	  public void testOne() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestOne in SampleTwo...."+id);
	  }
	  @Test
	  public void testTwo() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestTwo in SampleTwo...."+id);
	  }
	  @Test
	  public void testThree() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestThree in SampleTwo...."+id);
	  }
	  @Test
	  public void testFour() {
		  long id= Thread.currentThread().getId();
		  System.out.println("TestFour in SampleTwo...."+id);
	  }
}
