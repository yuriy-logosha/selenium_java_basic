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
import static org.junit.Assert.assertFalse;

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
    public void errorOnText() throws Exception{
        WebElement inputText = driver.findElement(By.id("numb"));
        inputText.sendKeys("bla-bla");
        WebElement clickButton = driver.findElement(By.cssSelector("body > div.w3-row > div > div > div.w3-container.w3-card-4 > button"));
        clickButton.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", errorMsg.getText());
//        TODO
//        enter a text instead of a number, check that correct error is seen
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement inputText = driver.findElement(By.id("numb"));
        inputText.sendKeys("4");
        WebElement clickButton = driver.findElement(By.cssSelector("body > div.w3-row > div > div > div.w3-container.w3-card-4 > button"));
        clickButton.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputText = driver.findElement(By.id("numb"));
        inputText.sendKeys("666");
        WebElement clickButton = driver.findElement(By.cssSelector("body > div.w3-row > div > div > div.w3-container.w3-card-4 > button"));
        clickButton.click();
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", errorMsg.getText());

    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement inputText = driver.findElement(By.id("numb"));

        WebElement clickButton = driver.findElement(By.cssSelector("body > div.w3-row > div > div > div.w3-container.w3-card-4 > button"));
        String Keys = "81";
        String Success = "Square root of 81 is 9.00";

        inputText.sendKeys(Keys);
        clickButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(Success, alert.getText());



    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement inputText = driver.findElement(By.id("numb"));

        WebElement clickButton = driver.findElement(By.cssSelector("body > div.w3-row > div > div > div.w3-container.w3-card-4 > button"));
        String Keys = "80";
        String Success = "Square root of 80 is 8.94";

        inputText.sendKeys(Keys);
        clickButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(Success, alert.getText());
    }
}
