import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Graph extends Application {

     Write write = new Write();
     Functions function = new Functions();
     
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override public void start(Stage stage) throws IOException 
	{
		String[] data = write.getDelayArray();
		String[] averages = function.getDailyAvg(data);
		
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("$$");
        
        
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Amazon Average Toilet Paper Prices");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("TP Price Average");
        
        for (int i=0;i<averages.length;i++)
        {
        String date = averages[i].substring(6,11);	
        String stringAvgPrice = averages[i].substring(23, 27);
        double avgPrice = Double.parseDouble(stringAvgPrice);
        series.getData().add(new XYChart.Data(date, avgPrice));

        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }

	
//---------------------------------------------------------------------DRIVER---------------------------------------------------------------------	
 
    public static void main(String[] args) {
        launch(args);
    }
}