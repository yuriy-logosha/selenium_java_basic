package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        String expectedError = "Please enter a number";
        String enter = "Hi";
        WebElement textArea = driver.findElement(By.id("numb"));
        WebElement errorArea = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        textArea.sendKeys(enter);
        button.click();

        assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        String expectedError = "Number is too small";
        String enter = "15";

        WebElement textArea = driver.findElement(By.id("numb"));
        WebElement errorArea = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        textArea.sendKeys((enter));
        button.click();

        assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String expectedError = "Number is too big";
        String enter = "115";

        WebElement textArea = driver.findElement(By.id("numb"));
        WebElement errorArea = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        textArea.sendKeys((enter));
        button.click();

        assertTrue(errorArea.getText().equals(expectedError));
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        String enter = "64";
        // Square root of 64 is 8.00
        String expectedError = "Square root of 64 is 8.00";

        WebElement textArea = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        textArea.sendKeys((enter));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        assertTrue(alertText.equals(expectedError));
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        String enter = "60";
        // Square root of 60 is 7.75
        String expectedError = "Square root of 60 is 7.75";

        WebElement textArea = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        textArea.sendKeys((enter));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        assertTrue(alertText.equals(expectedError));
    }
}
