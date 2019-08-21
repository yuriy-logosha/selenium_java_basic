package selenium.tasks;

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
//         TODO 2.1:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters

        WebElement textInputName = driver.findElement(By.id("fb_name"));
        assertEquals(textInputName.getText(), ""); // checking that Name field is empty

        WebElement textInputAge = driver.findElement(By.id("fb_age"));
        assertEquals(textInputAge.getText(), ""); // checking that Age field is empty

        WebElement textInputComment = driver.findElement(By.name("comment"));
        assertEquals(textInputComment.getText(), ""); // checking that Comment field is empty

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checking that checkboxes are NOT selected
        }

        WebElement radioGenderMale = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(radioGenderMale.isSelected());
        WebElement radioGenderFemale = driver.findElement(By.cssSelector(".w3-radio[value='female'][type='radio'"));
        assertFalse(radioGenderFemale.isSelected());
        WebElement radioGenderInitial = driver.findElement(By.cssSelector(".w3-radio[value=''][type='radio'"));
        assertTrue(radioGenderInitial.isSelected()); // checking that "Don't know" is selected in "Gender"

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText()); // checking that "Choose your option" is selected in "How do you like us?"

        WebElement sendButtonBackground = driver.findElement(By.cssSelector("button[class='w3-btn-block w3-blue w3-section']"));
        assertEquals("rgba(33, 150, 243, 1)", sendButtonBackground.getCssValue("background-color"));
        WebElement sendButtonTextColor = driver.findElement(By.cssSelector("button[class='w3-btn-block w3-blue w3-section']"));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonTextColor.getCssValue("color"));

    } // End of Task 2.1

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO 2.2:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)

        WebElement sendButton = driver.findElement(By.cssSelector("button[class='w3-btn-block w3-blue w3-section']"));
        Thread.sleep(2000); // for visual check
        sendButton.click();
        Thread.sleep(2000); // for visual check

        WebElement textOutputName = driver.findElement(By.id("name"));
        assertEquals(textOutputName.getText(), "");
        WebElement textOutputAge = driver.findElement(By.id("age"));
        assertEquals(textOutputAge.getText(), "");
        WebElement textOutputLanguage = driver.findElement(By.id("language"));
        assertEquals(textOutputLanguage.getText(), "");
        WebElement textOutputGender = driver.findElement(By.id("gender"));
        assertEquals(textOutputGender.getText(), "null");
        WebElement textOutputYourOptionOfUs = driver.findElement(By.id("option"));
        assertEquals(textOutputYourOptionOfUs.getText(), "null");
        WebElement textOutputComment = driver.findElement(By.id("comment"));
        assertEquals(textOutputComment.getText(), "");

        WebElement yesButtonBackground = driver.findElement(By.cssSelector("button[class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", yesButtonBackground.getCssValue("background-color"));
        WebElement yesButtonTextColor = driver.findElement(By.cssSelector("button[class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", yesButtonTextColor.getCssValue("color"));
        WebElement noButtonBackground = driver.findElement(By.cssSelector("button[class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", noButtonBackground.getCssValue("background-color"));
        WebElement noButtonTextColor = driver.findElement(By.cssSelector("button[class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", noButtonTextColor.getCssValue("color"));

    } // End of Task 2.2




    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO 2.3:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        String newName = "Janis Test Berzins";
        String newAge = "36";
        String newComment = "This is a test comment";

        WebElement textInputName = driver.findElement(By.id("fb_name"));
        textInputName.sendKeys(newName);

        WebElement textInputAge = driver.findElement(By.id("fb_age"));
        textInputAge.sendKeys(newAge);

        WebElement checkbox1 = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        assertFalse(checkbox1.isSelected());
        checkbox1.click();
        assertTrue(checkbox1.isSelected());

        WebElement radioMale = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(radioMale.isSelected());
        radioMale.click();
        assertTrue(radioMale.isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        Thread.sleep(2000);
        dropdown.selectByValue("Good");
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        Thread.sleep(2000);

        WebElement textInputComment = driver.findElement(By.name("comment"));
        textInputComment.sendKeys(newComment);

        WebElement sendButton = driver.findElement(By.cssSelector("button[class='w3-btn-block w3-blue w3-section']"));
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(2000);

        WebElement textOutputName = driver.findElement(By.id("name"));
        assertEquals(textOutputName.getText(), "Janis Test Berzins");
        WebElement textOutputAge = driver.findElement(By.id("age"));
        assertEquals(textOutputAge.getText(), "36");
        WebElement textOutputLanguage = driver.findElement(By.id("language"));
        assertEquals(textOutputLanguage.getText(), "English");
        WebElement textOutputGender = driver.findElement(By.id("gender"));
        assertEquals(textOutputGender.getText(), "male");
        WebElement textOutputYourOptionOfUs = driver.findElement(By.id("option"));
        assertEquals(textOutputYourOptionOfUs.getText(), "Good");
        WebElement textOutputComment = driver.findElement(By.id("comment"));
        assertEquals(textOutputComment.getText(), "This is a test comment");

        WebElement yesButtonBackground = driver.findElement(By.cssSelector("button[class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", yesButtonBackground.getCssValue("background-color"));
        WebElement yesButtonTextColor = driver.findElement(By.cssSelector("button[class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", yesButtonTextColor.getCssValue("color"));
        WebElement noButtonBackground = driver.findElement(By.cssSelector("button[class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", noButtonBackground.getCssValue("background-color"));
        WebElement noButtonTextColor = driver.findElement(By.cssSelector("button[class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", noButtonTextColor.getCssValue("color"));

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
