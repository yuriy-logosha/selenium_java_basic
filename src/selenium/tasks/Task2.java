package selenium.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.tasks.pages.FeedbackForm;
import selenium.tasks.pages.FeedbackPage;
import selenium.tasks.pages.FeedbackForm.ButtonType;

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
    	WebElement nameField = driver.findElement(By.id("fb_name"));
    	WebElement ageField = driver.findElement(By.id("fb_age"));
    	List<WebElement> checkboxList = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
    	List<WebElement> genderList = driver.findElements(By.cssSelector(".w3-radio[type='radio']"));
    	Select likeUs = new Select(driver.findElement(By.id("like_us")));
    	WebElement commentArea = driver.findElement(By.cssSelector("textarea[name='comment']"));
    	WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
    	
    	assertEquals("", nameField.getText());
    	assertEquals("", ageField.getText());
    	for(WebElement e : checkboxList) {
    		assertFalse(e.isSelected());
    	}
    	for(WebElement e : genderList) {
    		if(!e.getAttribute("value").equals("")) {
    			assertFalse(e.isSelected());
    		} else {
    			assertTrue(e.isSelected());
    		}
    	}
    	assertEquals("Choose your option", likeUs.getFirstSelectedOption().getText());
    	assertEquals("", commentArea.getText());
    	assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
    	WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
    	sendButton.click();
    	
    	WebElement formName = driver.findElement(By.id("name"));
    	WebElement formAge = driver.findElement(By.id("age"));
    	WebElement formLanguage = driver.findElement(By.id("language"));
    	WebElement formGender = driver.findElement(By.id("gender"));
    	WebElement formOption = driver.findElement(By.id("option"));
    	WebElement formComment = driver.findElement(By.id("comment"));
    	WebElement yesButton = driver.findElement(By.cssSelector("button.w3-green"));
    	WebElement noButton = driver.findElement(By.cssSelector("button.w3-red"));
    	
    	assertTrue((formName.getText().equals("") || formName.getText().equals("null")));
    	assertTrue((formAge.getText().equals("") || formAge.getText().equals("null")));
    	assertTrue((formLanguage.getText().equals("") || formLanguage.getText().equals("null")));
    	assertTrue((formGender.getText().equals("") || formGender.getText().equals("null")));
    	assertTrue((formOption.getText().equals("") || formOption.getText().equals("null")));
    	assertTrue((formComment.getText().equals("") || formComment.getText().equals("null")));
    	
    	assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
    	assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
    	// Test data
    	String name = "Test Name";
    	String age = "29";
    	String[] language = {"English", "Spanish"};
    	String gender = "male";
    	String option = "Good";
    	String comment = "This is a comment!";
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
    	// Feedback page elements
    	{
	    	FeedbackPage page = new FeedbackPage(driver);
	    	page.setName(name);
	    	page.setAge(age);
	    	page.setLanguageByName(language[0]);
	    	page.setLanguageByName(language[1]);
	    	page.setGenderByValue(gender);
	    	page.setOptionByText(option);
	    	page.setComment(comment);
	    	page.clickSend();
    	}
    	
    	// Submit form elements
    	{
    		FeedbackForm page = new FeedbackForm(driver);
    		assertEquals(name, page.getName());
    		assertEquals(age, page.getAge());
    		assertEquals(gender, page.getGender().toLowerCase());
    		assertEquals(option, page.getOption());
    		assertEquals(comment, page.getComment());
    		
    		String[] formLanguage = page.getLanguage().split(",");
    		assertEquals(language.length, formLanguage.length);
    		boolean matchFound = false;
    		for(String fl : formLanguage) {
    			matchFound = false;
    			for(String l : language ) {
    				if(fl.equals(l)) {
    					matchFound = true;
    					break;
    				}
    			}
    			if(!matchFound) {
    				break;
    			}
    		}
    		assertTrue(matchFound);
    		
    		assertEquals("rgba(76, 175, 80, 1)", page.getButtonCssProperty(ButtonType.YES, "background-color"));
        	assertEquals("rgba(255, 255, 255, 1)", page.getButtonCssProperty(ButtonType.YES, "color"));
        	assertEquals("rgba(244, 67, 54, 1)", page.getButtonCssProperty(ButtonType.NO, "background-color"));
        	assertEquals("rgba(255, 255, 255, 1)", page.getButtonCssProperty(ButtonType.NO, "color"));
    	}
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    	String name = "Test Name";
    	{
	    	FeedbackPage page = new FeedbackPage(driver);
	    	page.setName(name);
	    	page.clickSend();
    	}
    	FeedbackForm page = new FeedbackForm(driver);
    	page.clickYes();
    	
    	String expectedMessage = "Thank you, " + name + ", for your feedback!";
    	WebElement textBox = driver.findElement(By.cssSelector("#fb_thx > div"));
    	WebElement textElement = textBox.findElement(By.id("message"));
    	assertEquals(expectedMessage, textElement.getText());
    	assertEquals("rgba(255, 255, 255, 1)", textBox.getCssValue("color"));
    	assertEquals("rgba(76, 175, 80, 1)", textBox.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    	{
	    	FeedbackPage page = new FeedbackPage(driver);
	    	page.clickSend();
    	}
    	FeedbackForm page = new FeedbackForm(driver);
    	page.clickYes();
    	
    	String expectedMessage = "Thank you for your feedback!";
    	WebElement textBox = driver.findElement(By.cssSelector("#fb_thx > div"));
    	WebElement textElement = textBox.findElement(By.id("message"));
    	assertEquals(expectedMessage, textElement.getText());
    	assertEquals("rgba(255, 255, 255, 1)", textBox.getCssValue("color"));
    	assertEquals("rgba(76, 175, 80, 1)", textBox.getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    	WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
    	String name = "xxxEDGELORDxxx";
    	String age = "21";
    	String[] language = {"Spanish", "Chinese"};
    	String gender = "male";
    	String option = "Why me?";
    	String comment = "No one understands me.";

    	
    	FeedbackPage page = new FeedbackPage(driver);
    	page.setName(name);
    	page.setAge(age);
    	page.setLanguageByName(language[0]);
    	page.setLanguageByName(language[1]);
    	page.setGenderByValue(gender);
    	page.setOptionByText(option);
    	page.setComment(comment);
    	page.clickSend();
    	
    	new FeedbackForm(driver).clickNo();
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fb_name")));
    	
    	String formName = page.getName();
    	String formAge = page.getAge();
    	List<String> formLanguage = page.getSelectedLanguages();
    	String formGender = page.getGenderValue();
    	String formOption = page.getOptionText();
    	String formComment = page.getComment();
    	
    	assertEquals(name, formName);
    	assertEquals(age, formAge);
    	assertEquals(gender, formGender);
    	assertEquals(option, formOption);
    	assertEquals(comment, formComment);
    	
    	boolean matchFound = false; 
    	for(String l : language) {
    		matchFound = false;
    		for(String fl : formLanguage) {
    			if(l.equals(fl)) {
    				matchFound = true;
    				break;
    			}
    		}
    		if(!matchFound) {
    			break;
    		}
    	}
    	assertTrue(matchFound);
    }
}
