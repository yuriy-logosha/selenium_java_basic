package selenium.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


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
//       1.1  check that all field are empty and no tick are clicked
//       1.2  "Don't know" is selected in "Genre"
//       1.3 "Choose your option" in "How do you like us?"
//       1.4  check that the button send is blue with white letters

        //1.1
        WebElement nameArea = driver.findElement(By.id("fb_name"));
        assertEquals("", nameArea.getText());
        WebElement ageArea = driver.findElement(By.id("fb_age"));
        assertEquals("", ageArea.getText());
        WebElement language1 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='English']"));
        assertFalse(language1.isSelected());
        WebElement language2 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='French']"));
        assertFalse(language2.isSelected());
        WebElement language3 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='Spanish']"));
        assertFalse(language3.isSelected());
        WebElement language4 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='Chinese']"));
        assertFalse(language4.isSelected());

        //1.2
        WebElement areaMaleRadio = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='male']"));
        assertFalse(areaMaleRadio.isSelected());
        WebElement areaFemaleRadio = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='female']"));
        assertFalse(areaFemaleRadio.isSelected());
        WebElement dontknowAreaRadio = driver.findElement(By.cssSelector("input[type='radio'][disabled]"));
        assertTrue(dontknowAreaRadio.isSelected());


        //1.3
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        //1.4
        WebElement sendButton = driver.findElement(By.cssSelector("button.w3-btn-block.w3-blue.w3-section"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//        2.1 click "Send" without entering any data
//        2.2 check fields are empty or null
//        2.3 check button colors
//         (green with white letter and red with white letters)

        //2.1
        WebElement sendButton = driver.findElement(By.cssSelector("button.w3-btn-block.w3-blue.w3-section"));
        sendButton.click();

        //2.2
        WebElement yourNameArea = driver.findElement(By.id("name"));
        assertTrue((yourNameArea.getText().equals("")) || yourNameArea.getText().equals("null"));
        WebElement yourAgeArea= driver.findElement(By.id("age"));
        assertTrue((yourAgeArea.getText().equals("")) || yourAgeArea.getText().equals("null"));
        WebElement yourLanguageArea = driver.findElement(By.id("language"));
        assertTrue((yourLanguageArea.getText().equals("")) || yourLanguageArea.getText().equals("null"));
        WebElement yourGenderArea = driver.findElement(By.id("gender"));
        assertTrue((yourGenderArea.getText().equals("")) || yourGenderArea.getText().equals("null"));
        WebElement yourOptionArea = driver.findElement(By.id("option"));
        assertTrue((yourOptionArea.getText().equals("")) || yourOptionArea.getText().equals("null"));
        WebElement yourCommentArea = driver.findElement(By.id("comment"));
        assertTrue((yourCommentArea.getText().equals("")) || yourCommentArea.getText().equals("null"));

        //2.3.1 green button
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));

        //2.3.2 red button
        WebElement noButton = driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//       3.1 fill the whole form, click "Send"
//       3.2 check fields are filled correctly
//       3.3 check button colors
//         (green with white letter and red with white letters)

        //3.1
        WebElement nameArea = driver.findElement(By.id("fb_name"));
        nameArea.sendKeys("Irina");
        WebElement ageArea = driver.findElement(By.id("fb_age"));
        ageArea.sendKeys("27");
        WebElement language2 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='French']"));
        language2.click();
        WebElement areaFemaleArea = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='female']"));
        areaFemaleArea.click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");
        WebElement commentArea = driver.findElement(By.cssSelector("textarea[name='comment']"));
        commentArea.sendKeys("Bootcamp2019");
        WebElement sendButton = driver.findElement(By.cssSelector(".w3-btn-block.w3-blue.w3-section"));
        sendButton.click();

       //3.2
        WebElement myName = driver.findElement(By.id("name"));
        assertEquals(myName.getText(), "Irina");
        WebElement myAge = driver.findElement(By.id("age"));
        assertEquals(myAge.getText(), "27");
        WebElement myLanguage = driver.findElement(By.id("language"));
        assertEquals(myLanguage.getText(), "French");
        WebElement myGenre = driver.findElement(By.id("gender"));
        assertEquals(myGenre.getText(), "female");
        WebElement myOption = driver.findElement(By.id("option"));
        assertEquals(myOption.getText(), "Good");
        WebElement myComment = driver.findElement(By.id("comment"));
        assertEquals(myComment.getText(), "Bootcamp2019");

        //3.3.1
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));

        //3.3.2
        WebElement noButton = driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        WebElement nameArea = driver.findElement(By.id("fb_name"));
        nameArea.sendKeys("Irina");
        WebElement sendButton = driver.findElement(By.cssSelector(".w3-btn-block.w3-blue.w3-section"));
        sendButton.click();
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        yesButton.click();
        WebElement messageArea = driver.findElement(By.id("message"));
        assertEquals(messageArea.getText(),"Thank you, Irina, for your feedback!");

        assertEquals("rgba(255, 255, 255, 1)", messageArea.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", messageArea.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        WebElement sendButton = driver.findElement(By.cssSelector(".w3-btn-block.w3-blue.w3-section"));
        sendButton.click();
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        yesButton.click();
        WebElement messageArea = driver.findElement(By.id("message"));
        assertEquals(messageArea.getText(),"Thank you for your feedback!");

        assertEquals("rgba(255, 255, 255, 1)", messageArea.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", messageArea.getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        WebElement nameArea = driver.findElement(By.id("fb_name"));
        nameArea.sendKeys("Irina");
        WebElement ageArea = driver.findElement(By.id("fb_age"));
        ageArea.sendKeys("27");
        WebElement language2 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='French']"));
        language2.click();
        WebElement areaFemaleArea = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='female']"));
        areaFemaleArea.click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");
        WebElement commentArea = driver.findElement(By.cssSelector("textarea[name='comment']"));
        commentArea.sendKeys("Bootcamp2019");
        WebElement sendButton = driver.findElement(By.cssSelector(".w3-btn-block.w3-blue.w3-section"));
        sendButton.click();
        WebElement noButton = driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge"));
        noButton.click();

        nameArea = driver.findElement(By.id("fb_name"));
        ageArea = driver.findElement(By.id("fb_age"));
        language2 = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='French']"));
        areaFemaleArea = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='female']"));
        dropdown = new Select(driver.findElement(By.id("like_us")));
        commentArea = driver.findElement(By.cssSelector("textarea[name='comment']"));

        assertEquals("Irina", nameArea.getAttribute("value"));
        assertEquals("27", ageArea.getAttribute("value"));
        assertEquals("French", language2.getAttribute("value"));
        assertEquals("female", areaFemaleArea.getAttribute("value"));
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());
        assertEquals("Bootcamp2019", commentArea.getAttribute("value"));
    }
}
