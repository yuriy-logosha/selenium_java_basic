package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.AgeSamplePage;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class Task2 {

    static AgeSamplePage agePage;
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

        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());
        assertEquals("", driver.findElement(By.name("comment")).getText());

        List<WebElement> checkBoxes = driver.findElements(By.name("language"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
//         "Don't know" is selected in "Genre"

        List<WebElement> radioButtons = driver.findElements(By.className("w3-radio"));
        assertTrue(radioButtons.get(2).isSelected());

//         "Choose your option" in "How do you like us?"

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters

        WebElement sendButton= driver.findElement(By.className("w3-btn-block"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {

        //         click "Send" without entering any data

        driver.findElement(By.className("w3-btn-block")).click();

//         check fields are empty or null

        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)

        WebElement sendButtonYes= driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", sendButtonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonYes.getCssValue("color"));

        WebElement sendButtonNo= driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", sendButtonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonYes.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"

        driver.findElement(By.id("fb_name")).sendKeys("Marusja");
        driver.findElement(By.id("fb_age")).sendKeys("26");
        List<WebElement> checkBox = driver.findElements(By.className("w3-check"));
        checkBox.get(2).click();
        List<WebElement> radioButtons = driver.findElements(By.className("w3-radio"));
        radioButtons.get(1).click();
        Select dropdown = new Select (driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(2);
        driver.findElement(By.name("comment")).sendKeys("Hello!!!");
        driver.findElement(By.className("w3-btn-block")).click();

//        check that all fields are correct

        assertEquals("Marusja", driver.findElement(By.id("name")).getText());
        assertEquals("26", driver.findElement(By.id("age")).getText());
        assertEquals("Spanish", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("Hello!!!", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)

        WebElement sendButtonYes= driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", sendButtonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonYes.getCssValue("color"));

        WebElement sendButtonNo= driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", sendButtonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonYes.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("Marusja");

//         click "Send"
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"
//      WebElement text = driver.findElement(By.id("message"));
//      assertTrue(text.isDisplayed());
//        System.out.println("");

        assertEquals("Thank you, Marusja, for your feedback!", driver.findElement(By.xpath("//*[@id=\"message\"]")).getText());

//         color of text is white with green on the background
        WebElement panel= driver.findElement(By.className("w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", panel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", panel.getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-btn-block")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.xpath("//*[@id=\"message\"]")).getText());

//         color of text is white with green on the background
        WebElement panel= driver.findElement(By.className("w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", panel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", panel.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Marusja");
        driver.findElement(By.id("fb_age")).sendKeys("26");
        List<WebElement> checkBox = driver.findElements(By.className("w3-check"));
        checkBox.get(2).click();
        List<WebElement> radioButtons = driver.findElements(By.className("w3-radio"));
        radioButtons.get(1).click();
        Select dropdown = new Select (driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(2);
        driver.findElement(By.name("comment")).sendKeys("Hello!!!");
        driver.findElement(By.className("w3-btn-block")).click();

//         click "No"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).click();

//         check fields are filled correctly

        assertEquals("Marusja", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("26", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals("Spanish", driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).getAttribute("value"));
        assertEquals("female", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).getAttribute("value"));

//        List<WebElement> radioButton = driver.findElements(By.className("w3-radio"));

//
////         "Choose your option" in "How do you like us?"
//
//        Select dropdown = new Select(driver.findElement(By.id("like_us")));
//        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getAttribute("value"));
        assertEquals("Hello!!!", driver.findElement(By.className("w3-input")).getAttribute("value"));

    }
}
