package project.bzu.assignment2.Models;

public class CV {
    private String name;
    private String email;
    private String DOB;
    private String gender;
    private String education;
    private String experience;
    private String skills;
    private String languages;
    private String references;

    public CV() {
    }

    public CV(String name, String email, String DOB, String gender, String education, String experience, String skills, String languages, String references) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.gender = gender;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.languages = languages;
        this.references = references;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    @Override
    public String toString() {
        return "CV{" +
                "Name= " + name + '\n' +
                "Email= " + email + '\n' +
                "DOB= " + DOB + '\n' +
                "Gender= " + gender + '\n' +
                "Education= " + education + '\n' +
                "Experience= " + experience + '\n' +
                "Skills= " + skills + '\n' +
                "Languages= " + languages + '\n' +
                "References= " + references + '\n'+ "}";
    }
}
