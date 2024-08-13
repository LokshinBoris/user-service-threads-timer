package telran.multithreading;

import java.util.Scanner;

public class TimerControllerAppl {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Command words for timer: start/stop/exit/time <time>/format <format>/exit");
		Thread.sleep(2000);
		
		Timer timer = new Timer();
		timer.start();

		
		Scanner scanner = new Scanner(System.in); 
		String input="";
		while (input.compareToIgnoreCase("exit")!=0)
		{
			input = scanner.nextLine();
			String strCommand="";
			String strPar="";
			int index= input.indexOf(' ');
			if(index!=-1) 
			{
				strCommand=input.substring(0, index);
				strPar=input.substring(index).trim();					
			}
			else strCommand=input;
				
			
			switch(strCommand)
			{
				case "start": timer.resumeThread(); break;
				case "stop":  timer.pauseThread(); break;
				case "time": 
					{
						try
						{
							timer.setPauseTime(Long.parseLong(strPar)); 
							
						} 
						catch (NumberFormatException e)
						{
						
						}
						break;
					}
				case "format": timer.setTimeFormat(strPar); break;				
			}
		}			 
		scanner.close(); 

		//TODO
		//figure out a solution allowing timer stop
		//as example in 5 seconds you stop the timer
		//following 5 seconds application is running with no timer

	}

}
