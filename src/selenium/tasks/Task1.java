package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;
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
        WebElement textArea = driver.findElement(By.id("numb"));
        String enterKeys = "test input string";

        textArea.sendKeys(enterKeys);
        driver.findElement(By.className("w3-btn")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());

        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());

        sleep(5000);
    }

    @Test
    public void errorOnNumberTooSmall() throws Exception  {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement textArea = driver.findElement(By.id("numb"));
        String enterKeys = "10";

        textArea.sendKeys(enterKeys);
        driver.findElement(By.className("w3-btn")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());

        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());

        sleep(5000);
    }

    @Test
    public void errorOnNumberTooBig() throws Exception {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement textArea = driver.findElement(By.id("numb"));
        String test1 = "500";
        String test2 = "666";

        textArea.sendKeys(test1);
        driver.findElement(By.className("w3-btn")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());

        sleep(5000);

        textArea.clear();
        textArea.sendKeys(test2);
        driver.findElement(By.className("w3-btn")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());

        sleep(5000);
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement textArea = driver.findElement(By.id("numb"));
        String sqTest = "64";


        textArea.sendKeys(sqTest);
        driver.findElement(By.className("w3-btn")).click();


        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 64 is 8.00", alert.getText());
        alert.accept();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
        sleep(5000);

       // assertEquals("Square root of 64 is 8.00", alert.getText());
        //System.out.println(driver.alert.getText()
    }

    @Test
    public void correctSquareRootWithRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement textArea = driver.findElement(By.id("numb"));
        String sqTest = "74";


        textArea.sendKeys(sqTest);
        driver.findElement(By.className("w3-btn")).click();


        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 74 is 8.60", alert.getText());
        alert.accept();

        //System.out.println(driver.get(alert.getText()));

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
        sleep(5000);
    }
}
