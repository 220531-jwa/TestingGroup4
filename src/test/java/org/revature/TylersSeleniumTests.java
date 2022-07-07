package org.revature;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TylersSeleniumTests {
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        File chrome = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        WebElement customerLogin = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")));


        customerLogin.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Select nameSelector = new Select(driver.findElement(By.xpath("//*[@id=\"userSelect\"]")));

        nameSelector.selectByVisibleText("Harry Potter");
        
        WebElement secondLogin = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/form/button")));

        secondLogin.click();

    }


    @Test
    public void successfulDeposit() {
        WebElement depositButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));

        depositButton.click();

        WebElement depositBox = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")));

        depositBox.sendKeys("1.00");

        depositButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button"));

        depositButton.click();

        WebElement balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));

        assertEquals("1", balance.getText());
    }

    @Test
    public void unsuccessfulDeposit() {
        WebElement depositButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));

        depositButton.click();

        WebElement depositBox = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")));

        depositBox.sendKeys("-1.00");

        depositButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button"));

        depositButton.click();

        WebElement balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));

        assertEquals("1", balance.getText());
    }

    @Test
    public void badInput() {
        WebElement depositButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));

        depositButton.click();

        WebElement depositBox = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")));

        depositBox.sendKeys("3.1415");

        depositButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button"));

        depositButton.click();

        WebElement balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"));

        assertEquals("1", balance.getText());
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }
}
