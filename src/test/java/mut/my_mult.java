package mut;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class my_mult {
	ArrayList<String> url = new ArrayList<String>();
	
	
	ArrayList<String> pass = new ArrayList<String>();
	@Test
	public void web() throws AWTException, InterruptedException, CsvValidationException, IOException
	{
		String userdir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userdir + "/src/test/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		readCSV();
		System.out.print("read csv : ");
		System.out.print("read csv : "+url.size());
		for (int i=0; i <=url.size();i++)
		{
			//System.
		driver.get(url.get(i));
		
		driver.findElement(By.xpath("//a[text()='SECURITY CHECKOUT']")).click();
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass.get(i));

		openingNewTab();
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);

		}
		}
		}
		
		
	}
	
	public void openingNewTab() throws AWTException
	{
		   
	    Robot robot = new Robot();

	       // Simulate a mouse click
	                    robot.mousePress(InputEvent.BUTTON1_MASK);
	                    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	      // ctrl + T & ctrl TAB  

	                robot.keyPress(KeyEvent.VK_CONTROL);
	                robot.keyPress(KeyEvent.VK_T);

	                // CTRL+T is now pressed 

	                robot.keyRelease(KeyEvent.VK_T);
	                robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public void readCSV() throws CsvValidationException, IOException
	{
		//parsing a CSV file into Scanner class constructor  
		
		System.out.println("Inside read csv");
		String userdir = System.getProperty("user.dir");
		File file = new File(userdir + "/src/test/resources/Passcodes.csv");  
		 FileReader filereader = new FileReader(file);
		 
		  
	        // create csvReader object passing
	        // file reader as a parameter
	        CSVReader csvReader = new CSVReader(filereader);
	        String[] nextRecord;
	  
	        // we are going to read data line by line
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	url.add(nextRecord[1]);
	        	System.out.println(nextRecord[1]);
	        	pass.add(nextRecord[2]);
	        	System.out.println(nextRecord[2]);	        
	        }	           
	}
}
