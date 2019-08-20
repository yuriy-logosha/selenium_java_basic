package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;
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
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        assertEquals("", nameBox.getText());
        WebElement ageBox = driver.findElement(By.id("fb_age"));
        assertEquals("", ageBox.getText());
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        assertFalse(option1.isSelected());
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='French'][type='checkbox']"));
        assertFalse(option2.isSelected());
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Spanish'][type='checkbox']"));
        assertFalse(option3.isSelected());
        WebElement option4 = driver.findElement(By.cssSelector(".w3-check[value='Chinese'][type='checkbox']"));
        assertFalse(option4.isSelected());
        WebElement maleRadio = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(maleRadio.isSelected());
        WebElement femaleRadio = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(femaleRadio.isSelected());
        WebElement dontknowRadio = driver.findElement(By.xpath("//div[4]//input[3]"));
        assertTrue(dontknowRadio.isSelected());
        Select oSelect = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", oSelect.getFirstSelectedOption().getText());
        WebElement textArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        assertEquals("", textArea.getText());
        WebElement send = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        assertEquals("rgba(255, 255, 255, 1)", send.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", send.getCssValue("background-color"));




    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        WebElement send = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        send.click();
        WebElement yourName = driver.findElement(By.id("name"));
        assertTrue((yourName.getText().equals("")) || yourName.getText().equals("null"));
        WebElement yourAge= driver.findElement(By.id("age"));
        assertTrue((yourAge.getText().equals("")) || yourAge.getText().equals("null"));
        WebElement yourLanguage = driver.findElement(By.id("language"));
        assertTrue((yourLanguage.getText().equals("")) || yourLanguage.getText().equals("null"));
        WebElement yourGender = driver.findElement(By.id("gender"));
        assertTrue((yourGender.getText().equals("")) || yourGender.getText().equals("null"));
        WebElement yourOption = driver.findElement(By.id("option"));
        assertTrue((yourOption.getText().equals("")) || yourOption.getText().equals("null"));
        WebElement yourComment = driver.findElement(By.id("comment"));
        assertTrue((yourComment.getText().equals("")) || yourComment.getText().equals("null"));
        WebElement yesBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", yesBtn.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesBtn.getCssValue("background-color"));
        WebElement noBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", noBtn.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noBtn.getCssValue("background-color"));


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Mihails");
        WebElement ageBox = driver.findElement(By.id("fb_age"));
        ageBox.sendKeys("31");
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        option1.click();
        WebElement maleRadio = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        maleRadio.click();
        Select oSelect = new Select(driver.findElement(By.id("like_us")));
        oSelect.selectByValue("Why me?");
        WebElement textArea = driver.findElement(By.xpath("//textarea[@name='comment']"));
        textArea.sendKeys("kek");
        WebElement send = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        send.click();

        WebElement yourName = driver.findElement(By.id("name"));
        assertEquals(yourName.getText(), "Mihails");
        WebElement yourAge= driver.findElement(By.id("age"));
        assertEquals(yourAge.getText(), "31");
        WebElement yourLanguage = driver.findElement(By.id("language"));
        assertEquals(yourLanguage.getText(), "English");
        WebElement yourGender = driver.findElement(By.id("gender"));
        assertEquals(yourGender.getText(), "male");
        WebElement yourOption = driver.findElement(By.id("option"));
        assertEquals(yourOption.getText(), "Why me?");
        WebElement yourComment = driver.findElement(By.id("comment"));
        assertEquals(yourComment.getText(), "kek");
        WebElement yesBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", yesBtn.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", yesBtn.getCssValue("background-color"));
        WebElement noBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(255, 255, 255, 1)", noBtn.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noBtn.getCssValue("background-color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        WebElement nameBox = driver.findElement(By.id("fb_name"));
        nameBox.sendKeys("Mihails");
        WebElement send = driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']"));
        send.click();
        WebElement yesBtn = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        yesBtn.click();
        Thread.sleep(1000);
        WebElement thanksMsg = driver.findElement(By.id("message"));
        assertEquals(thanksMsg.getText(), "Thank you, Mihails, for your feedback!");

        assertEquals("rgba(255, 255, 255, 1)", thanksMsg.getCssValue("color"));
        assertEquals("rgba(0, 0, 0, 0)", thanksMsg.getCssValue("background-color"));

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
