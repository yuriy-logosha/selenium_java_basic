package selenium.tasks.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
	private WebDriver driver;
	
	@FindBy(how = How.ID, using = "name")
	private WebElement name;
	@FindBy(how = How.ID, using = "surname")
	private WebElement surname;
	@FindBy(how = How.ID, using = "job")
	private WebElement job;
	@FindBy(how = How.ID, using = "dob")
	private WebElement dob;
	@FindBy(how = How.CSS, using = ".w3-check[type='checkbox']")
	private List<WebElement> langauge;
	@FindBy(how = How.CSS, using = ".w3-radio[type='radio']")
	private List<WebElement> gender;
	@FindBy(how = How.ID, using = "status")
	private WebElement status;
	@FindBy(how = How.CSS, using = "button[onclick='openModalForAddPerson()']")
	private WebElement clearButton;
	@FindBy(how = How.CSS, using = "button[onclick='addPersonToList()']")
	private WebElement addButton;
	@FindBy(how = How.CSS, using = "button[onclick^='editPerson']")
	private WebElement editButton;
	@FindBy(how = How.CSS, using = "button[onclick='window.history.back()']")
	private WebElement cancelButton;
	
	public FormPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void setName(String name) {
		this.name.clear();
		this.name.sendKeys(name);
	}
	
	public void setSurname(String surname) {
		this.surname.clear();
		this.surname.sendKeys(surname);
	}
	
	public void setJob(String job) {
		this.job.clear();
		this.job.sendKeys(job);
	}
	
	public void setDobByString(String dob) {
		// DOB - MM/DD/YYYY
		this.dob.clear();
		this.dob.sendKeys(dob);
	}

	public void setDobByPicker(String dob) {
		// DOB - MM/DD/YYYY
		this.dob.clear();
		this.dob.click();
		
		WebElement dateWidget = this.driver.findElement(By.id("ui-datepicker-div"));
		WebElement widgetYear = dateWidget.findElement(By.cssSelector(".ui-datepicker-week-end[data-handler='selectDay']"));
		String[] explodedDate = dob.split("//");
	}
	
	public void setLanguageById(String langauge) {
		String langLower = langauge.toLowerCase();
		for(WebElement e : this.langauge) {
			if(e.getAttribute("id").equals(langLower) && !e.isSelected()) {
				e.click();
			}
		}
	}
	
	public void setLanguageById(String[] langauge) {
		for(String l : langauge) {
			setLanguageById(l);
		}
	}
	
	public void setGenderById(String gender) {
		String langLower = gender.toLowerCase();
		for(WebElement e : this.gender) {
			if(e.getAttribute("id").equals(langLower)) {
				e.click();
			}
		}
	}
	
	public void setEmployeeStatusByValue(String text) {
		Select select = new Select(status);
		select.selectByValue(text);
	}
	
	public void clickAdd() {
		addButton.click();
	}
	
	public void clickEdit() {
		editButton.click();
	}
	
	public void clickCancel() {
		cancelButton.click();
	}
}
