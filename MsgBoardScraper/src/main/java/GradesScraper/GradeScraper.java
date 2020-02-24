package GradesScraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private static String mailContent;
	
	private static File file = new File("/Users/maxknauer/git/MsgBoardScraper/MsgBoardScraper/zugang");
	
	public static void main(String [] args) throws EmailException, InterruptedException, IOException {
//Login Data aus File
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String zeile;
		String [] loginData = new String[2];
		int i = 0;
		
		while((zeile = br.readLine()) != null ) {
			
			loginData[i]= zeile;
			i++;
		}
		br.close();
		fr.close();
//Def WebDriver	
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions headlessOpt = new ChromeOptions();
		headlessOpt.addArguments("--headless");
		WebDriver wDriver = new ChromeDriver(headlessOpt);
//Webite Aufruf, Navigation zum Table
		wDriver.navigate().to("https://www.fh-bielefeld.de/qisserver/rds?state=user&type=0");
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content_max_portal_qis > div > div.portalcontent1 > div > ol > a > button")).click();
		wDriver.findElement(By.name("asdf")).sendKeys(loginData[0]);
		wDriver.findElement(By.name("fdsa")).sendKeys(loginData[1]);
		wDriver.findElement(By.cssSelector("#loginForm\\:login")).click();
		wDriver.findElement(By.cssSelector("#makronavigation > ul > li:nth-child(2) > a")).click();
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content_max_portal_qis > div > form > div > ul > li:nth-child(3) > a")).click();
		wDriver.findElement(By.cssSelector("#wrapper > div.lsf_tab_outer > table > tbody > tr > td > div > div.content > form > ul > li > a:nth-child(3) > img")).click();
//Anzahl der TR
		rows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
		System.out.println("Number of Rows: " + rows.size());
//in Methode mailContent auslesen und schreiben, Initialmail versenden
		mailContent = MailContentWriter.mailContentWriter(wDriver, mailContent, gradeString, rowsCourse, rowsGrade, courseString);
		autoMail.autoMail(mailContent);	
// Vergleichs definition 
		compRows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
		Thread.sleep(1000);
//Prüfen ob neues TR hinzugefügt wurde
		while(rows.size() == compRows.size()) {
			wDriver.navigate().refresh();
			//System.out.println("Änderung");
		    Thread.sleep(10000); 
			//System.out.println("Element wird gesucht");
			compRows = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr"));
			//System.out.println(compRows.size());	
		}
		mailContent = MailContentWriter.mailContentWriter(wDriver, mailContent, gradeString, rowsCourse, rowsGrade, courseString);
		autoMail.autoMail(mailContent);
		System.out.println("Neue Noten Online");
	}
}
