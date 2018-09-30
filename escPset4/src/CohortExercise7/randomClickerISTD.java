package CohortExercise7;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class randomClickerISTD {
	
	public static void main(String[] args) throws InterruptedException {		
		System.setProperty("webdriver.gecko.driver","/home/zenger/Downloads/gecko/INSTALL_DIR");
 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://istd.sutd.edu.sg/");
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		
		int listOfEle = links.size();
		Random rand = new Random();
		while(true) {
			
			int randSelection=rand.nextInt(listOfEle);
			if (links.get(randSelection).getAttribute("href") == null)
                continue;
			boolean linkLoaded= true; //initialised as true first
			while(linkLoaded) {
				try {
                    driver.navigate().to(links.get(randSelection).getAttribute("href"));
                    Thread.sleep(5000);
                    driver.navigate().back();
                    links = driver.findElements(By.tagName("a"));
                    linkLoaded= false;// go back to first while loop
                } catch (StaleElementReferenceException e) {
                	//if any error change the LL to true
                    linkLoaded = true;
                }
			}
			
		}
	}
}