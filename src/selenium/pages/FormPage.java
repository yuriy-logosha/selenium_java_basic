package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class FormPage {

    @FindBy(how = How.ID, using = "name") // By.id("name")
    private WebElement nameInput; // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.ID, using = "dob")
    private WebElement dobInput;
    @FindBy(how = How.ID, using = "english")
    private WebElement optionEnglish;
    @FindBy(how = How.ID, using = "french")
    private WebElement optionFrench;
    @FindBy(how = How.ID, using = "spanish")
    private WebElement optionSpanish;
    @FindBy(how = How.ID, using = "male")
    private WebElement radioMale;
    @FindBy(how = How.ID, using = "female")
    private WebElement radioFemale;

    @FindBy(how = How.ID, using = "status")
    private WebElement statusDropdown;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]")
    private WebElement addBtn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]")
    private WebElement cancelBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='addPersonBtn']")
    private WebElement clearAllBtn;

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
    }
    public void enterJob(String job) {
        dobInput.clear();
        dobInput.sendKeys(job);
    }
    public void enterDob(String dob) {
        dobInput.clear();
        dobInput.sendKeys(dob);
    }

    public void checkEnglish() {
        if (optionEnglish.isSelected()) {
            return;
        } else {
            optionEnglish.click();
        }

    }

    public void checkFrench() {
        if (optionFrench.isSelected()) {
            return;
        } else {
            optionFrench.click();
        }

    }

    public void checkSpanish() {
        if (optionSpanish.isSelected()) {
            return;
        } else {
            optionSpanish.click();
        }

    }

    public void selectFemale() {
radioFemale.click();
    }

    public void selectMale() {
        radioMale.click();
    }

    public void selectStatusEmployee() {
        Select s = new Select(statusDropdown);
        s.selectByValue("employee");
    }

    public void selectStatusIntern() {
        Select s = new Select(statusDropdown);
        s.selectByValue("intern");
    }

    public void selectStatusContractor() {
        Select s = new Select(statusDropdown);
        s.selectByValue("contractor");
    }

    public void clickAdd() {
        addBtn.click();


    }

    public void clickCancel() {
        cancelBtn.click();
    }
}








