package com.MentorMate.performancereports;

/**
* 
* @author  Aleksandar Markov
* @version 1.0
* @created 2021-05-14 
*/
public class App {
	
  public static void main(String[] args) {
	
	  BusinessLogic obj=new BusinessLogic();
	
	 
	  
	  
	  if(args.length==2) {
		obj.setPathData(args[0]);
		  obj.setPathReport(args[1]);
		  obj.readingJSON();
	}
	
	else {
		System.out.println("Expected 2 paths one for Data and one for Report");
		System.exit(0);
	}
    
  }
}
