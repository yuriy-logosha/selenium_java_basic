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
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement textBox = driver.findElement(By.id("numb"));
        String nextTextOne = "Text";
        textBox.sendKeys(nextTextOne);
        WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
        buttonSubmit.click();
        WebElement errorInfo = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", errorInfo.getText());
    }

    @Test
    public void errorOnNumberTooSmall()  {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
            WebElement textBox = driver.findElement(By.id("numb"));
            String nextNumOne = "35";
            textBox.sendKeys(nextNumOne);
            WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
            buttonSubmit.click();
            WebElement errorInfo = driver.findElement(By.id("ch1_error"));
            assertEquals("Number is too small", errorInfo.getText());

        }

    @Test
    public void errorOnNumberTooBig()   {


//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
                WebElement textBox= driver.findElement(By.id("numb"));
                String nextNumFive = "365";
                textBox.sendKeys(nextNumFive);
                WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
                buttonSubmit .click();
                WebElement errorInfo = driver.findElement(By.id("ch1_error"));
                assertEquals("Number is too big", errorInfo.getText());

                textBox.clear();


                String nextNumSix = "666";
                textBox.sendKeys(nextNumSix);
                buttonSubmit = driver.findElement(By.className("w3-btn"));
                buttonSubmit .click();
                errorInfo = driver.findElement(By.id("ch1_error"));
                assertEquals("", errorInfo.getText());




    }

    @Test
    public void correctSquareRootWithoutRemainder() throws Exception  {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
                WebElement textBox= driver.findElement(By.id("numb"));
                String nextNumSeven = "81";
                textBox.sendKeys(nextNumSeven);
                WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
                buttonSubmit .click();
                Alert alert = driver.switchTo().alert();
                alert.getText();
                assertEquals("Square root of 81 is 9.00", alert.getText());
                buttonSubmit .click();
                WebElement errorInfo = driver.findElement(By.id("ch1_error"));
                assertEquals("", errorInfo.getText());


    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
                WebElement textBox= driver.findElement(By.id("numb"));
                String nextNumEigth = "56";
                textBox.sendKeys(nextNumEigth);
                WebElement buttonSubmit = driver.findElement(By.className("w3-btn"));
                buttonSubmit .click();

                Alert alert = driver.switchTo().alert();
                alert.getText();
                assertEquals("Square root of 56 is 7.48", alert.getText());

                buttonSubmit .click();
                WebElement errorInfo = driver.findElement(By.id("ch1_error"));
                assertEquals("", errorInfo.getText());



    }
}
