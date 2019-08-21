package selenium.tasks;

import org.omg.CORBA.WStringSeqHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.ID, using = "dob")
    private WebElement dobInput;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]")
    private WebElement AddButton;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]")
    private WebElement CancelButton;
    @FindBy(how = How.XPATH, using = "//button[@id='addPersonBtn']")
    private WebElement ClearAllButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]")
    private WebElement EmployeeStatus;
    @FindBy(how = How.ID, using = "male")
    private WebElement MaleRadioButton;
    @FindBy(how = How.ID, using = "female")
    private WebElement FemaleRadioButton;
    @FindBy(how = How.ID, using = "english")
    private WebElement EnglishCheckBox;
    @FindBy(how = How.ID, using = "french")
    private WebElement FrenchCheckBox;
    @FindBy(how = How.ID, using = "spanish")
    private WebElement SpanishCheckBox;


    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
    }

    public void enterJob(String jobTitle) {
        jobInput.clear();
        jobInput.sendKeys(jobTitle);
    }

    public void enterDob(String Dob) {
        dobInput.clear();
        dobInput.sendKeys(Dob);
    }

    public void clickAddButton() {
        AddButton.click();
    }

    public void clickCancelButton() {
        CancelButton.click();
    }

    public void clickClearAllButton() {
        ClearAllButton.click();
    }

    public void selectStatusEmployee() {
        Select drop = new Select(EmployeeStatus);
        drop.selectByValue("employee");
    }
    public void selectStatusIntern() {
        Select drop = new Select(EmployeeStatus);
        drop.selectByValue("intern");
    }
    public void selectStatusContractor() {
        Select drop = new Select(EmployeeStatus);
        drop.selectByValue("contractor");
    }

    public void selectMaleRadioButton() {
        MaleRadioButton.click();
    }

    public void selectFemaleRadioButton() {
        FemaleRadioButton.click();
    }

    public void clickEnglishCheckBox() {
        if (!EnglishCheckBox.isSelected()) {
            EnglishCheckBox.click();
        }
    }

    public void clickFrenchCheckBox() {
        if (!FrenchCheckBox.isSelected()) {
            FrenchCheckBox.click();
        }
    }

    public void clickSpanishCheckBox() {
        if (!SpanishCheckBox.isSelected()) {
            SpanishCheckBox.click();
        }
    }

}
