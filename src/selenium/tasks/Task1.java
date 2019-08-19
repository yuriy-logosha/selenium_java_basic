package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
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
    public void errorOnEmpty() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen

        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys(" ");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("You haven't entered anything", errorMsg.getText());
    }
    @Test
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen

         WebElement inputText =  driver.findElement(By.id("numb"));
         inputText.sendKeys("q");
         WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
         submitBtn.click();
         WebElement errorMsg = driver.findElement(By.id("ch1_error"));
         assertEquals("Please enter a number", errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys("1");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys("101");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", errorMsg.getText());
    }
    @Test
    public void errorOnNumber666() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys("666");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        //Thread.sleep(1000);

        assertTrue(errorMsg.isDisplayed()); ;
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys("64");
        //Thread.sleep(1000);
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 64 is 8.00", alert.getText());
        alert.accept();
       // Thread.sleep(1000);
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        //Thread.sleep(1000);
        assertFalse(errorMsg.isDisplayed());
        //Thread.sleep(1000);

        ;



    }

    @Test
    public void correctSquareRootWithRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement inputText =  driver.findElement(By.id("numb"));
        inputText.sendKeys("51");
        Thread.sleep(1000);
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-orange w3-margin']"));
        submitBtn.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 51 is 7.14", alert.getText());
        alert.accept();
        Thread.sleep(1000);
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        Thread.sleep(1000);
        assertFalse(errorMsg.isDisplayed());
        Thread.sleep(1000);
    }
}
