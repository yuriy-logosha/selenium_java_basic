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
    	FeedbackPage page = new FeedbackPage(driver);
    	
    	assertEquals("", page.getName());
    	assertEquals("", page.getAge());
    	assertEquals(0, page.getSelectedLanguages().size());
    	assertEquals("", page.getGenderValue());
    	assertEquals("Choose your option", page.getOptionText());
    	assertEquals("", page.getComment());
    	assertEquals("rgba(33, 150, 243, 1)", page.getButtonCssValue("background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", page.getButtonCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
    	{
    		FeedbackPage page = new FeedbackPage(driver);
    		page.clickSend();
    	}
    	FeedbackForm page = new FeedbackForm(driver);
    	
    	assertTrue((page.getName().equals("") || page.getName().equals("null")));
    	assertTrue((page.getAge().equals("") || page.getAge().equals("null")));
    	assertTrue((page.getLanguage().equals("") || page.getLanguage().equals("null")));
    	assertTrue((page.getGender().equals("") || page.getGender().equals("null")));
    	assertTrue((page.getOption().equals("") || page.getOption().equals("null")));
    	assertTrue((page.getComment().equals("") || page.getComment().equals("null")));
    	
    	assertEquals("rgba(76, 175, 80, 1)", page.getButtonCssProperty(ButtonType.YES, "background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", page.getButtonCssProperty(ButtonType.YES, "color"));
    	assertEquals("rgba(244, 67, 54, 1)", page.getButtonCssProperty(ButtonType.NO, "background-color"));
    	assertEquals("rgba(255, 255, 255, 1)", page.getButtonCssProperty(ButtonType.NO, "color"));
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
