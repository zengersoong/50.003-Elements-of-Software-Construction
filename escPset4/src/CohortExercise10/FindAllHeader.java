package CohortExercise10;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindAllHeader {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver","/home/zenger/Downloads/gecko/INSTALL_DIR");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.reddit.com/r/compsci/");
        // Collect the links
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());
        // Loop through all the list
       for(int i = 0; i < links.size(); i++)
        {
            System.out.println("Navigating to... "  + links.get(i).getAttribute("href"));
            if (links.get(i).getAttribute("href") == null)
                continue;
            boolean pageElementLoaded = true;
            while(pageElementLoaded) {
            	
                try {
                    driver.navigate().to(links.get(i).getAttribute("href"));
                    Thread.sleep(5000);
                    
                    
                    if(driver.getTitle().equals(null) || driver.getTitle().length() == 0){
                        System.out.println("NULL TITLE!");
                       assert(false);
                    }
                    driver.navigate().back();
                    System.out.println("Navigating back to start..");
                    links = driver.findElements(By.tagName("a"));
                    System.out.println("Visited to..." + links.get(i).getAttribute("href"));
                    pageElementLoaded = false;
                } catch (StaleElementReferenceException e) {
                    pageElementLoaded = true;
                }
            }
        }
		
		
		
		
	}

}
