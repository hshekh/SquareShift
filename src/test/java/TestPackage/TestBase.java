package TestPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	private static  String ArticleHead = null;
	public static RemoteWebDriver driver=null;
	static WebDriverWait wait= null;
	public static void intialize() {

		if (driver==null) {	
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Himank\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
			//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}

	}
	public static void quit() {
		driver.quit();
		System.out.println("Quitting Driver");
		driver=null;
	}

	

	public static String get_ClickTeaserTitle(String T_title) {
		//String TeaserTitle = driver.findElement(By.className("teaser__title")).getText();
		String TeaserTitle = driver.findElement(By.className(T_title)).getText();
		System.out.println(TeaserTitle+"******");
		driver.findElement(By.className(T_title)).click();
		return TeaserTitle;
	}

	public static String GetArticleHead(String Art_path, String loc) {
		if (loc=="class") {
			ArticleHead = driver.findElement(By.className(Art_path)).getText();

		}
		else if (loc=="xpath") {
			ArticleHead = driver.findElement(By.xpath(Art_path)).getText();

		}
		return ArticleHead;

	}



	public static void ScrolltoEnd(String btn_path,int i) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 20);
		int eleCnt=0;
		while(eleCnt<i) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			wait.until(ExpectedConditions.elementToBeClickable(By.className(btn_path)));
			js.executeScript("window.scrollBy(0,-250)");
			js.executeScript("window.scrollBy(0,+250)");
			eleCnt= driver.findElements(By.className(btn_path)).size();
			//System.out.println("Scrolling from Loop"+eleCnt);
		}

	}
	
	
	public static void switchSelectto(String path,String value) {
		WebElement sel = driver.findElement(By.xpath(path));
		Select select=new Select(sel);
		select.selectByVisibleText(value);
	}
}