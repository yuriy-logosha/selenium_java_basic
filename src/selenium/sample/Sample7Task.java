package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCheckBox() throws Exception {
//        check that none of the checkboxes are ticked
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }
//        tick  "Option 2"
        checkboxes.get(1).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertTrue(checkboxes.get(1).isSelected());
        assertFalse(checkboxes.get(0).isSelected());
        assertFalse(checkboxes.get(2).isSelected());
//        tick  "Option 3"
        checkboxes.get(2).click();
//        click result
        driver.findElement(By.cssSelector("#result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String resultText = driver.findElement(By.cssSelector("#result_checkbox")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", resultText);
    }


    @Test
    public void selectRadioButton() throws Exception {
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }
//        select  "Option 3"
        radioButtons.get(2).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertTrue(radioButtons.get(2).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(0).isSelected());
//        select  "Option 1"
        radioButtons.get(0).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());
//        click result
        driver.findElement(By.cssSelector("#result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        String radioResult = driver.findElement(By.cssSelector("#result_radio")).getText();
        assertEquals("You selected option: Option 1", radioResult);
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select select = new Select(driver.findElement(By.cssSelector("select#vfb-12")));
        select.selectByIndex(3);
//        check that selected option is "Option 3"
        assertEquals("Option 3", select.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        select.selectByIndex(2);
//        check that selected option is "Option 2"
        assertEquals("Option 2", select.getFirstSelectedOption().getText());
//        click result
        driver.findElement(By.cssSelector("#result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        String resultText = driver.findElement(By.cssSelector("#result_select")).getText();
        assertEquals("You selected option: Option 2", resultText);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        driver.findElement(By.xpath("//input[@id='vfb-8']")).click();
        Thread.sleep(1000);
//        enter date '4 of July 2007' using calendar widget
        for(int i = 0; i < 141; i++) {
            driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-prev')]")).click();
            Thread.sleep(60);
        }
        driver.findElement(By.xpath("//a[text()='4']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@id='result_button_date']")).click();
//        check that correct date is added
        String result = driver.findElement(By.xpath("//p[@id='result_date']")).getText();
        assertEquals("You entered date: 07/04/2007", result);
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        WebElement field = driver.findElement(By.cssSelector("#vfb-8"));
        field.clear();
//        enter date '2 of May 1959' using text
        field.sendKeys("05/02/1959");
        field.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#result_button_date")).click();
//        check that correct date is added
        String resultText = driver.findElement(By.cssSelector("#result_date")).getText();
        assertEquals("You entered date: 05/02/1959", resultText);
    }
}
