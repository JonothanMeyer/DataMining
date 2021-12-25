
/**
 * Write a description of class wsReddPol here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import org.jsoup.nodes.Document;
import java.util.Scanner;  
public class searchForWord
{
  public void getThings()
  {
            String url = "https://www.reddit.com/r/gaming/comments/fgmklz/what_games_can_be_played_only_with_a_mouse/";
            String urrl = "https://www.reddit.com/r/politics/comments/fgjw4w/20_leading_economists_just_signed_a_letter/";
           
          Scanner input = new Scanner(System.in);
          System.out.println(url);
          System.out.println("Enter the URL of the page you are searching");
          url = input.next();
            try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            String html = Jsoup.connect(url).get().html();
            System.out.println("Title: " + title);
            
            //String description = doc.select("meta[name=description]").first().attr("content");
            //System.out.println("Description : " + description);
            
            String keywords = doc.select(".RichTextJSON-root").text();
            System.out.println("Comment words: " + keywords);
            System.out.println("The amount of characters in this element is: " + keywords.length());
            CharSequence cs = "dark";
            boolean contains = keywords.contains(cs);
            System.out.println("This page contains the word '" + cs + "': " + contains); 
            //System.out.println("Thing: " + keywords);
            //String text = doc.body().text();
            //System.out.println("Text: " + text);
        }
        catch (IOException e)
        {
         System.out.println("No good dumbfuck"); 
        }
    }
    
  //----------------------------------------------------------------------------------------Driver---------------------------------------------------------------------------------- 
  public static void main(String[] args) throws IOException
  {

    searchForWord ws = new searchForWord();
    ws.getThings();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
  }
}
