package GradesScraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import AutoMail.autoMail;

import org.openqa.selenium.WebElement;
import java.util.stream.*;

public class GradeScraper {
	
	private static List<WebElement> rows;
	private static List<WebElement> compRows;
	private static List<WebElement> rowsCourse;
	private static List<WebElement> rowsGrade;
	private static List<String> courseString = new ArrayList<String>();
	private static List<String> gradeString= new ArrayList<String>();
	
	private static String mailContent = "Das sind deine Noten";
	
	
	public static void main(String [] args) throws EmailException, InterruptedException {
		
		gradeString.clear();
		courseString.clear();

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		ChromeOptions headlessOpt = new ChromeOptions();
		headlessOpt.addArguments("--headless");
		//headlessOpt.addArguments("--window-size=1000,900");
		WebDriver wDriver = new ChromeDriver(headlessOpt);

//Website aufruf und navigation zum Table
		wDriver.navigate().to("https://www.fh-bielefeld.de/qisserver/rds?state=user&type=0");
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content_max_portal_qis > div > div.portalcontent1 > div > ol > a > button")).click();

		wDriver.findElement(By.name("asdf")).sendKeys("mknauer");
		wDriver.findElement(By.name("fdsa")).sendKeys("a11roy");
		wDriver.findElement(By.cssSelector("#loginForm\\:login")).click();
		wDriver.findElement(By.cssSelector("#makronavigation > ul > li:nth-child(2) > a")).click();
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content_max_portal_qis > div > form > div > ul > li:nth-child(3) > a")).click();
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content > form > ul > li > a:nth-child(3) > img")).click();
//Anzahl der TR
		rows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
		System.out.println("Number of Rows: " + rows.size());
//Inhalte suchen Fach + Note
		rowsCourse = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr/td[2]"));
		rowsGrade = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr/td[4]"));

		for(WebElement c : rowsCourse) {
			courseString.add(c.getText());
		}
		for(WebElement g : rowsGrade) {
			gradeString.add(g.getText());
		}

		for(int i = 0; i < gradeString.size(); i++) {
			if(rowsCourse.get(i).getText().equals("1. Wahlpflichtmodul")) {
				continue;
			}else {
				System.out.println(rowsCourse.get(i).getText() + "	Note: " + rowsGrade.get(i).getText());
			}
			mailContent = rowsCourse.get(i).getText() + " Note: " + rowsGrade.get(i).getText() + "\n" + mailContent;
		}

		//		    autoMail.autoMail(mailContent);

		compRows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
	
		Thread.sleep(5000);
		
		while(rows.size() == compRows.size()) {
			wDriver.navigate().refresh();
			System.out.println("test1");
			Thread.sleep(20000); 
			System.out.println("test2");
			compRows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
			System.out.println(compRows.size());
			
		}
		System.out.println("Neue Noten Online");
	}
}
