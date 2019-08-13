package uk.gov.ho.domain.component.ui.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.*;

//import Page_BasePage;

@DefaultUrl("https://www.google.co.uk")

public class Page_GoogleHomepage extends PageObject {

	@FindBy(name = "q") WebElement searchBox;
	@FindBy(name = "btnK") WebElement searchButton;

//	@FindBy(xpath = "//button[contains(.,'Google Search')]") WebElement searchButton;
	//WebElement searchMain;



//    public static WebDriver driver;
	// launch Chrome

/*	public void launchBrowser() {
		driver = new ChromeDriver();
	// maximise the browser
		driver.manage().window().maximize();
	}
	
	public void openGoogleURL() {
	 // declare and initialise the variable to store the expected title of the webpage	
		String baseUrl = "https://www.google.com";
        String expectedTitle = "Google";
        String actualTitle = "";
		
     // direct it to the Base URL
        driver.get(baseUrl);
     // get the actual value of the title
        actualTitle = driver.getTitle();
        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
/*       if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("The Google Page Is Displayed!");
        } else {
            System.out.printf("The Google Page Is NOT Displayed, it's: " + actualTitle );
         // close the Browser 
    		driver.close();
    		System.out.println("Test terminated as desired webpage not loaded");
        }
        
        //driver.get("http://www.google.com");
	}

*/
   //FindBy(xpath = "//input[contains(@id,\"search-main-\")]")
	public void checkSearchBoxIsDisplayed() {
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("I am here");
		if(searchBox.isDisplayed()) {
			System.out.println("Search text box is displayed");
		} else {
			System.out.println("Search text box is NOT displayed");
		}
	}


	
	public void checkGoogleSearchButtonIsDisplayed() {
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("I am at button");

	//	System.out.println(searchButton.getText());

		if(searchButton.isDisplayed()) {
			System.out.println("Google Search button is displayed");
		} else {
			System.out.println("Google Search button is NOT displayed");
		}
	}

	/*

	public void checkImFeelingLuckyButtonIsDisplayed() {
		if(driver.findElement(By.name("btnI")).isDisplayed()) {
			System.out.println("I'm Feeling Lucky button is displayed");
		} else {
			System.out.println("I'm Feeling Lucky button is NOT displayed");
		}
		
	}
	
	public void clickGMaillink() {
		if(driver.findElement(By.xpath("//*[@id=\"gbw\"]/div/div/div[1]/div[1]/a")).isDisplayed())
		{
			System.out.println("The GMail link is displayed");
			driver.findElement(By.xpath("//*[@id=\"gbw\"]/div/div/div[1]/div[1]/a")).click();
		} else {
			System.out.println("The GMail link is NOT displayed");
		}
		
		// close the Browser 
		//driver.close();
	}
	
	public void searchOnGoogleHomepage(String keyword) {
		driver.findElement(By.name("q")).sendKeys(keyword); 
		driver.findElement(By.name("btnK")).submit();
		System.out.println("Search completed successfully");
		
		// close the Browser 
		driver.close();
		System.out.println("Test script executed successfully.");

		} */

}
