package CohortExercise9;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleForms {
	public static String userString="";
	static String UserName = "<<insertRealUSerHere>>";
	static String InvalidName="";
	public Integer length;
	static String pass = "<<insert real password here>>";
public static void main(String[] args) {
	System.setProperty("webdriver.gecko.driver","/home/zenger/Downloads/gecko/INSTALL_DIR");
	 
	WebDriver driver = new FirefoxDriver();
	driver.get("https://accounts.google.com/ServiceLogin/identifier?flowName=GlifWebSignIn&flowEntry=AddSession");
	java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
	WebElement username = driver.findElement(By.name("identifier"));
    // locate the "Next" button in the account page
    WebElement nextButton = driver.findElement(By.id("identifierNext"));
    

	for(int i =0;i<20;i++) {
	    Random rLength = new Random();
		int length = rLength.nextInt(10);
			for (int n=0;n<length;n++) {
				userString=""; //empty this out
				Random boolAlphaOrInteger = new Random();
				int choice = boolAlphaOrInteger.nextInt(2);
				if (choice == 0) generateNextInteger();
				else generateNextAlpha();		
			}
		InvalidName = userString + "@gmail.com";
		
        username.sendKeys(InvalidName);
        nextButton.click();
        username.clear();
        InvalidName ="";
    }
	
	username.sendKeys(UserName);
    nextButton.click();
    try {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        WebElement password = driver.findElement(By.name("password"));

        password.sendKeys(pass);

        nextButton = driver.findElement(By.id("passwordNext"));
        nextButton.click();
    } catch (Exception NoSuchElementException) {
        System.out.println("login name invalid");
    }

	
}
	
	
	
    
public static void generateNextAlpha() {
	Random alphaChar = new Random();
	char c = (char) (alphaChar.nextInt(26) + 'a');
	
	userString = userString+c;
	
	
}
public static void generateNextInteger() {
	Random rInteger = new Random();
	Integer integer = rInteger.nextInt(9);
	String stringInteger = integer.toString();
	userString =userString+stringInteger;
	
	
}
}
