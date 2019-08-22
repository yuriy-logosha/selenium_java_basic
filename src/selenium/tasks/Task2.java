package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.FeedbackPage;


import static org.junit.Assert.*;

public class Task2 {
    static WebDriver driver;
    static FeedbackPage feedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }


    public void checkIfInputFieldIsEmpty(WebElement element) {
        if (element.getAttribute("value").isEmpty()) {
            System.out.println(element + " is empty");
        } else {
            fail("Input field is not empty");
        }


    }


    public void checkIfTickFieldIsEmpty(WebElement element) {
        if (!element.getAttribute("checked").equals("true")) {
            System.out.println("Field is not ticked");
        } else {
            fail("Field is ticked");
        }
    }


    @Test
    public void initialFeedbackPage() {

        WebElement inputName = driver.findElement(By.id("fb_name"));
        checkIfInputFieldIsEmpty(inputName);

        WebElement inputAge = driver.findElement(By.id("fb_age"));
        checkIfInputFieldIsEmpty(inputAge);

        WebElement inputComment = driver.findElement(By.name("comment"));
        checkIfInputFieldIsEmpty(inputComment);

        WebElement englishLanguageField = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]"));
        assertFalse(englishLanguageField.isSelected());

        WebElement frenchLanguageField = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[2]"));
        assertFalse(frenchLanguageField.isSelected());

        WebElement spanishLanguageField = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]"));
        assertFalse(spanishLanguageField.isSelected());

        WebElement chineseLanguageField = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[4]"));
        assertFalse(chineseLanguageField.isSelected());

        WebElement maleField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]"));
        assertFalse(maleField.isSelected());

        WebElement femaleField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        assertFalse(femaleField.isSelected());

        WebElement dontKnowField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        dontKnowField.isSelected();
        System.out.println("Button is selected");

        WebElement chooseOptionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        assertEquals(new Select(chooseOptionField).getFirstSelectedOption().getText(),"Choose your option");

        WebElement sendButtonColor = driver.findElement(By.className("w3-btn-block "));
        assertEquals("rgba(255, 255, 255, 1)", sendButtonColor.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", sendButtonColor.getCssValue("background-color"));


    }


//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters


    @Test
    public void emptyFeedbackPage() throws Exception {

        WebElement sendButton = driver.findElement(By.className("w3-btn-block "));
        sendButton.click();

        WebElement yourNameField = driver.findElement(By.id("name"));
        assertTrue(yourNameField.getText().isEmpty());

        WebElement yourAgeField = driver.findElement(By.id("age"));
        assertTrue(yourAgeField.getText().isEmpty());

        WebElement yourLanguage = driver.findElement(By.id("language"));
        assertTrue(yourLanguage.getText().isEmpty());


        assertEquals("null", driver.findElement(By.id("gender")).getText());

        WebElement yourOption = driver.findElement(By.id("option"));
        assertEquals("null", yourOption.getText());

        WebElement yourComment = driver.findElement(By.id("comment"));
        assertTrue(yourComment.getText().isEmpty());

        WebElement yesButtonColor = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(255, 255, 255, 1)", yesButtonColor.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesButtonColor.getCssValue("background-color"));

        WebElement noButtonColor = driver.findElement((By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")));
        assertEquals("rgba(255, 255, 255, 1)", noButtonColor.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButtonColor.getCssValue("background-color"));


//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {

//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys("Nellija");

        WebElement ageField = driver.findElement(By.id("fb_age"));
        ageField.sendKeys("23");

        WebElement languageOption = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]"));
        languageOption.click();

        WebElement femaleGender = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        femaleGender.click();

        WebElement chooseOptionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        chooseOptionField.click();
        WebElement chooseOptionWhyMe = driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[5]"));
        chooseOptionWhyMe.click();

        WebElement commentField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea"));
        commentField.sendKeys("Today is rainy day");

        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
        sendButton.click();

        WebElement yesButtonColor = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(255, 255, 255, 1)", yesButtonColor.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesButtonColor.getCssValue("background-color"));

        WebElement noButtonColor = driver.findElement((By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")));
        assertEquals("rgba(255, 255, 255, 1)", noButtonColor.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noButtonColor.getCssValue("background-color"));


    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Nellija");
        WebElement send = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        send.click();
        WebElement yesButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        yesButton.click();

        WebElement thankYouMessage = driver.findElement(By.id("message"));
        assertEquals(thankYouMessage.getText(), "Thank you, Nellija, for your feedback!");

        assertEquals("rgba(255, 255, 255, 1)", thankYouMessage.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", thankYouMessage.getCssValue("background-color"));

//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {

        WebElement send = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
        send.click();

        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        yesButton.click();

        WebElement thankYouMessage = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        WebElement messageBox = driver.findElement(By.className("w3-green"));

        assertEquals(thankYouMessage.getText(), "Thank you for your feedback!");
        assertEquals("rgba(255, 255, 255, 1)", thankYouMessage.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", messageBox.getCssValue("background-color"));


//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Than you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {

//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly

        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys("Nellija");

        WebElement ageField = driver.findElement(By.id("fb_age"));
        ageField.sendKeys("23");

        WebElement languageOption = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]"));
        languageOption.click();

        WebElement femaleGender = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        femaleGender.click();

        WebElement chooseOptionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        chooseOptionField.click();

        WebElement chooseOptionWhyMe = driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[5]"));
        chooseOptionWhyMe.click();

        WebElement commentField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea"));
        commentField.sendKeys("Today is rainy day");

        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
        sendButton.click();

        WebElement noButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        noButton.click();


        WebElement inputName = driver.findElement(By.id("fb_name"));
        assertEquals(inputName.getAttribute("value"), "Nellija");

        WebElement inputAge = driver.findElement(By.id("fb_age"));
        assertEquals(inputAge.getAttribute("value"), "23");


        WebElement spanishLanguageField = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]"));
        assertTrue(spanishLanguageField.isSelected());

        WebElement femaleField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        assertTrue(femaleField.isSelected());

        WebElement inputComment = driver.findElement(By.name("comment"));
        assertEquals(inputComment.getAttribute("value"), "Today is rainy day");

        WebElement optionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        assertEquals(new Select(optionField).getFirstSelectedOption().getAttribute("value"), "Why me?");
    }
}
