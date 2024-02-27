package Practice.RedBus;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {

	public static WebDriver driver;
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
		
		//Check PNR Status
		driver.findElement(By.xpath("//div[@class='ris-selector']/descendant::p[text()='Check PNR Status']")).click();
		driver.findElement(By.xpath("//input[@class='pnr-input-text']")).sendKeys("7395729495");
		driver.findElement(By.xpath("//button[contains(@class,' button')]")).click();
		
		//navigate to train page again
		driver.navigate().back();
		driver.navigate().back();
		
		//check live train status
		driver.findElement(By.xpath("//div[@class='ris-selector']/descendant::p[text()='Live Train Status']")).click();
		driver.findElement(By.xpath("//input[@class='pnr-input-text']")).sendKeys("12724");
		
		driver.findElement(By.xpath("//div[@class='lts_solr_wrap']/descendant::b")).click();
		
		driver.findElement(By.xpath("//button[text()='Check Status']")).click();
	}
}
