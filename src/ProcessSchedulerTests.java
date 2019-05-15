
public class ProcessSchedulerTests {
	/**
	   * Main method for running all the tests
	   * @param args is the array of string commands that run the code
	   */
	  public static void main(String[] args)
	  {
	    boolean error = false;
	    
	    if(!testEnqueueCustomProcessQueue()) 
	    {
	      System.out.println("Failed testEnqueueCustomProcessQueue()");
	      error = true;
	    }
	    
	    if(!testDequeueCustomProcessQueue()) 
	    {
	      System.out.println("Failed testDequeueCustomProcessQueue()");
	      error = true;
	    }
	    
	    if(!testCompareTo())
	    {
	      System.out.println("Failed testCompareTo()");
	      error = true;
	    }
	    
	    if(!error) 
	    {
	      System.out.println("Passed All.");
	    }
	  }
	  

  /**
   * This method checked customProcess class tests.
   * 
   * @return true if test passed, false if failed
   */
  
  public static boolean CustomProcessTest()
  {
	  CustomProcess testheap = new  CustomProcess(3);
	  CustomProcess testheap1 = new  CustomProcess(10);

     if (testheap.getBurstTime() !=3 && testheap1.getBurstTime() !=10)
     {
        return false ;
     }
     return true;
  }
  /**
	 * checks the correctness of the enqueue
	 */
  
  public static boolean testEnqueueCustomProcessQueue()
  {
    boolean error = true;
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(3));
    
    if(q.peek().getBurstTime()!=3) 
    { 
      error = false;
    }
    
    q.enqueue(new CustomProcess(10));
    if(q.peek().getBurstTime()!=3) 
    {
      error = false;
    }
    
    q.enqueue(new CustomProcess(1)); 
    if(q.peek().getBurstTime()!=1) 
    { 
      error = false;
    }
    
    q.enqueue(new CustomProcess(0)); 
    q.enqueue(new CustomProcess(4)); 
    if(q.peek().getBurstTime()!=1) 
    { 
      error = false;
    }
    
    return error;
  } 



  /**
	 * checks the correctness of the dequeue
	 */
  public static boolean testDequeueCustomProcessQueue()
  {
    boolean error = false;
    CustomProcessQueue q = new CustomProcessQueue();
    
    q.enqueue(new CustomProcess(9));
    q.enqueue(new CustomProcess(3));
    q.enqueue(new CustomProcess(6));
    
    if(q.dequeue().getBurstTime()!=3) 
    {
      error = true;
    }
    
    if(q.dequeue().getBurstTime()!=6) 
    {
      error = true;
    }
    
    if(q.peek().getBurstTime()!=9) 
    {
      error = true;
    }
    
    return error;
  } 
  
  public static boolean testCompareTo() 
  {
    CustomProcess process1 = new CustomProcess(13);
    CustomProcess process2 = new CustomProcess(13);
    CustomProcess process3 = new CustomProcess(3); 
    CustomProcess process4 = new CustomProcess(33);
    
    if(process1.compareTo(process2) > 0) 
    { 
      return false;
    }
    if(process1.compareTo(process3) < 0) 
    { 
      return false;
    }
    if(process1.compareTo(process4) > 0) 
    {
      return false;
    }
    return true;
  }
}