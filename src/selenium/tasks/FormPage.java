package selenium.tasks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormPage {
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'modal_button')]//button[contains(text(), 'Add')]")
    private WebElement addConfirm;
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'modal_button')]//button[contains(text(), 'Cancel')]")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'clear-btn')]//button[contains(text(), 'Clear')]")
    private WebElement clearAll;
    @FindBy(how = How.XPATH, using = "//*[@id='name']")
    private WebElement nameInput;
    @FindBy(how = How.XPATH, using = "//*[@id='surname']")
    private WebElement surnameInput;
    @FindBy(how = How.XPATH, using = "//*[@id='job']")
    private WebElement jobInput;
    @FindBy(how = How.XPATH, using = "//*[@id='dob']")
    private WebElement dobInput;
    @FindBy(how = How.XPATH, using = "//*[@id='english']")
    private WebElement langEn;
    @FindBy(how = How.XPATH, using = "//*[@id='french']")
    private WebElement langFr;
    @FindBy(how = How.XPATH, using = "//*[@id='spanish']")
    private WebElement langSp;
    @FindBy(how = How.XPATH, using = "//*[@id='male']")
    private WebElement genMale;
    @FindBy(how = How.XPATH, using = "//*[@id='female']")
    private WebElement genFemale;
    @FindBy(how = How.XPATH, using = "//*[@id='status']")
    private WebElement selStatus;
}
