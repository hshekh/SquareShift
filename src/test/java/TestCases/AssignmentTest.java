package TestCases;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestPackage.TestBase;

public class AssignmentTest {
	static WebDriverWait wait= null;

	@BeforeMethod
	public static void setup() {
		TestBase.intialize();
	}

	@Test(enabled=true)
	public static void Test1() {
		WebDriver d = TestBase.driver;
		d.get("https://www.channelnewsasia.com/");
		String TeaserTitle = TestBase. get_ClickTeaserTitle("teaser__title");
		String ArticleTitle=TestBase.GetArticleHead("article__title","class");
		Assert.assertEquals(TeaserTitle,ArticleTitle);
		TestBase.ScrolltoEnd("article__read-full-story-button",2);
		String ArtHeader = TestBase.GetArticleHead("//*[@id=\"main\"]/div[3]/div[3]/div[2]/div[1]/div[2]/div/article/header/h1", "xpath");
		d.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div[3]/div[2]/div[1]/div[3]/button")).click();
		Assert.assertEquals(ArtHeader,d.getTitle());

	}

	@Test(enabled=true)
	public static void Test2() {
		WebDriver d = TestBase.driver;
		d.get("https://www.channelnewsasia.com/news/international");
		TestBase.switchSelectto("//*[@id=\"home-switcher\"]", "Singapore Edition");
		String TeaserTitle = TestBase. get_ClickTeaserTitle("teaser__title");
		String ArticleTitle=TestBase.GetArticleHead("article__title","class");
		Assert.assertEquals(TeaserTitle,ArticleTitle);
		TestBase.ScrolltoEnd("article__read-full-story-button",2);
		String ArtHeader = TestBase.GetArticleHead("//*[@id=\"main\"]/div[3]/div[3]/div[2]/div[1]/div[2]/div/article/header/h1", "xpath");
		d.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div[3]/div[2]/div[1]/div[3]/button")).click();
		Assert.assertEquals(ArtHeader,d.getTitle());

	}


	@Test(enabled=true)
	public static void Test3() {
		WebDriver d = TestBase.driver;
		d.get("https://www.channelnewsasia.com/news/international");
		wait=new WebDriverWait(d, 20);
		List<WebElement> ls = d.findElements(By.className("button-main-nav__button-open-text"));
		System.out.println(ls.size());
		Iterator<WebElement> itr = ls.iterator();
		int i=0;
		while(itr. hasNext()) {
			String s = itr.next().getText();
			System.out.println(s);
			if (s.equalsIgnoreCase("All Sections")){
				ls.get(i).click();
			}
			i++;
			System.out.println(i);
		}
		String weather = d.findElement(By.xpath("//*[@id=\"menu-1\"]/div/div[2]/div/div[2]/ul/li[7]/a")).getAttribute("href");
		System.out.println(weather);
		d.navigate().to(weather);
		if (d.findElement(By.xpath("//*[@id=\"section-content-8527636\"]/div/div/div/div/div[1]/div[3]/h2")).getText()=="Singapore") {
			System.out.println("Singpore Weather Today : " +d.findElement(By.id("today-weather-desc")).getText());
		}

	}

	@AfterMethod
	public static void teardown() {
		TestBase.quit();
	}
}
