import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import javafx.application.Application;

public class Driver extends Application {

	public static void main(String[] args) throws IOException
	{
     UserInterface userFace = new UserInterface();
     userFace.chooseOption();	
	}

}
