
/**
 * Write a description of class scrapememes here.
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
public class scrapememes
{
  public void getThings()
  {
            String url = "https://www.reddit.com/r/AdviceAnimals/";
            String urrl = "https://www.reddit.com/r/politics/comments/fgjw4w/20_leading_economists_just_signed_a_letter/";
           
          System.out.println(url);
            try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            String html = Jsoup.connect(url).get().html();
            System.out.println("Title: " + title);
            
            //String description = doc.select("meta[name=description]").first().attr("content");
            //System.out.println("Description : " + description);
            
            String titles = doc.select("#t3").text();
            System.out.println("Comment words: " + titles);
            System.out.println("The amount of characters in this element is: " + titles.length());
            
            //System.out.println("Thing: " + keywords);
            //String text = doc.body().text();
            //System.out.println("Text: " + text);
        }
        catch (IOException e)
        {
         System.out.println("No good dumbfuck"); 
        }
    }
  public void getImages()
  {
    
              Document doc;
            try {

                //get all images
                doc = Jsoup.connect("https://www.reddit.com/r/AdviceAnimals/").get();
                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                for (Element image : images) {

                    System.out.println("\nsrc : " + image.attr("src"));
                    System.out.println("alt : " + image.attributes());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }  
    
    
    
    
    }
    
  //----------------------------------------------------------------------------------------Driver---------------------------------------------------------------------------------- 
  public static void main(String[] args) throws IOException
  {

    scrapememes sm = new scrapememes();
    sm.getThings();
    //sm.getImages();
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
