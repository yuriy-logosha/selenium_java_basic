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
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement textArea = driver.findElement(By.id("numb"));
        String newText = "Text instead of a number";
        textArea.sendKeys(newText);
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        //assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement textArea = driver.findElement(By.id("numb"));
        String numberTooSmall = "48";
        textArea.sendKeys(numberTooSmall);
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement textArea = driver.findElement(By.id("numb"));
        String numberTooBig = "101";
        textArea.sendKeys(numberTooBig);
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement textArea = driver.findElement(By.id("numb"));
        String numberHasSquareRootWithoutRemainder = "81";
        String numberSquareRootWithoutRemainder = "9.00";
        textArea.sendKeys(numberHasSquareRootWithoutRemainder);
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + numberHasSquareRootWithoutRemainder + " is " + numberSquareRootWithoutRemainder, alert.getText());
        Thread.sleep(2000);
    }

    @Test
    public void correctSquareRootWithRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement textArea = driver.findElement(By.id("numb"));
        String numberHasSquareRootWithRemainder = "82";
        String numberSquareRootWithRemainder = "9.06";
        textArea.sendKeys(numberHasSquareRootWithRemainder);
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + numberHasSquareRootWithRemainder + " is " + numberSquareRootWithRemainder, alert.getText());
        Thread.sleep(2000);
    }
}
