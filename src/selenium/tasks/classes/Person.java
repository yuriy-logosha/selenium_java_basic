package selenium.tasks.classes;

public class Person {
	private String name;
	private String surname;
	private String job;
	private String dob;
	private String languageString;
	private String gender;
	private String employeeStatus;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setLanguageString(String rawLangs) {
		this.languageString = rawLangs;
	}
	public String getLanguageString() {
		return this.languageString;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return employeeStatus;
	}
	public void setStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			Person persObj = (Person) obj;
			return (this.getName().equals(persObj.getName()) &&
					this.getSurname().equals(persObj.getSurname()) &&
					this.getJob().equals(persObj.getJob()) &&
					this.getDob().equals(persObj.getDob()) &&
					this.getGender().equals(persObj.getGender()) &&
					this.getStatus().equals(persObj.getStatus()) &&
					this.getLanguageString().equals(persObj.getLanguageString()) );
		}
		return super.equals(obj);
	}
}
