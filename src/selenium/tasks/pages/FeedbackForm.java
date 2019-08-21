package selenium.tasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FeedbackForm {
	public enum ButtonType { YES, NO }
	@FindBy(how = How.ID, using = "name")
	private WebElement formName;
	@FindBy(how = How.ID, using = "age")
	private WebElement formAge;
	@FindBy(how = How.ID, using = "language")
	private WebElement formLanguage;
	@FindBy(how = How.ID, using = "gender")
	private WebElement formGender;
	@FindBy(how = How.ID, using = "option")
	private WebElement formOption;
	@FindBy(how = How.ID, using = "comment")
	private WebElement formComment;
	@FindBy(how = How.CSS, using = "button.w3-green")
	private WebElement yesButton;
	@FindBy(how = How.CSS, using = "button.w3-red")
	private WebElement noButton;
	
	FeedbackForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getName() {
		return formName.getText();
	}
	
	public String getAge() {
		return formAge.getText();
	}
	
	public String getLanguage() {
		return formLanguage.getText();
	}
	
	public String getGender() {
		return formGender.getText();
	}
	
	public String getOption() {
		return formOption.getText();
	}
	
	public String getComment() {
		return formComment.getText();
	}
	
	public void clickYes() {
		yesButton.click();
	}
	
	public void clickNo() {
		noButton.click();
	}
	
	public String getButtonCssProperty(ButtonType type, String attribute) {
		WebElement button;
		switch(type) {
			case YES:
				button = yesButton;
				break;
			case NO:
				button = noButton;
				break;
			default:
				return null;
		}
		return button.getCssValue(attribute);
	}
}
