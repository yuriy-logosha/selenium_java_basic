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
//         TODO:
//         check that all field are empty and no tick are clicked

        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"fb_name\"]"));
        assertEquals(textInput.getText(), "",textInput.getText());

        WebElement ageInput = driver.findElement(By.xpath("//*[@id=\"fb_age\"]"));
        assertEquals(ageInput.getText(), "", ageInput.getText());

        List<WebElement> checkBoxes = driver.findElements(By.className("w3-check"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement radioMale = driver.findElement(By.xpath("//div[4]//input[1]"));
        assertFalse(radioMale.isSelected()); // radio are NOT selected

        WebElement radioFemale = driver.findElement(By.xpath("//div[4]//input[2]"));
        assertFalse(radioFemale.isSelected()); // radio are NOT selected

        WebElement radioDont = driver.findElement(By.xpath("//div[4]//input[3]"));
        assertTrue(radioDont.isSelected()); //"Don't know" is selected in "Genre"

        WebElement textComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        assertEquals(textComment.getText(), "",textComment.getText());

//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='like_us']")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        WebElement h1 = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        assertEquals("block", h1.getCssValue("display"));
        assertEquals("rgba(0, 0, 0, 1)", h1.getCssValue("color"));
        assertEquals("64px", h1.getCssValue("font-size"));
        assertEquals("rgba(0, 0, 0, 0)", h1.getCssValue("background-color"));

        WebElement div_h1 = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        assertEquals("rgba(241, 241, 241, 1)", div_h1.getCssValue("background-color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
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
