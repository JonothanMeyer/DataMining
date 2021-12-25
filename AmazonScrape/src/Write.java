
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import org.jsoup.nodes.Document;

public class Write {
	
	public void toFile(double average) throws IOException
	{
		FileWriter toFile = new FileWriter("C:\\Users\\Jonothan\\eclipse-workspace\\WebScrapeProject\\src\\averagetpprice\\", true);
		String stringAverage = Double.toString(average + .01);
		toFile.write("Avg: $" + stringAverage);
		toFile.close();
	}
	
	public void toFile(String date) throws IOException
	{
		try {
		FileWriter toFile = new FileWriter("C:\\Users\\Jonothan\\eclipse-workspace\\WebScrapeProject\\src\\averagetpprice\\", true);
		toFile.write("Date: " + date + "   ");
		toFile.close();
		}
		catch (FileNotFoundException e)
		{
		 System.out.println("Could not find file asshole");
		}
	}
	
	public String[] getDelayArray() throws IOException //fills an array from reading from the text document.
	{
		
		BufferedReader bufReader = new BufferedReader(new FileReader("C:\\Users\\Jonothan\\eclipse-workspace\\WebScrapeProject\\src\\averagetpprice"));
		Stream<String> delayStream = bufReader.lines();
		Object[] delayArray = delayStream.toArray();
		String[] stringArray = new String[delayArray.length];
		System.arraycopy(delayArray, 0, stringArray, 0, delayArray.length);
		for (int i=0;i<stringArray.length;i++)
		{
		 System.out.println(stringArray[i]);
		}
		System.out.println();
		System.out.println();
		//System.out.print("Line 14: " + stringArray[13] + stringArray[13].isEmpty());
		bufReader.close();
		return stringArray;
	}
	
	public void nextLine() throws IOException
	{
		FileWriter toFile = new FileWriter("C:\\Users\\Jonothan\\eclipse-workspace\\WebScrapeProject\\src\\averagetpprice\\", true);
		toFile.write(System.lineSeparator());
		toFile.close();
	}
	
	public String getDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		String dateString = dateFormat.format(date);
		return dateString;
	}
	public void writeTenToText() throws IOException //Yo, Jono. If you come back in a few months and want to just run this, then this is the
	//function to run. Then launch the Graph class to see the data as a graph
	{
		nextLine();
		int waiting = 0;
	    while (waiting<10)
	    {
	     nextLine();	
	     Functions average = new Functions();
	     ArrayList<Double> arrayprices = average.getAllPricesArray();
	     double theAverage = average.getAverage(arrayprices);
	     String date = getDate();
	     toFile(date);
	     toFile(theAverage);
	     waiting++;
	     try {
	    	 Thread.sleep(10000);
	     	 } 
	     catch (InterruptedException e) 
	     	 {
	    	 	System.out.println("Sleep failed");
	     	 }
	    }
	    
	}
	  public void runOnceADay()
	  {

				Timer timer = new Timer();
				TimerTask tt = new TimerTask()
			   
				
				{
					public void run(){
						Calendar cal = Calendar.getInstance(); //this is the method you should use, not the Date(), because it is desperated.
		 
						int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23
		 
						if(hour == 14)
						
						{
							System.out.println("doing the scheduled task");
							try {
								writeTenToText();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				};
				timer.schedule(tt, 1000, 1000*60);//	delay the task 1 second, and then run task every minute
	  }
	  public void mips() //This was just used for an assembly language program
	   {
	      int t1,t2,t3;
	      t1=fact(2);
	      t2=fact(4);
	      t3=t1+t2;
	      System.out.println("t1: " + t1);
	      System.out.println("t2: " + t2);
	      System.out.println("t3: " + t3);
	 }
	  int fact(int n)
	 {
	     int i, f=1;
	     
	     for(i = n; i > 1; i--)
	           { f = f * i;}
	     return f;
	 }
	
	



	
	
//----------------------------------------------------DRIVER-----------------------------------------------------------------
	  public static void main(String[] args) throws IOException
	  {
       Write x = new Write();
       //String date = x.getDate();
       //x.toFile(date);
       //x.toFile("average: 2324");
      // x.nextLine();
       //x.getDelayArray();
       x.writeTenToText(); //This runs the program, all else is debugging, use this to see in a few months
       //x.runOnceADay();
       //x.mips(); //This was just used for Computer Organization II, has no bearing on this program
	   }
}
