package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
        WebElement textBox = driver.findElement(By.id("numb"));
        String newTextOne = "text";
        textBox.sendKeys(newTextOne);
        WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
        buttonSubmit.click();
        WebElement errorInfo = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", errorInfo.getText());


//      enter a text instead of a number, check that correct error is seen
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
        String enterKeys = "27";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        textInput.sendKeys((enterKeys));
        submitButton.click();

        driver.findElement(By.className("w3-btn"));
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());


//        enter number which is too small (below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {
        String enterKeys = "127";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        textInput.sendKeys(enterKeys);
        submitButton.click();

        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());


//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
        String enterKeys = "81";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        textInput.sendKeys(enterKeys);
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 81 is 9.00", alert.getText());
        alert.accept();

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
    }

    @Test
    public void correctSquareRootWithRemainder() {
        String enterKeys = "63";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        textInput.sendKeys(enterKeys);
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 63 is 7.94", alert.getText());
        alert.accept();

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
    }
}
