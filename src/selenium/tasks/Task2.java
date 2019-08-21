package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        //         check that all field are empty and no tick are clicked
        assertTrue(driver.findElement(By.cssSelector("#fb_name")).getText().equals(""));
        assertTrue(driver.findElement(By.cssSelector("#fb_age")).getText().equals(""));
        assertTrue(driver.findElement(By.cssSelector("textarea")).getText().equals(""));

        //         "Don't know" is selected in "Genre"
        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        for (WebElement radioButton : radioButtons)
            assertTrue(radioButton.isSelected());

        //         "Choose your option" in "How do you like us?"
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        //         check that the button send is blue with white letters
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.cssSelector(".w3-btn-block")).getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.cssSelector(".w3-btn-block")).getCssValue("background-color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         click "Send" without entering any data
        driver.findElement(By.tagName("button")).click();

//         check fields are empty or null
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"name\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"age\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"language\"]")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id=\"gender\"]")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id=\"option\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"comment\"]")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         fill the whole form, click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys("Johnny Bravo");
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys("27");
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        dropdown.selectByIndex(1);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("One Man Army");
        driver.findElement(By.tagName("button")).click();

//         check fields are filled correctly
        assertEquals("Johnny Bravo", driver.findElement(By.xpath("//*[@id=\"name\"]")).getText());
        assertEquals("27", driver.findElement(By.xpath("//*[@id=\"age\"]")).getText());
        assertEquals("English", driver.findElement(By.xpath("//*[@id=\"language\"]")).getText());
        assertEquals("male", driver.findElement(By.xpath("//*[@id=\"gender\"]")).getText());
        assertEquals("Good", driver.findElement(By.xpath("//*[@id=\"option\"]")).getText());
        assertEquals("One Man Army", driver.findElement(By.xpath("//*[@id=\"comment\"]")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         enter only name
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys("Johnny Bravo");
//         click "Send"
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Johnny Bravo, for your feedback!", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         click "Send" (without entering anything
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         fill the whole form
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys("Johnny Bravo");
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys("27");
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        dropdown.selectByIndex(1);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("One Man Army");

//         click "Send"
        driver.findElement(By.tagName("button")).click();

//         click "No"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();

//         check fields are filled correctly
        assertEquals("Johnny Bravo", driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).getAttribute("value"));
        assertEquals("27", driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).getAttribute("value"));

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[@id=\"lang_check\"]/input[1]"));
        for (WebElement checkBox : checkBoxes)
            assertTrue(checkBox.isSelected()); // checkbox is selected
        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]"));
        for (WebElement radioButton : radioButtons)
            assertTrue(radioButton.isSelected());// radiobox is selected
        Select dropdown2 = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        dropdown2.selectByIndex(1);
        assertEquals("Good", dropdown2.getFirstSelectedOption().getText());
        assertEquals("One Man Army", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));
    }
}
