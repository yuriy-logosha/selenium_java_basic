package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        //Name and Age text inputs
        assertTrue(driver.findElement(By.xpath("//*[@id='fb_name']")).getText().equals(""));
        assertTrue(driver.findElement(By.xpath("//*[@id='fb_age']")).getText().equals(""));
        //Language Checkboxes
        List<WebElement> cBoxes = new ArrayList<>();
        cBoxes = driver.findElements(By.xpath("//*[contains(@class, 'w3-check')]"));
        for(WebElement we : cBoxes) {
            assertFalse(we.isSelected());
        }

        //Gender Radio buttons
        assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'w3-radio')][3]")).isSelected());

        //How do you like us Select
        Select sel = new Select(driver.findElement(By.xpath("//*[@id='like_us']")));
        assertEquals("Choose your option", sel.getFirstSelectedOption().getText());

        //Comments textarea
        assertTrue(driver.findElement(By.xpath("//*[contains(@name, 'comment')]")).getText().equals(""));

        //Check if the button is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.xpath("//button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        //Clicking Send
        driver.findElement(By.xpath("//button")).click();

        //The values are contained in Span elements
        List<WebElement> spans = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@class, 'description')]//span")));
        for (WebElement we : spans) {
            assertTrue(we.getText().equals("") || we.getText().equals("null"));
        }

        //The buttons are under the same div
        List<WebElement> buttons = new ArrayList(driver.findElements(By.xpath("//*[contains(@class, 'w3-btn-group')]//button")));
        for (WebElement we : buttons) {
            if (we.getText().equals("Yes")) {
                assertTrue("rgba(76, 175, 80, 1)".equals(we.getCssValue("background-color")));
                assertTrue("rgba(255, 255, 255, 1)".equals(we.getCssValue("color")));
            } else if (we.getText().equals("No")) {
                assertTrue("rgba(244, 67, 54, 1)".equals(we.getCssValue("background-color")));
                assertTrue("rgba(255, 255, 255, 1)".equals(we.getCssValue("color")));
            }
        }

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        //Setting up values
        String name = "TempName";
        String age = "33";
        String language = "French"; //English/French/Spanish/Chinese

        driver.findElement(By.xpath("//*[@id='fb_name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='fb_age']")).sendKeys(age);
        driver.findElement(By.xpath("//*[contains(text(), '" + language + "')]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'w3-radio')][1]")).click();
        new Select(driver.findElement(By.xpath("//*[@id='like_us']"))).selectByVisibleText("Good");

        Thread.sleep(3000);
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}
