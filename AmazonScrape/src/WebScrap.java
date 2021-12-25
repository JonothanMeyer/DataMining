/**
 * Program name: Web Scrap
 * @author Jonothan Meyer
 * Date: 3/4/2020
 * Description: Just basic into for web scraping, extracting strings from websites, writing
 * into a txt file and manipulating that info ext. 
 */

//import org.jsoup.*;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import org.jsoup.nodes.Document;
//import java.util.*;
public class WebScrap
{
   public void webScrap()
  {
    //Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
    //log(doc.title());
    //Elements newsHeadlines = doc.select("#mp-itn b a");
    //for (Element headline : newsHeadlines) {
      //log("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
    
  }
    
  public void getThings()
  {
            String url = "https://en.wikipedia.org/wiki/Text_messaging";
            try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            String html = Jsoup.connect(url).get().html();
            System.out.println("Title: " + title);
            
            //String description = doc.select("meta[name=description]").first().attr("content");
            //System.out.println("Description : " + description);
            
            //String keywords = doc.select("meta[name=keywords]").first().attr("content");
            //System.out.println("Keywords : " + keywords);
            Element search = doc.select("div.firstHeading").first();
            System.out.println("Thing: " + search);
            String text = doc.body().text();
            System.out.println("Text: " + text);
        }
        catch (IOException e)
        {
         System.out.println("No good dumbfuck"); 
        }
    } 
  
    
  //-------------------------------------Driver---------------------------------------------  
  public static void main(String[] args) throws IOException
  {
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    WebScrap ws = new WebScrap();
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
