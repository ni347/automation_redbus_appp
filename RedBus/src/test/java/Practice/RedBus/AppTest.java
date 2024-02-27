package Practice.RedBus;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest{
	public static WebDriver driver;
	static String expectedDay="31";
	static String expectedMonth="May 2024";
	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//navigate to the train ticket page
		driver.findElement(By.xpath("//span[text()='Train Tickets']")).click();
		
		// scroll window
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,290)", "");
		
		//source box access
		driver.findElement(By.xpath("//div[contains(@class,'search-box-src')]")).click();
		
		//enter source
		String src = "New Delhi";
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys(src);
		Thread.sleep(3000);
		
		List<WebElement> sourceOptions = driver.findElements(By.xpath("//div[@class='solr_results_block']/div/div"));
		
		for(WebElement sourceOption:sourceOptions) {
			if(sourceOption.getText().equalsIgnoreCase(src)) {
				sourceOption.click();
				break;
			}
		}
		
		// destination box access
		driver.findElement(By.xpath("(//div[contains(@class,'form-control')])[2]")).click();
		
		//destination enter
		String dest = "Hyderabad";
		driver.findElement(By.xpath("//input[@id='dst']")).sendKeys(dest);
		Thread.sleep(3000);
		
        List<WebElement> destOptions = driver.findElements(By.xpath("//div[@class='solr_results_block']/div/div"));
		
		for(WebElement destOption:destOptions) {
			if(destOption.getText().contains(dest)) {
				destOption.click();
				break;
			}
		}
		
		//select date
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("div[class=home_calendar]"))).click().build().perform();
		driver.findElement(By.xpath("//div[@class='date-text']")).click();
		

		
		while(true) {
			String actualMonth = driver.findElement(By.xpath("//div[contains(@style,'flex-grow: 2')]")).getText();
			if(actualMonth.equalsIgnoreCase(expectedMonth)) {
				select_Date();
				break;
			}
			else {
				driver.findElement(By.xpath("(//div[contains(@style,'flex-grow: 1')])[2]")).click();
			}
		}
		
		//select free cancellation
		driver.findElement(By.xpath("//div[@class='checkbox_wrap']")).click();
		
		//click on search Trains button
		driver.findElement(By.xpath("//button[@class='search-btn ']")).click();
		
		//select a train
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='search_tupple_wrapper']/descendant::span[@class='srp_train_name']")));

		String trainName = "Telangana";
		List<WebElement> trainOptions = driver.findElements(By.xpath("//div[@class='search_tupple_wrapper']/descendant::span[@class='srp_train_name']"));

		
				
		for(int i=0;i<trainOptions.size();i++) {
			String train = trainOptions.get(i).getText();
			if(train.contains(trainName)) {
				driver.findElements(By.xpath("//div[@class='search_fare_class_wrap ']/descendant::div[text()='SL']")).get(i).click();
				break;
			}
		}
		
		
//		//add username
//	
		driver.findElement(By.xpath("//input[@placeholder='IRCTC username']")).click();
//		
//		// add irctc username
		driver.findElement(By.xpath("//input[@placeholder='IRCTC username']")).sendKeys("nikhilvats");
		driver.findElement(By.xpath("//div[@class='check_irctc_un']")).click();
		Thread.sleep(2000);
		

		//add new passenger
		driver.findElement(By.xpath("//div[@class='add_new_passenger_button']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Nikhil");
		driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys("24");
		driver.findElement(By.xpath("//div[@class='gender_select_block']/descendant::span[text()='Male']")).click();
		driver.findElement(By.xpath("//div[text()='Side Upper']")).click();
		driver.findElement(By.id("www.redbus.in/railways/travellerInfo/addpax")).click();
		
		//contact details
		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("nikhilvats3009@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Enter your phone no']")).sendKeys("8368531899");
		String city = "Delhi";
		driver.findElement(By.xpath("//input[@placeholder='City']")).click();
		driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys(city);
		Thread.sleep(1500);
		//select city
		List<WebElement> cityOptions = driver.findElements(By.xpath("//div[@class='display_flex_2 inp_list']"));
		for(WebElement selectCity:cityOptions) {
			if(selectCity.getText().contains(city)) {
				selectCity.click();
				break;
			}
		}
		
		//click on proceed button
		driver.findElement(By.xpath("//div[@class='proceed_button']")).click();
	}
	
	public static void select_Date() {
		List<WebElement> dateOptions = driver.findElements(By.xpath("(//div[@class='sc-jTzLTM cSXDfm']/div)[3]/span/div/span"));
		System.out.println(dateOptions.size());
		for(WebElement selectDate:dateOptions) {
			if(selectDate.getText().contains(expectedDay)) {
				selectDate.click();
			}
		}
	}
}