package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

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

        WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1

        String newTextOne = "text"; //enter text "text" in area 1

        textArea.sendKeys(newTextOne); // sending keys "text"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());

        //assert actual end expected error messages are same

    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen

        WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1

        String newTextOne = "5"; //enter "5" in area 1

        textArea.sendKeys(newTextOne); // sending keys "5"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());

        //assert actual end expected error messages are same

    }

    @Test
    public void errorOnNumberTooBig()  throws Exception {

//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1

        String newTextOne = "105"; //enter "105" in area 1

        textArea.sendKeys(newTextOne); // sending keys "105"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());

        textArea.clear();

        //assert actual end expected error messages are same

        //Test 2 for 666 bug:


        String newTextTwo = "666"; //enter "666" in area 1

        textArea.sendKeys(newTextTwo); // sending keys "666"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        assertEquals("", driver.findElement(By.id("ch1_error")).getText());

        //assert that no error message is shown

    }

    @Test
    public void correctSquareRootWithoutRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly

        WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1

        String newTextOne = "64"; //enter "64" in area 1

        textArea.sendKeys(newTextOne); // sending keys "64"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        //assertEquals("void", driver.findElement(By.id("ch1_error")).getText());

        //assert there are no error messages

        Alert alert = driver.switchTo().alert();
        alert.getText();
        assertEquals("Square root of 64 is 8.00", alert.getText()); //check that square root is calculated correctly

        alert.accept(); //click ok button on popup

        assertEquals("", driver.findElement(By.id("ch1_error")).getText());

       //check that correct no error is seen


    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which does have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1

        String newTextOne = "65"; //enter "65" in area 1

        textArea.sendKeys(newTextOne); // sending keys "65"

        driver.findElement(By.className("w3-btn")).click(); //click on submit button

        //assertEquals("void", driver.findElement(By.id("ch1_error")).getText());

        //assert there are no error messages

        Alert alert = driver.switchTo().alert();
        alert.getText();
        assertEquals("Square root of 65 is 8.06", alert.getText()); //check that square root is calculated correctly


        alert.accept(); //click ok button on popup

        assertEquals("", driver.findElement(By.id("ch1_error")).getText());

        //check that correct no error is seen
    }
}

