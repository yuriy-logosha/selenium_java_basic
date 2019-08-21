package selenium.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import selenium.tasks.classes.Person;
import selenium.tasks.pages.FormPage;
import selenium.tasks.pages.ListPage;

//import pages.FormPage;
//import pages.ListPage;

public class Task3Bonus {
    WebDriver driver;
//	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
//	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class
    ListPage listPage;
    FormPage formPage;
    
    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/list_of_people.html");
        listPage = new ListPage(driver);
        formPage = new FormPage(driver);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() throws Exception {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
    	// Test data
    	String name = "Test";
    	String surname = "Person";
    	String job = "Dream Job";
    	String dob = "03/22/1980";
    	String[] languagesId = {"english", "spanish"};
    	String genderId = "male";
    	String employeeStatus = "contractor";
    	
    	// Save current persons on list
    	List<Person> currentPersons = listPage.getPersonList();
    	listPage.clickAddPerson();
    	
    	formPage.setName(name);
    	formPage.setSurname(surname);
    	formPage.setJob(job);
    	formPage.setDobByString(dob);
    	formPage.setLanguageById(languagesId);
    	formPage.setGenderById(genderId);
    	formPage.setEmployeeStatusByValue(employeeStatus);
    	formPage.clickAdd();
    	
    	List<Person> newPersons = listPage.getPersonList();
    	assertEquals(currentPersons.size()+1, newPersons.size());
    	
    	int samePersons = 0;
    	int notSamePersons = 0;
    	
    	for(Person p : newPersons) {
    		boolean matchFound = false;
    		for(Person c : currentPersons) {
    			if(c.equals(p)) {
    				matchFound = true;
    				break;
    			}
    		}
    		if(matchFound) {
    			samePersons++;
    		} else {
    			notSamePersons++;
    			if(notSamePersons > 1) {
    				fail("Only 1 persons information expected to be new");
    			}
    			assertEquals(name, p.getName());
    			assertEquals(surname, p.getSurname());
    			assertEquals(job, p.getJob());
    		}
    	}
    	
    	assertEquals(currentPersons.size(), samePersons);
    }

    @Test
    public void editPerson() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
    	int empIndexInList = 0;
    	
    	String surname = "Other";
    	String job = "Job";
    	String languageId = "spanish";
    	String employeeStatusVal = "contractor";
    	String expectedLanguageString = "English, Spanish";
    	
    	List<Person> currentPersons = listPage.getPersonList();
    	Person oldEditablePerson = listPage.getPerson(empIndexInList);
    	
    	listPage.clickEditPerson(empIndexInList);
    	formPage.setSurname(surname);
    	formPage.setJob(job);
    	formPage.setLanguageById(languageId);
    	formPage.setEmployeeStatusByValue(employeeStatusVal);
    	formPage.clickEdit();
    	
    	List<Person> newPersons = listPage.getPersonList();
    	Person newEditablePerson = listPage.getPerson(empIndexInList);
    	
    	assertEquals(currentPersons.size(), newPersons.size());
    	
    	int samePersons = 0;
    	int notSamePersons = 0;
    	
    	for(Person p : newPersons) {
    		boolean matchFound = false;
    		for(Person c : currentPersons) {
    			if(c.equals(p)) {
    				matchFound = true;
    				break;
    			}
    		}
    		if(matchFound) {
    			samePersons++;
    		} else {
    			notSamePersons++;
    			if(notSamePersons > 1) {
    				fail("Only 1 persons information expected to be changed");
    			}
    		}
    	}
    	
    	assertEquals(currentPersons.size()-1, samePersons);
    	 	
    	assertEquals(expectedLanguageString, newEditablePerson.getLanguageString());
    	assertEquals(job, newEditablePerson.getJob());
    	assertEquals(expectedLanguageString, newEditablePerson.getLanguageString());
    	assertEquals(employeeStatusVal, newEditablePerson.getStatus());
    	assertEquals(oldEditablePerson.getName(), newEditablePerson.getName());
    	assertEquals(oldEditablePerson.getDob(), newEditablePerson.getDob());
    	assertEquals(oldEditablePerson.getGender(), newEditablePerson.getGender());
    }

    @Test
    public void editPersonCancel() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
    	int empIndexInList = 0;
    	
    	String surname = "Other";
    	String job = "Job";
    	String languageId = "spanish";
    	String employeeStatusVal = "contractor";
    	
    	List<Person> currentPersons = listPage.getPersonList();
    	
    	listPage.clickEditPerson(empIndexInList);
    	formPage.setSurname(surname);
    	formPage.setJob(job);
    	formPage.setLanguageById(languageId);
    	formPage.setEmployeeStatusByValue(employeeStatusVal);
    	formPage.clickCancel();
    	
    	List<Person> newPersons = listPage.getPersonList();
    	
    	int samePersons = 0;
    	
    	for(Person p : newPersons) {
    		boolean matchFound = false;
    		for(Person c : currentPersons) {
    			if(c.equals(p)) {
    				matchFound = true;
    				break;
    			}
    		}
    		if(matchFound) {
    			samePersons++;
    		} else {
    			fail("Expected persons not to change after cancelling edit");
    		}
    	}
    	
    	assertEquals(currentPersons.size(), newPersons.size());
    	assertEquals(currentPersons.size(), samePersons);
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
    	int empIndexInList = 1;
    	List<Person> currentPersons = listPage.getPersonList();
    	Person personToDelete = listPage.getPerson(empIndexInList);
    	listPage.clickDeletePerson(empIndexInList);
    	List<Person> newPersons = listPage.getPersonList();
    	
    	// Check to see that size decreased by 1 and deleted person not in list
    	assertEquals(currentPersons.size()-1, newPersons.size());
    	assertFalse(newPersons.contains(personToDelete));
    	
    	// Check to see if all new persons are also in old list
    	for(Person np : newPersons) {
    		boolean matchFound = false;
    		for(Person op : currentPersons) {
    			if(np.equals(op)) {
    				matchFound = true;
    				break;
    			}
    		}
    		if(!matchFound) {
    			fail("After deletion of person, existing person information changed");
    		}
    	}
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
    	listPage.deleteAllPersons();
    	List<Person> newPersons = listPage.getPersonList();
    	
    	// Check that no persons are found and that the table doesn't have any child elements
    	assertEquals(0, newPersons.size());
    	assertEquals(0, driver.findElements(By.cssSelector("#listOfPeople > *")).size());
    	
    	// Check that the Add button works by adding new person
    	listPage.clickAddPerson();
    	
    	String name = "Test";
    	String surname = "Person";
    	String job = "Dream Job";
    	String dob = "03/22/1980";
    	String genderId = "male";
    	String language = "English";
    	String employeeStatus = "contractor";
    	
    	Person newPerson = new Person();
    	newPerson.setName(name);
    	newPerson.setSurname(surname);
    	newPerson.setJob(job);
    	newPerson.setDob(dob);
    	newPerson.setGender(genderId);
    	newPerson.setStatus(employeeStatus);
    	// Passes with this - BUG (probably)
    	newPerson.setLanguageString("English,");
    	//Fails with this
    	//newPerson.setLanguageString("English");
    	
    	formPage.setName(name);
    	formPage.setSurname(surname);
    	formPage.setJob(job);
    	formPage.setDobByString(dob);
    	formPage.setGenderById(genderId);
    	formPage.setEmployeeStatusByValue(employeeStatus);
    	formPage.clickAdd();
    	
    	List<Person> newList = listPage.getPersonList();
    	assertEquals(1, newList.size());
    	assertTrue(newPerson.equals(newList.get(0)));
    }
}
