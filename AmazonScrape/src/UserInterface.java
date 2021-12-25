import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 

import org.jsoup.nodes.Document;

import javafx.stage.Stage;

public class UserInterface {
	 Functions functions = new Functions();
	 Write write = new Write();
	 Graph graph = new Graph();
	 Stage stage = new Stage();
	 ArrayList<Double> userArray = new ArrayList<Double>();
	 ArrayList<Double> tpArray = new ArrayList<Double>();
	 Scanner input = new Scanner(System.in);
	 double average = 0;
	public void chooseOption() throws IOException
	 {
		

	    int option = 0;
		do {
	     System.out.printf("%-50s\n%-25s\n%-25s\n%-25s\n%-25s\n", "Options:","1: User Input Options ","2: Toilet Paper Options ",
	    "3: No purpose yet.", "4: Exit program ");
	    input.nextLine();
	    option = input.nextInt();
	    
	      {switch (option) {
	        case 1: userOptions();
	                input.close();
	                break;
	        case 2: tpOptions();
	                break;
	        case 3: graph.start(stage);
	                break;
	        case 4: System.out.println("Goodbye");
	        	    break;
	        default: System.out.println("That is not a valid option");
	                       } 
	       }
	    
	    } while (option != 4);
	    //input.nextLine();
       }
	
	public void userOptions() throws IOException
	{
		input.nextLine();
		input.nextLine();
		String data = input.next();
		int userInput = Integer.parseInt(data);
	    int length = 0;
	    while (userInput != 4) {
	    System.out.printf("%-50s\n%-25s\n%-25s\n%-25s\n", "User Options","1: Input a search and build an array of prices",
	    "2: Get daily average of search","3: Write average to text file", "4: Exit");
	    
	    

	     switch (userInput) {
	        case 1: Document doc = functions.getUserDocument();
	                userArray = functions.getUserArray(doc);
	                functions.getAverage(userArray);
	                
	                break;
	        case 2: functions.getAverage(userArray);
	                break;
	        case 3: 
	        case 4: 
	                break;
	        default: System.out.println("That is not a valid option, please enter another number");
	        }
	     
	   }
	 }
	public void tpOptions() throws IOException
	{
	    
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
	    String data = "nothing";
	    int length = 0;
	    while (userInput != 4) {
	    System.out.printf("%-50s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n", "Amazon Toilet Paper Options:","1: Get Array of prices",
	    "2: Get daily average of search","3: Write refresh of 10 averages w/(date/time) to text file", "4: Graph averages", "5: Back");
	    userInput = input.nextInt();

	     switch (userInput) {
	        case 1: Document doc = functions.getDocument();
	                tpArray = functions.getPricesArray(doc);
	                break;
	        case 2: average = functions.getAverage(tpArray);
	        	    break;
	        case 3: write.writeTenToText();
	                break;
	        case 4: 
	                break;
	        case 5: break;        
	        default: System.out.println("That is not a valid option, please enter another number");
	        } 
	     
	   }
	}
	
		
//----------------------------------------------------DRIVER-----------------------------------------------------------------
	  public static void main(String[] args) throws IOException
	  {
     UserInterface x = new UserInterface();
     x.chooseOption();

	   }
}
