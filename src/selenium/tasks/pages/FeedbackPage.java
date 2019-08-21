package selenium.tasks.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FeedbackPage {
	@FindBy(how = How.ID, using = "fb_name")
	private WebElement nameField;
	@FindBy(how = How.ID, using = "fb_age")
	private WebElement ageField;
	@FindBy(how = How.CSS, using = ".w3-check[type='checkbox']")
	private List<WebElement> languageList;
	@FindBy(how = How.CSS, using = ".w3-radio[type='radio']")
	private List<WebElement> genderList;
	@FindBy(how = How.ID, using = "like_us")
	private WebElement likeUs;
	@FindBy(how = How.CSS, using = "textarea[name='comment']")
	private WebElement commentArea;
	@FindBy(how = How.CSS, using = "button[type='submit']")
	private WebElement sendButton;
	
	public FeedbackPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setName(String name) {
		nameField.clear();
		nameField.sendKeys(name);
	}
	
	public String getName() {
		return nameField.getAttribute("value");
	}
	
	public void setAge(String age) {
		ageField.clear();
		ageField.sendKeys(age);
	}
	
	public String getAge() {
		return ageField.getAttribute("value");
	}
	
	public void setLanguageByName(String language) {
		for(WebElement e : languageList) {
    		if(e.getAttribute("value").equals(language)) {
    			e.click();
    		}
    	}
	}
	
	public List<String> getSelectedLanguages() {
		List<String> list = new ArrayList<String>();
		for(WebElement e : languageList) {
			if(e.isSelected()) {
				list.add(e.getAttribute("value"));
			}
    	}
		return list;
	}
	
	public void setGenderByValue(String gender) {
		for(WebElement e : genderList) {
    		if(e.getAttribute("value").equals(gender)) {
    			e.click();
    		}
    	}
	}
	
	public String getGenderValue() {
		for(WebElement e : genderList) {
    		if(e.isSelected()) {
    			return e.getAttribute("value");
    		}
    	}
		return "";
	}
	
	public void setOptionByText(String option) {
		Select dropdown = new Select(likeUs);
		dropdown.selectByVisibleText(option);
	}
	
	public String getOptionText() {
		Select dropdown = new Select(likeUs);
		return dropdown.getFirstSelectedOption().getText();
	}
	
	public void setComment(String comment) {
		commentArea.clear();
		commentArea.sendKeys(comment);
	}
	
	public String getComment() {
		return commentArea.getAttribute("value");
	}
	
	public void clickSend() {
		sendButton.click();
	}
	
	public String getButtonCssValue(String cssAttribute) {
		return sendButton.getCssValue(cssAttribute);
	}
}
