package selenium.tasks;

public class Person {
    String name;
    String surname;
    String job;
    String dob;
    String languages;
    String gender;
    String status;

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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                    this.getLanguages().equals(persObj.getLanguages()) );
        }
        return super.equals(obj);
    }
}
