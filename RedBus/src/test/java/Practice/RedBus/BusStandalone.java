package Practice.RedBus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BusStandalone {

	public static WebDriver driver;
	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// scroll window
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)", "");
		
		//source
		driver.findElement(By.xpath("//div[@class='sc-ifAKCX gLwVlD']/descendant::input[@id='src']")).click();
		
		String src="ISBT Kashmiri";
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys(src);
		
		List<WebElement> srcOptions=driver.findElements(By.xpath("//li[contains(@class,'sc-iwsKbI jTMXri')]/descendant::text"));
		
		for(WebElement sourceSelect:srcOptions) {
			if(sourceSelect.getText().contains(src)) {
				sourceSelect.click();
				break;
			}
		}
		
		//destination
		driver.findElement(By.xpath("//div[@class='sc-ifAKCX gLwVlD']/descendant::input[@id='dest']")).click();
				
		String dest="Nashik Phata";
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys(dest);
		Thread.sleep(2000);
				
		List<WebElement> destOptions=driver.findElements(By.xpath("//li[contains(@class,'sc-iwsKbI jTMXri')]/descendant::text"));
		Thread.sleep(2000);
				
		for(WebElement destSelect:destOptions) {
			if(destSelect.getText().contains(dest)) {
				destSelect.click();
				break;
			}
		}
		
		
		//select date
		
		String mon = "Mar 2024";
		while(true) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='flex-grow: 2; font-size: 0.875rem;']")));
			String expectedMonth = monthElement.getText();
			if(expectedMonth.contains(mon)) {
				selectDate();
				break;
			}
			else {
				driver.findElement(By.xpath("//div[@style='flex-grow: 1; cursor: pointer;'][2]")).click();
			}
		}
		//search buses
		driver.findElement(By.id("search_button")).click();
		
		//select buses
		
		
	 }
	public static void selectDate() {
		String date = "20";
		List<WebElement> dateOptions = driver.findElements(By.xpath("//span[contains(@class,'DayTiles__CalendarDaysSpan-sc-1xum02u-1')]"));
		for(WebElement selectDate:dateOptions) {
			if(selectDate.getText().contains(date)) {
				selectDate.click();
				break;
			}
		}
	}
}
