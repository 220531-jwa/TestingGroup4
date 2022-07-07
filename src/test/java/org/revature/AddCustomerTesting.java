package org.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class AddCustomerTesting 
{
	public static WebDriver driver;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		
		DesiredCapabilities dc = new DesiredCapabilities();
		
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		driver = new ChromeDriver();
	}
	
	@AfterAll
	public static void teardown()
	{
		driver.quit();
	}
	
	@Test
	@Order(1)
	public void AddCustomerTest() 
	{
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
		
		Actions act = new Actions(driver);
		
		
		
		WebElement managerLoginButton = new WebDriverWait(driver , Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")));
		
		act.moveToElement(managerLoginButton).build().perform();
		
		managerLoginButton.click();
		
		WebElement addCustomerButton = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]")));
		
		act.moveToElement(addCustomerButton).build().perform();
		
		addCustomerButton.click();
		
		WebElement firstNameInput = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")));
		
		
		firstNameInput.sendKeys("Stephen");
		
		WebElement lastNameInput = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")));
		
		lastNameInput.sendKeys("Jacot");
		
		WebElement postalCodeInput = new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input")));
		
		postalCodeInput.sendKeys("08851");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement submitAddCustomerButton = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")));
	
		act.moveToElement(submitAddCustomerButton).build().perform();
		
		submitAddCustomerButton.click();
		
		Alert a = driver.switchTo().alert();
		
		if(!a.equals(null))
		{
			System.out.println("Alert is present");
		}
		
		String x = a.getText();
		
		assertThat(x, containsString("Customer added successfully with customer id"));
		
		a.accept();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
			
			WebElement customersButton = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[3]")));
			
			act.moveToElement(customersButton).build().perform();
			
			customersButton.click();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement customer = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[6]/td[1]")));
			
			assertEquals("Stephen", customer.getText());
			
			assertNotEquals("Hi", customer.getText());
			
			
			WebElement opennedAccounts = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")));
		
			act.moveToElement(opennedAccounts).build().perform();
			
			opennedAccounts.click();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Select selector = new Select(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/select")));
			
			selector.selectByVisibleText("Stephen Jacot");
			
			WebElement s = selector.getFirstSelectedOption();
			
			String y = s.getText();
			
			assertThat(y, containsString("Stephen Jacot"));

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		} catch (UnhandledAlertException f)
//		{
//			f.printStackTrace();
//		}
		
		

//		TakesScreenshot screenshot = (TakesScreenshot) driver;
//		
//		File sourceScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
//		File destinationScreen = new File("screenshots/Addcustomervalid.jpeg");
//		
//		
//		try {
//			
//			// here we are actually doing the copying
//			FileUtils.copyFile(sourceScreenshot, destinationScreen);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	@Test
	@Order(2)
	public void NegativeCustomerTesting()
	{
		
		
	}

}
