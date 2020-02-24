package scraper.MsgBoardScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MsgBoardReader {
	
	private static List<WebElement> stringResults;
    private static List<String>textFromRes = new ArrayList<String>();
    private static WebElement common;
    private static WebElement event;
    
   

    /* noch nicht implementiert
    String mailContent;
    */
    
    //Site öffnen und durchsuchen
    public static void search(String user, String password) throws InterruptedException, IOException {
    	
    	System.setProperty("webdriver.chrome.driver", "chromedriver");

    	ChromeOptions headlessOpt = new ChromeOptions();
    	headlessOpt.addArguments("--headless");
    	WebDriver driver = new ChromeDriver();

    	driver.navigate().to("https://www.fh-bielefeld.de/mitteilungsboard");
    	driver.findElement(By.name("Ecom_User_ID")).sendKeys(user);
    	driver.findElement(By.name("Ecom_Password")).sendKeys(password);

    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    	driver.findElement(By.name("loginButton2")).click();

    	try {
    		common = driver.findElement(By.cssSelector("#mboard > div:nth-child(3) > div.tb_title2.open"));
    		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    		common.click();
    	} catch(NoSuchElementException n) {
    		JOptionPane.showMessageDialog(null, "Element nicht vorhanden.");
    	}

    	stringResults = driver.findElements(By.xpath("//div[@class='msg_block']"));
    	for(WebElement b : stringResults ) {
    		textFromRes.add(b.getText());
    	}
    	boolean noNews = true;

    	for(String result : textFromRes ) {
    		if(result.toLowerCase().indexOf( "Kommunikation und Projektmanagement".toLowerCase()) != -1 || result.toLowerCase().indexOf("Mathe für Ökonomen".toLowerCase()) != -1 
    				|| result.toLowerCase().indexOf("netzwerk".toLowerCase()) != -1 || result.toLowerCase().indexOf("software engineering".toLowerCase()) != -1
    				|| result.toLowerCase().indexOf("technology of erp systems".toLowerCase()) != -1 || result.toLowerCase().indexOf("tutorium mathematik für ökonomen (ws 19-20 - 5) nolting".toLowerCase()) != -1) {
    			noNews = false;
    			System.out.print(result);
    			MsgBoardFrame.getTextResult().append(result + "\n");
    			MsgBoardFrame.getTextResult().append("___________________________________________" + "\n");
    		}
    	}		
    	if(noNews) {
    		System.out.print("Keine Infos.");
    		MsgBoardFrame.getTextResult().setText("Keine Infos");
    	}
    }
}
