package selenium.tasks.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.tasks.classes.Person;

public class ListPage {
	@FindBy(how = How.CLASS_NAME, using = "w3-padding-16")
	private List<WebElement> people;
	@FindBy(how = How.CSS, using = "#addPersonBtn[onclick='openModalForAddPerson()']")
	private WebElement addPersonButton;
	@FindBy(how = How.ID, using = "#addPersonBtn[onclick='resetListOfPeople()']")
	private WebElement resetListButton;
	
	public ListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public List<Person> getPersonList() {
		List<Person> personList = new ArrayList<Person>();
		for(WebElement e : people) {
			personList.add(getPersonFromWebElement(e));
		}
		return personList;
	}
	
	public Person getPerson(int indexInList) {
		WebElement e = people.get(indexInList);
		Person person = getPersonFromWebElement(e);
		return person;
	}
	
	private Person getPersonFromWebElement(WebElement dataSource) {
		Person person = new Person();
		person.setName(dataSource.findElement(By.className("name")).getText());
		person.setSurname(dataSource.findElement(By.className("surname")).getText());
		person.setJob(dataSource.findElement(By.className("job")).getText());
		person.setDob(dataSource.findElement(By.className("dob")).getText());
		person.setLanguageString(dataSource.findElement(By.className("language")).getText());
		person.setGender(dataSource.findElement(By.className("gender")).getText());
		person.setStatus(dataSource.findElement(By.className("status")).getText());
		return person;
	}
	
	public void clickAddPerson() {
		addPersonButton.click();
	}
	
	public void clickEditPerson(int index) {
		people.get(index).findElement(By.className("editbtn")).click();
	}
	
	public void clickDeletePerson(int index) {
		people.get(index).findElement(By.className("closebtn")).click();
	}
}
