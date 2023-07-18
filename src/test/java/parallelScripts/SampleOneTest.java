package parallelScripts;

import org.testng.annotations.Test;

public class SampleOneTest {
  @Test
  public void testOne() {
	  long id= Thread.currentThread().getId();
	  System.out.println("TestOne in SampleOne...."+id);
  }
  @Test
  public void testTwo() {
	  long id= Thread.currentThread().getId();
	  System.out.println("TestTwo in SampleOne...."+id);
  }
  @Test
  public void testThree() {
	  long id= Thread.currentThread().getId();
	  System.out.println("TestThree in SampleOne...."+id);
  }
  @Test(invocationCount=6,threadPoolSize=3,timeOut=2000)
 // @Test
  public void testFour() {
	  long id= Thread.currentThread().getId();
	  System.out.println("TestFour in SampleOne...."+id);
  }
}
