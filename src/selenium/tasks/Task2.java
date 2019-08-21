package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.xml.soap.Text;
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
        assertTrue(driver.findElement(By.id("fb_name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("fb_age")).getText().isEmpty());
        assertFalse(driver.findElement(By.xpath("//input[@name='language' and @value='English']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@name='language' and @value='French']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@name='language' and @value='Spanish']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@name='language' and @value='Chinese']")).isSelected());


        List<WebElement> allElementsWithClass = driver.findElements(By.className("w3-radio"));
        WebElement thirdButton = allElementsWithClass.get(2);
        assertTrue(thirdButton.isSelected());

        Select select = new Select(driver.findElement(By.id("like_us")));
       select.getFirstSelectedOption();

        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));


   }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.className("w3-btn-block")).click();
        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());
        WebElement yourGenre = driver.findElement(By.id("gender"));
        assertEquals("null",yourGenre.getText());
        WebElement yourOption = driver.findElement(By.id("option"));
        assertEquals("null", yourOption.getText());

        WebElement buttonYes = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(255, 255, 255, 1)", buttonYes.getCssValue("color")) ;
        assertEquals("rgba(76, 175, 80, 1)", buttonYes.getCssValue("background-color"));

        WebElement buttonNo = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        assertEquals("rgba(255, 255, 255, 1)", buttonNo.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", buttonNo.getCssValue("background-color"));


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement name = driver.findElement(By.id("fb_name")).sendKeys();
        String newName = "Irina";
        name.sendKeys(newName);
        assertEquals(
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
