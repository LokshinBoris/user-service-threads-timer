package telran.multithreading;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
//TODO displaying time in a given format and a given resolution
	//example displaying each second, or each 5 seconds, etc.
	long pauseTime=1000;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final Object lock = new Object();
	private boolean paused = false;

	public void setTimeFormat(String timeFormat)
	{
		try
		{
			this.formatter = DateTimeFormatter.ofPattern(timeFormat);
		} 
		catch (IllegalArgumentException e)
		{
		}
	}
	
	public void setPauseTime(long pauseTime)
	{
		this.pauseTime = pauseTime;
	}
	
	public Timer() 
	{
		setDaemon(true);
	}
	
	public void run()
	{
		while(true) 
		{
			synchronized (lock)
			{
				while (paused) 
				{
					try 
					{
						lock.wait();
					}
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\r"+" ".repeat(20)+LocalTime.now().format(formatter));
			
			try
			{
				Thread.sleep(pauseTime); 
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}		
	}
	
	public void pauseThread() 
	{
		synchronized (lock) 
		{
			paused = true;
		}
	}

	public void resumeThread()
	{
		synchronized (lock) 
		{
			paused = false;
			lock.notify();
		}
	}
	
}
