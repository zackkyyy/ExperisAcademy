public class Person {
    private String firstname;
    private String lastname;
    private String telephone;
    private int age;

    public Person(String firstname, String lastname, int age, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.telephone = telephone;
    }

    public Person(String firstname, String lastname, int age) {
        this(firstname, lastname, age, "N/A");
    }

    public Person(String firstname, String lastname, String telephone) {
        this(firstname, lastname, 0, telephone);
    }

    public Person(String firstname, String lastname) {
        this(firstname, lastname, 0, "N/A");
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public String getTelephone() {
        return telephone;
    }
}
