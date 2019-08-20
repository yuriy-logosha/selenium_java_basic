package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertTrue;
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
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("fg ");

        driver.findElement(By.tagName("button")).click();

        driver.findElement(By.id("ch1_error"));
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys(("48"));
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.id("ch1_error"));
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void errorOnNumberTooBig()  throws Exception {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys(("666"));
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.id("ch1_error"));
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys(("64"));
        driver.findElement(By.tagName("button")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("8.0"));
    }

    @Test
    public void correctSquareRootWithRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys(("65"));
        driver.findElement(By.tagName("button")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("8.06"));
    }
}
