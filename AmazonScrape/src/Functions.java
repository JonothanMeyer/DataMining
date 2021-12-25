import org.jsoup.select.Elements;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import java.lang.reflect.Array;

import org.jsoup.nodes.Document;
import java.util.Scanner;
import java.util.*;

public class Functions {
     
	
	public Document getDocument() throws IOException //grabs and returns the Document for toilet paper searching on the amazon web page.
     {
       String url = "https://www.amazon.com/s?k=toilet+paper&crid=2D1P4CI7VCNET&sprefix=toilet%2Caps%2C222&ref=nb_sb_ss_i_1_6";
       String funurl = "https://www.amazon.com/s?k=friends&ref=nb_sb_noss_2";
       System.out.println(url);
       Document doc = Jsoup.connect(url).get();
       System.out.println("Title: " + doc.title()); 
       return doc;
     }
	
	public Document getNextPage(int page) throws IOException
	{
	       String url = "https://www.amazon.com/s?k=toilet+paper&page=" + page + "&qid=1585861737&ref=sr_pg_" + page;
	       System.out.println(url);
	       Document doc = Jsoup.connect(url).get();
	       System.out.println("Title: " + doc.title()); 
	       return doc;	
	}
	
	public int getNumOfPages() throws IOException
	{
		String url = "https://www.amazon.com/s?k=toilet+paper&crid=2D1P4CI7VCNET&sprefix=toilet%2Caps%2C222&ref=nb_sb_ss_i_1_6";
		Document doc = Jsoup.connect(url).get();
		Elements name = doc.select("#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.sg-col-20-of-24.s-matching-dir.sg-col-28-of-32.sg-col-16-of-20.sg-col.sg-col-32-of-36.sg-col-8-of-12.sg-col-12-of-16.sg-col-24-of-28 > div > span:nth-child(5) > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(62) > span > div > div > ul > li:nth-child(6)");
		
	    System.out.println("Amount of pages for this search: " + name.text());

		//int pageNum = Integer.parseInt(name.text());
	    int pageNum = 7;
        return pageNum;
	}
	
	public Document getUserDocument() throws IOException //prompts user for a search, and grabs the amazon web page of that search, and returns the scraped Document of that web page.
	{
		Scanner input = new Scanner(System.in);
		input.nextLine();
		System.out.println("What are you searching for? ");
		String userInput = "https://www.amazon.com/s?k=" + input.nextLine();
		userInput = userInput.replaceAll(" ", "+");
		userInput = userInput + "&ref=nb_sb_noss";
		//input.close();
		System.out.println(userInput);
		Document doc = Jsoup.connect(userInput).get();
		System.out.println("Title: " + doc.title());
		return doc;
	}
	
	public ArrayList<Double> getUserArray(Document doc) //User prompted document is the condition. Generates and returns an ArrayList of the prices from that amazon web search.
	{
		ArrayList<Double> userArray = new ArrayList<Double>();
		Elements elementprices = doc.getElementsByClass("a-price-whole");
			
		System.out.println("Elements size: " + elementprices.size());
		int count = 0;
		
		for (Element element: elementprices )
		{
			String currentLink = element.text();
			currentLink = currentLink.replaceFirst(",", "");
			System.out.println(element.text());
			int endIndex = currentLink.lastIndexOf("."); 
			currentLink = currentLink.substring(0, endIndex);
			double price = Double.parseDouble(currentLink) + 1;
			userArray.add(price);			 		
		    //System.out.println(arrayprices.get(i-1)); //Just wanted to make sure arraylist was getting populated ya'll
			System.out.println(userArray.get(count));
			count++;
		}
		return userArray;
	}
	
	public ArrayList<Integer> getCheckArray(Document doc) //**Not Completed** 
	{
	  ArrayList<Integer> arraycheck = new ArrayList<Integer>();
	  Elements elementauto = doc.getElementsByClass("a-row");
	  //System.out.println("Check: " + doc.child(0));
	  System.out.println("elementauto size: " + elementauto.size());
	  for (Element element: elementauto)
	  {
		  String currentLink = element.className();
		  System.out.println("Elements check unavailable: " + currentLink);
	  }
		
		return arraycheck;
	}
	
	public ArrayList<String> getDelayArray() //Will read in from notepad and put the daily averages in an arraylist.
	{
		return null;
	}
 
	 public ArrayList<Double> getPricesArray(Document doc) //
	   {
		 ArrayList<Double> arrayprices = new ArrayList<Double>();
		 Elements elementprices = doc.getElementsByClass("a-price-whole");
		
		System.out.println("Elements size: " + elementprices.size());
		int count = 0;
		
		for (Element element: elementprices )
		{
			String currentLink = element.text();
			int endIndex = currentLink.lastIndexOf("."); 
			currentLink = currentLink.substring(0, endIndex);
			double price = Double.parseDouble(currentLink) + 1;
			count++;
			arrayprices.add(price);			 		
		    //System.out.println(arrayprices.get(i-1)); //Just wanted to make sure arraylist was getting populated ya'll
		}
		 return arrayprices;
	   }
	 public ArrayList<Double> getAllPricesArray() throws IOException //Scrapes the 7 pages of prices on amazon for 'TP' and puts them into an ArrayList
	 {   
		 ArrayList<Double> arrayprices = new ArrayList<Double>();
		 Functions function = new Functions();
		 int pageNum = function.getNumOfPages() + 1;
		 for (int i=1;i<pageNum;i++)
		 {
		   Document doc = function.getNextPage(i);
		   Elements elementprices = doc.getElementsByClass("a-price-whole");
		   System.out.println("Elements size: " + elementprices.size());
		   int count = 0;
		   for (Element element: elementprices )
			{
				String currentLink = element.text();
				int endIndex = currentLink.lastIndexOf("."); 
				currentLink = currentLink.substring(0, endIndex);
				double price = Double.parseDouble(currentLink) + 1;
				count++;
				arrayprices.add(price);			 		
			    System.out.println(arrayprices.get(count -1)); //Just wanted to make sure arraylist was getting populated ya'll
			}
		 }
		 
		 return arrayprices;
	 }
	 

	 
	 public double getAverage(ArrayList<Double> arrayprices) //Imput is any created ArrayList of prices. Finds and returns the average of those prices.
	 {
	  double total = 0;
	  double price = 0;
	  double average =  0;
	   for (int i=0;i<arrayprices.size();i++)
	   {
		 price = arrayprices.get(i);
		 total = total + price;
	   }
	   average = total / arrayprices.size();
	   System.out.println("The average price for today is: $" + average);
	   System.out.println("The  amount of elements scraped is: " + arrayprices.size());
	   return average;
	 }
	
	 public ArrayList<String> getStringArray(Document doc) //**Not Completed**
	 {
		 ArrayList<String> arraystrings = new ArrayList<String>();
		 Elements name = doc.select("#search > div.s-desktop-width-max.s-desktop-content.sg-row"
		 		+ " > div.sg-col-20-of-24.sg-col-28-of-32.sg-col-16-of-20.sg-col.sg-col-32-of-36.sg-col-8-of-12.sg-col-12-of-16.sg-col-24-of-28"
		 		+ " > div > span:nth-child(10) > div > div > span > div > div > ul > li:nth-child(6)");
         System.out.println("Amount of pages for this search: " + name.text());
         
		 for (Element element: name)
         {
       	  String current = element.text();
       	  System.out.println("Things: " + element.text());
       	  arraystrings.add(current);
       	  //count++;
       	  //System.out.println();
       	//System.out.println("String #" + count + ": " + current + "        -Index number: " + elm.id());
         } 
		return arraystrings; 
	 } 
	 
	 public String[] getDailyAvg(String[] delayArray) //Used for finding and returning the average of all the daily averages.
	 {
		 String[] arrayDailyAverages = new String[delayArray.length/11];
		 //System.out.println("Substring of 34-37: " + stringCurrentPrice);
		 int newArrayIndex = 0;
		 double total = 0;
		 double dailyAvg = 0;
		 for(int i=0;i<delayArray.length;i++)
		 {
			 if(delayArray[i].isEmpty())
			 {
				dailyAvg = (total/10);
				
				arrayDailyAverages[newArrayIndex] = "Date: " + delayArray[i-1].substring(6,15) + "  Avg: $" + dailyAvg;
				System.out.println(arrayDailyAverages[newArrayIndex]);
				newArrayIndex++;
				total = 0;
			 }
			 else {
			 String stringCurrentPrice = delayArray[i].substring(32, 37); //get substring from array (index 32-37)
			 double doubleCurrentPrice = Double.parseDouble(stringCurrentPrice);
			 total = total + doubleCurrentPrice;
			 System.out.println("This is the total: " + total + "This is the current price: " + doubleCurrentPrice);
			      }
		 }
		 return arrayDailyAverages;
	 }
	 
	 public double changeInDailyAvg() //**Not Finished* Will just find the change in daily averages.
	 {
		 double thing = 89;
		 return thing;
	 }
	 
	 
	 
	 public ArrayList<String> getStringArray()
	 {
		ArrayList<String> arraystring = new ArrayList<String>();
		 
		 
		return arraystring;
		 
		 
	 }
	
private String arrayprices(int a) {
		// TODO Auto-generated method stub
		return null;
	}


//----------------------------------------------------DRIVER-----------------------------------------------------------------
	  public static void main(String[] args) throws IOException
	  {
        Functions x = new Functions();
        Document doc = x.getDocument();
        Write write = new Write();
        //ArrayList<Double> arrayprices = x.getPricesArray(doc);
        ArrayList<String> arraystrings = x.getStringArray(doc);
        //ArrayList<Double> arrayprices = x.getAllPricesArray();
        //x.getAverage(arrayprices);
	    //x.getCheckArray(doc);
        
        //String[] delayArray = write.getDelayArray();
        //x.getDailyAvg(delayArray);
        //Document doc = x.getUserDocument();
	    //ArrayList<Double> userArray = x.getUserArray(doc);
	    //x.getAverage(userArray);
	    
	    }
}
