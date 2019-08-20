package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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
//        enter a text instead of a number, check that correct error is seen
driver.findElement(By.id("numb")).sendKeys("abc");
//Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
//Thread.sleep(1000);
        driver.findElement(By.id("ch1_error"));
        //Thread.sleep(1000);
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
       String s = driver.findElement(By.id("ch1_error")).getText();

       System.out.println(s);
       assertEquals("Please enter a number",s);

    }

    @Test()
    public void errorOnNumberTooSmall() throws InterruptedException {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        //BUG 49
        driver.findElement(By.id("numb")).sendKeys("49");
Thread.sleep(1000);
       // driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(1000);
        //driver.findElement(By.id("ch1_error"));
        //Thread.sleep(1000);
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        String s = driver.findElement(By.id("ch1_error")).getText();

        System.out.println(s);
        assertEquals("Number is too small",s);
       }


    @Test
    public void errorOnNumberTooBig() throws InterruptedException {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("101");
        Thread.sleep(1000);
        // driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(1000);
        //driver.findElement(By.id("ch1_error"));
        //Thread.sleep(1000);
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        String s = driver.findElement(By.id("ch1_error")).getText();

        System.out.println(s);
        assertEquals("Number is too big",s);
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
   driver.findElement(By.id("numb")).sendKeys("81");
   driver.findElement(By.tagName("button")).click();
   Alert alert = driver.switchTo().alert();
   assertTrue(alert.getText().equals("9"));
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys("50");
        driver.findElement(By.tagName("button")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().equals("7.07"));
    }
}
