package no.noroff.models;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.StringJoiner;

public class Person {
    private String firstName;
    private String lastName;
    private String birthday;

    private int personId;

    private ContactNumbers contactNumbers;
    private Emails emails;
    private HomeAddress homeAddress;
    private List<Family> personFamilyList;

    public Person() {
    }

    public Person(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Person(String firstName, String lastName, int personId, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public ContactNumbers getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(ContactNumbers contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Emails getEmails() {
        return emails;
    }

    public void setEmails(Emails emails) {
        this.emails = emails;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<Family> getPersonFamilyList() {
        return personFamilyList;
    }

    public void setPersonFamilyList(List<Family> personFamilyList) {
        this.personFamilyList = personFamilyList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("birthday='" + birthday + "'")
                .toString();
    }
}
