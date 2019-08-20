package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

import static org.junit.Assert.assertEquals;

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


        driver.findElement(By.id("numb")).sendKeys("Text");  // find field with id=numb, print "Text"
        driver.findElement(By.className("w3-btn")).click();     // find button, click
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());   // error message
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(10000);
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("47");  // find field with id=numb, print "47"
        driver.findElement(By.className("w3-btn")).click();    // find button, click
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());   // error message
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(10000);
    }

    @Test
    public void errorOnNumberTooBig() throws InterruptedException {
        //        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("700");  // find field with id=numb, print "700"
        driver.findElement(By.className("w3-btn")).click();  // find button, click
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());  // error message
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(10000);
//        BUG: if I enter number 666 no errors where seen
        driver.findElement(By.id("numb")).clear();   // find and clear field
        driver.findElement(By.id("numb")).sendKeys("666");  // find and print 666
        driver.findElement(By.className("w3-btn")).click();   // find button, click
        assertEquals("",driver.findElement(By.id("ch1_error")).getText());  // check no error message
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        Thread.sleep(10000);
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
        driver.findElement(By.id("numb")).sendKeys("81");   // find field with id=numb, print "81"
        driver.findElement(By.className("w3-btn")).click();   // find button, click
        Alert alert = driver.switchTo().alert();
        alert.getText();
        assertEquals("Square root of 81 is 9.00", alert.getText());  // alert window with answer

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
    }

    @Test
    public void correctSquareRootWithRemainder()throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        driver.findElement(By.id("numb")).sendKeys("60");  // find field with id=numb, print "60"
        driver.findElement(By.className("w3-btn")).click();      // find button, click
        Alert alert = driver.switchTo().alert();
        alert.getText();
        assertEquals("Square root of 60 is 7.75",alert.getText());  // alert window with correct answer
        alert.accept();
        assertEquals("",driver.findElement(By.id("ch1_error")).getText());  // check no error message
    }
}
