package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
        assertEquals("rgba(33, 150, 243, 1)", h1.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", h1.getCssValue("text-decoration-color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']")).click();
//         check fields are empty or null
        WebElement nameInput2 = driver.findElement(By.id("name"));
        assertTrue((nameInput2.getText().equals(""))||(nameInput2.getText().equals("null")));

        WebElement ageInput2 = driver.findElement(By.id("age"));
        assertTrue((ageInput2.getText().equals(""))||(ageInput2.getText().equals("null")));

        WebElement languageInput2 = driver.findElement(By.id("language"));
        assertTrue((languageInput2.getText().equals(""))||(languageInput2.getText().equals("null")));

        WebElement genreInput2 = driver.findElement(By.id("gender"));
        assertTrue((genreInput2.getText().equals(""))||(genreInput2.getText().equals("null")));

        WebElement optionInput2 = driver.findElement(By.id("option"));
        assertTrue((optionInput2.getText().equals(""))||(optionInput2.getText().equals("null")));

        WebElement commentInput2 = driver.findElement(By.id("comment"));
        assertTrue((commentInput2.getText().equals(""))||(commentInput2.getText().equals("null")));

//         check button colors
//         (green with white letter and red with white letters)
        WebElement buttonGreen = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", buttonGreen.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", buttonGreen.getCssValue("text-decoration-color"));

        WebElement buttonRed = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", buttonRed.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", buttonRed.getCssValue("text-decoration-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"fb_name\"]"));
        String name = "John";
        textInput.sendKeys(name);

        WebElement ageInput = driver.findElement(By.xpath("//*[@id=\"fb_age\"]"));
        String age = String.valueOf(22);
        ageInput.sendKeys(age);

        WebElement checkBox = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        checkBox.click();

        WebElement radioMale = driver.findElement(By.xpath("//div[4]//input[1]"));
        radioMale.click();

        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='like_us']")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
        //assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        WebElement textComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        String comment = "comment";
        textComment.sendKeys(comment);

        //click "Send"

        driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']")).click();

        //check fields are filled correctly

        WebElement nameInput2 = driver.findElement(By.id("name"));
        assertEquals(nameInput2.getText(), "John");

        WebElement ageInput2 = driver.findElement(By.id("age"));
        assertEquals(ageInput2.getText(), "22");

        WebElement languageInput2 = driver.findElement(By.id("language"));
        assertEquals(languageInput2.getText(), "English");

        WebElement genreInput2 = driver.findElement(By.id("gender"));
        assertEquals(genreInput2.getText(), "male");

        WebElement optionInput2 = driver.findElement(By.id("option"));
        assertEquals(optionInput2.getText(), "Good");

        WebElement commentInput2 = driver.findElement(By.id("comment"));
        assertEquals(commentInput2.getText(), "comment");

        //check button colors
        //(green with white letter and red with white letters)

        WebElement buttonGreen = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", buttonGreen.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", buttonGreen.getCssValue("text-decoration-color"));

        WebElement buttonRed = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", buttonRed.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", buttonRed.getCssValue("text-decoration-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"fb_name\"]"));
        String name = "John";
        textInput.sendKeys(name);
//         click "Send"
        driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement errorMsg = driver.findElement(By.id("message"));
        assertEquals("Thank you, John, for your feedback!", errorMsg.getText());
//         color of text is white with green on the background
        WebElement colorText = driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("rgba(0, 0, 0, 0)", colorText.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", colorText.getCssValue("text-decoration-color"));

        Thread.sleep(2000);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']")).click();
//         check message text: "Thank you for your feedback!"
        WebElement errorMsg = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", errorMsg.getText());
//         color of text is white with green on the background
        WebElement colorText = driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("rgba(0, 0, 0, 0)", colorText.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", colorText.getCssValue("text-decoration-color"));

        Thread.sleep(2000);
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"fb_name\"]"));
        String name = "John";
        textInput.sendKeys(name);

        WebElement ageInput = driver.findElement(By.xpath("//*[@id=\"fb_age\"]"));
        String age = String.valueOf(22);
        ageInput.sendKeys(age);

        WebElement checkBox = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        checkBox.click();

        WebElement radioMale = driver.findElement(By.xpath("//div[4]//input[1]"));
        radioMale.click();

        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='like_us']")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
        //assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        WebElement textComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        String comment = "comment";
        textComment.sendKeys(comment);

        //click "Send"

        driver.findElement(By.xpath("//button[@class='w3-btn-block w3-blue w3-section']")).click();
//         click "No"
        driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']")).click();

//         check fields are filled correctly

        WebElement nameAfter = driver.findElement(By.xpath("//*[@id=\"fb_name\"]"));
        assertEquals("John", nameAfter.getAttribute("value"));

        WebElement agenoInput = driver.findElement(By.xpath("//*[@id=\"fb_age\"]"));
        assertEquals("22", agenoInput.getAttribute("value"));

        WebElement checnokBox = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        assertTrue(checnokBox.isSelected());

        WebElement radionoMale = driver.findElement(By.xpath("//div[4]//input[1]"));
        assertTrue(radionoMale.isSelected());

        Select dropNOdown = new Select(driver.findElement(By.xpath("//select[@id='like_us']")));
        assertEquals("Good", dropNOdown.getFirstSelectedOption().getText());

        WebElement textnoComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        assertEquals("comment", textnoComment.getAttribute("value"));


        Thread.sleep(3000);
    }
}
