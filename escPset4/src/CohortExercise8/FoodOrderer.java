package CohortExercise8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FoodOrderer {
	static String User="<insert user here>";
	static String Pass="<insert password here>";
	static String Post ="<insert user here>";
	
public static void main(String[] args) {
	System.setProperty("webdriver.gecko.driver","/home/zenger/Downloads/gecko/INSTALL_DIR");
	 
	WebDriver driver = new FirefoxDriver();
	driver.get("https://deliveroo.com.sg/login");
	java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
	WebElement username = driver.findElement(By.name("login_email"));
	username.sendKeys(User);
	// now locate the password field in the current page
	WebElement password = driver.findElement(By.name("login_password"));	
	password.sendKeys(Pass);
	username.submit();
	password.submit();
	try {
		//wait until POSTALCODE element appears on the screen
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("postcode")));
		WebElement postcode = driver.findElement(By.name("postcode"));
		postcode.sendKeys(Post);
		postcode.submit();
	} catch (Exception NoSuchElementException) {
		System.out.println("login name invalid");
	}
	
	
}
}
