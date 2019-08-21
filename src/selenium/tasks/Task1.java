package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.*;
import static org.junit.Assert.*;

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
        driver.findElement(By.id("numb")).sendKeys("bla bla bla");
        //Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        //Thread.sleep(1000);
        System.out.println("The following error was shown:\n" + driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("33");
        //Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        //Thread.sleep(1000);
        System.out.println("The following error was shown:\n" + driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("111");
        //Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        //Thread.sleep(1000);
        System.out.println("The following error was shown:\n" + driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys("64");
        //Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
        Alert alert = driver.switchTo().alert();
        //Thread.sleep(1000);
        //Alert returns a sentence withe the result being the last element.
        //Splitting the sentence into words and checking only the last element.
        String[] toWords = alert.getText().split("\\s");
        assertTrue(toWords[toWords.length - 1].contains("8"));
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void correctSquareRootWithRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys("66");
        driver.findElement(By.tagName("button")).click();
        //Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        //Thread.sleep(1000);
        //Alert returns a sentence withe the result being the last element.
        //Splitting the sentence into words and checking only the last element.
        String[] toWords = alert.getText().split("\\s");
        assertTrue(toWords[toWords.length - 1].contains("8.12"));
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }
}
