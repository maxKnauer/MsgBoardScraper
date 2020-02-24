package GradesScraper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailContentWriter {
	
	public static String mailContentWriter(WebDriver wDriver, String mailContent, List<String> gradeString, List<WebElement>rowsCourse, List<WebElement>rowsGrade, List<String> courseString ) {
		
		//Listen Clear, damit keine OutOfBounds Exception
		gradeString.clear();
		courseString.clear();
		mailContent = "Das sind diene Noten"; //sonst Doppelt sich der Inhalt 
		
		//Elements finden und Listen befüllen
		rowsCourse = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr/td[2]"));
		rowsGrade = wDriver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[5]/table/tbody/tr/td/div/div[2]/form/table[2]/tbody/tr/td[4]"));

		for(WebElement c : rowsCourse) {
			courseString.add(c.getText());
		}
		for(WebElement g : rowsGrade) {
			gradeString.add(g.getText());
		}
		
		//MailContent befüllen
		for(int i = 0; i < gradeString.size(); i++) {
			if(rowsCourse.get(i).getText().equals("1. Wahlpflichtmodul")) {
				continue;
			}else {
				//System.out.println(rowsCourse.get(i).getText() + "	Note: " + rowsGrade.get(i).getText());
			}
			mailContent = rowsCourse.get(i).getText() + " Note: " + rowsGrade.get(i).getText() + "\n" + mailContent;
		}
		return mailContent;
	}
}
