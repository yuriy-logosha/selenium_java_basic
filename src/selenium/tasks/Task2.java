package selenium.tasks;

import javafx.scene.web.WebEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

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

    public void checkIfTickFieldIsEmpty(WebElement element) {
        if (element.getAttribute("checked") != "true") {
        } else {
            fail("Field is ticked");
        }
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        WebElement inputName = driver.findElement(By.id("fb_name"));
        assertTrue(inputName.getText().isEmpty());
        WebElement inputAge = driver.findElement(By.id("fb_age"));
        assertTrue(inputAge.getText().isEmpty());
        WebElement inputEnglish = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]"));
        checkIfTickFieldIsEmpty(inputEnglish);
        WebElement inputFrench = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[2]"));
        checkIfTickFieldIsEmpty(inputFrench);
        WebElement inputSpanish = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]"));
        checkIfTickFieldIsEmpty(inputSpanish);
        WebElement inputChinese = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[4]"));
        checkIfTickFieldIsEmpty(inputChinese);
        WebElement inputMale = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]"));
        checkIfTickFieldIsEmpty(inputMale);
        WebElement inputFemale = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        checkIfTickFieldIsEmpty(inputFemale);
        //         "Don't know" is selected in "Genre"
        WebElement inputDontKnow = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        inputDontKnow.isSelected();
//         "Choose your option" in "How do you like us?"
        WebElement chouseOption = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        chouseOption.click();
        WebElement chouseGood = driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[2]"));
        chouseGood.click();
//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));

    }


    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();
//         check fields are empty or null
        WebElement yourName = driver.findElement((By.id("name")));
        assertTrue(yourName.getText().isEmpty());
        WebElement yourAge = driver.findElement(By.id("age"));
        assertTrue(yourName.getText().isEmpty());
        WebElement yourlang = driver.findElement(By.id("language"));
        assertTrue(yourName.getText().isEmpty());
        WebElement yourGendre = driver.findElement(By.id("gender"));
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        WebElement yourOption = driver.findElement(By.id("option"));
        assertEquals("null", driver.findElement(By.id("option")).getText());
        WebElement yourComment = driver.findElement(By.id("comment"));
        assertTrue(yourName.getText().isEmpty());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement ButtonYes = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(255, 255, 255, 1)", ButtonYes.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", ButtonYes.getCssValue("background-color"));
        WebElement ButtonNo = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        assertEquals("rgba(255, 255, 255, 1)", ButtonNo.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", ButtonNo.getCssValue("background-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Alina");
        WebElement ageBox = driver.findElement(By.id("fb_age"));
        ageBox.sendKeys("28");
        WebElement inputLanguage = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        inputLanguage.click();
        WebElement inputFemale = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        inputFemale.click();
        WebElement chooseOptionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        chooseOptionField.click();
        WebElement whyMe = driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[5]"));
        whyMe.click();
        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        commentArea.sendKeys("Meow");
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();

//         check fields are filled correctly
        WebElement yourName = driver.findElement(By.id("name"));
        assertEquals("Alina", driver.findElement(By.id("name")).getText());
        WebElement yourAge = driver.findElement(By.id("age"));
        assertEquals("28", driver.findElement(By.id("age")).getText());
        WebElement yourlang = driver.findElement(By.id("language"));
        assertEquals("English", driver.findElement(By.id("language")).getText());
        WebElement yourGendre = driver.findElement(By.id("gender"));
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        WebElement yourOption = driver.findElement(By.id("option"));
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        WebElement yourComment = driver.findElement(By.id("comment"));
        assertEquals("Meow", driver.findElement(By.id("comment")).getText());
        //   check button colors
        //  (green with white letter and red with white letters)
        WebElement ButtonYes = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(255, 255, 255, 1)", ButtonYes.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", ButtonYes.getCssValue("background-color"));
        WebElement ButtonNo = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        assertEquals("rgba(255, 255, 255, 1)", ButtonNo.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", ButtonNo.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Alina");
//         click "Send"
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        yesButton.click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement messageInfo = driver.findElement(By.id("message"));
        assertEquals("Thank you, Alina, for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", messageInfo.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", messageInfo.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        yesButton.click();
//         check message text: "Thank you for your feedback!"
        WebElement messageInfo = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", messageInfo.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", messageInfo.getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Alina");
        WebElement ageBox = driver.findElement(By.id("fb_age"));
        ageBox.sendKeys("28");
        WebElement inputLanguage = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        inputLanguage.click();
        WebElement inputFemale = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]"));
        inputFemale.click();
        WebElement chooseOptionField = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        chooseOptionField.click();
        WebElement whyMe = driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[5]"));
        whyMe.click();
        WebElement commentArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        commentArea.sendKeys("Meow");
//         click "Send"
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();
//         click "No"
        WebElement noButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        noButton.click();
//         check fields are filled correctly
        WebElement yourName = driver.findElement(By.id("fb_name"));
        assertEquals("Alina", yourName.getAttribute("value"));
        WebElement yourAge= driver.findElement(By.id("fb_age"));
        assertEquals("28", yourAge.getAttribute("value"));
        WebElement option1after = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        assertTrue(option1after.isSelected());
        WebElement option2after = driver.findElement(By.xpath("//div[4]//input[1]"));
        WebElement textAreaAfter = driver.findElement(By.xpath("//textarea[@name='comment']"));
    }
}
