package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static junit.framework.TestCase.*;

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
        driver.findElement(By.id("fb_name"));
        assertTrue(driver.findElement(By.id("fb_name")).getText().equals(""));

        driver.findElement(By.id("fb_age"));
        assertTrue(driver.findElement(By.id("fb_age")).getText().equals(""));
//         "Don't know" is selected in "Genre"
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
//         "Choose your option" in "How do you like us?"
        Select like = new Select(driver.findElement(By.id("like_us")));
        // like.getAttribute("value");
        like.selectByIndex(1);
        //like.getFirstSelectedOption();
        assertEquals("Good", like.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        //  driver.findElement(By.className("w3-btn-block w3-blue w3-section"));
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         check fields are empty or null
        WebElement name = driver.findElement(By.id("name"));
        assertTrue(name.getText().equals("null") || name.getText().equals(""));

        WebElement age = driver.findElement(By.id("age"));
        assertTrue(age.getText().equals("null") || age.getText().equals(""));

        WebElement language = driver.findElement(By.id("language"));
        assertTrue(language.getText().equals("null") || language.getText().equals(""));

        WebElement gender = driver.findElement(By.id("gender"));
        assertTrue(gender.getText().equals("null") || gender.getText().equals(""));

        WebElement option = driver.findElement(By.id("option"));
        assertTrue(option.getText().equals("null") || option.getText().equals(""));

        WebElement comment = driver.findElement(By.id("comment"));
        assertTrue(comment.getText().equals("null") || comment.getText().equals(""));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color"));
    }
    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("JanisBerzins");
        driver.findElement(By.id("fb_age")).sendKeys("20");
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[5]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("no comments");
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

//         check fields are filled correctly
        assertEquals("JanisBerzins", driver.findElement(By.id("name")).getText());
        assertEquals("20", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
//         check button colors
//         (green with white letter and red with white letters)
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
