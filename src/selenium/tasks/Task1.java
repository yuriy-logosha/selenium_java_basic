package selenium.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task1 {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/tasks/enter_a_number";
    
    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(base_url);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
    	String expectedError = "Please enter a number";
    	String input = "Linkin' Park";
//        TODO
//        enter a text instead of a number, check that correct error is seen
    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void errorOnNumberTooSmall() {
    	String expectedError = "Number is too small";
    	String input = "25";
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    	
    	input = "49";
    	textArea.clear();
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void errorOnNumberTooBig() {
    	String expectedError = "Number is too big";
    	String input = "150";
//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    	
    	input = "101";
    	textArea.clear();
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
    	String expectedError = "";
    	double numberToRoot = 64;
    	double expectedResult = Math.sqrt(numberToRoot);
    	DecimalFormat df = new DecimalFormat("#.00");
    	String expectedResponse = "Square root of " + numberToRoot + " is " + df.format(expectedResult);
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(String.valueOf(numberToRoot));
    	button.click();
    	
    	Alert alert = driver.switchTo().alert();
    	String alertText = alert.getText();
    	alert.accept();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    	assertEquals(expectedResponse, alertText);
    }

    @Test
    public void correctSquareRootWithRemainder() {
    	String expectedError = "";
    	double numberToRoot = 75;
    	double expectedResult = Math.sqrt(numberToRoot);
    	DecimalFormat df = new DecimalFormat("#.00");
    	expectedResult = (double)Math.round(expectedResult * 100d) / 100d;
    	
    	String expectedResponse = "Square root of " + numberToRoot + " is " + df.format(expectedResult);
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(String.valueOf(numberToRoot));
    	button.click();
    	
    	Alert alert = driver.switchTo().alert();
    	String alertText = alert.getText();
    	alert.accept();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    	assertEquals(expectedResponse, alertText);
    }
    
    @Test
    public void errorOnEmptyInput() {
    	String expectedError = "You haven't entered anything";
    	String input = "";

    	WebElement textArea = driver.findElement(By.id("numb"));
    	WebElement errorArea = driver.findElement(By.id("ch1_error"));
    	WebElement button = driver.findElement(By.className("w3-btn"));
    	
    	textArea.sendKeys(input);
    	button.click();
    	
    	assertTrue(errorArea.getText().equals(expectedError));
    }
}