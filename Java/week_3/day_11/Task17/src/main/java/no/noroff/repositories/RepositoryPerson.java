package no.noroff.repositories;

import no.noroff.models.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.noroff.Program.dbHandler;

/**
 * This class does all the person, email, family and contactnumbers sql queries
 */
@Component
public class RepositoryPerson {

    public int findPerson(String firstname, String lastname) {
        String query = "SELECT * " +
                "FROM Person " +
                "WHERE firstname LIKE '" + firstname + "' " +
                " AND lastname LIKE '" + lastname + "';";

        int pid = 0;

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            pid = rs.getInt("personid");

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }

        return pid;
    }

    /**
     * Finds first and lastname of a person id
     *
     * @param pid Person id
     * @return Returns persons id
     */
    public String[] findName(final int pid) {
        String query = "SELECT * " +
                "FROM Person " +
                "WHERE personID = " + pid + ";";

        String[] name = new String[2];

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            name[0] = rs.getString("firstname");
            name[1] = rs.getString("lastname");

            return name;

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Looks up persons matching a given pattern
     *
     * @param search pattern to search for
     * @return a list of persons matching
     */
    public List<Person> lookUp(String search) {
        final List<Person> persons = new ArrayList<>();
        Person person;

        String query = "SELECT * "
                + "FROM Person "
                + "NATURAL JOIN Contact "
                + "NATURAL JOIN Emails "
                + "NATURAL JOIN HomeAddress "
                + "WHERE firstname LIKE '%" + search + "%' "
                + "OR lastname LIKE '%" + search + "%' "
                + "OR contactNumHome LIKE '%" + search + "%' "
                + "OR contactNumWork LIKE '%" + search + "%' "
                + "OR contactNumMobile LIKE '%" + search + "%' ";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                person = new Person(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("personid"),
                        rs.getString("birth")
                );
                person.setContactNumbers(new ContactNumbers(
                        rs.getString("contactNumWork"),
                        rs.getString("contactNumHome"),
                        rs.getString("contactNumMobile")
                ));
                person.setEmails(new Emails(
                        rs.getString("emailAdrWork"),
                        rs.getString("emailAdrPersonal")
                ));
                person.setHomeAddress(new HomeAddress(
                        rs.getString("streetAddress"),
                        rs.getString("postCode"),
                        rs.getString("city"),
                        rs.getString("country")
                ));
                //Sett family list
                person.setPersonFamilyList(findRelations(rs.getInt("personid")));
                persons.add(person);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return persons;
    }

    /**
     * Find relation from a personid
     *
     * @param personid PersonId from table Person
     * @return A family list
     */
    public List<Family> findRelations(final int personid) {
        final List<Family> family = new ArrayList<>();
        int relationId;

        final String query = "SELECT * "
                + "FROM Family "
                + "WHERE personID = " + personid + ";";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //System.out.println("Results: ");
            //System.out.printf("%s %20s %20s\n", "firstname", "lastname", "status");

            // loop through the result set
            while (rs.next()) {
                relationId = rs.getInt("relationID");
                String[] name = findName(relationId);

                if (name != null) {
                    family.add(new Family(
                            name[0],
                            name[1],
                            relationId,
                            rs.getString("status")
                    ));
                }
                //System.out.printf("%s %20s %20s\n", firstname, lastname, status);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return family;
    }

    /**
     * Gets all the persons stored in db
     *
     * @return a list of persons
     */
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        Person person;

        String query = "SELECT * "
                + "FROM Person "
                + "NATURAL JOIN Contact "
                + "NATURAL JOIN Emails "
                + "NATURAL JOIN HomeAddress;";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                person = new Person(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("personid"),
                        rs.getString("birth")
                );
                person.setContactNumbers(new ContactNumbers(
                        rs.getString("contactNumWork"),
                        rs.getString("contactNumHome"),
                        rs.getString("contactNumMobile")
                ));
                person.setEmails(new Emails(
                        rs.getString("emailAdrWork"),
                        rs.getString("emailAdrPersonal")
                ));
                person.setHomeAddress(new HomeAddress(
                        rs.getString("streetAddress"),
                        rs.getString("postCode"),
                        rs.getString("city"),
                        rs.getString("country")
                ));
                person.setPersonFamilyList(findRelations(rs.getInt("personid")));

                persons.add(person);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return persons;
    }

    /**
     * Gets all contact information stored in db
     *
     * @return a list of contacts
     */
    public List<ContactNumbers> getContacts() {
        List<ContactNumbers> contactNumbers = new ArrayList<>();

        String query = "SELECT * " +
                "FROM Contact;";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String work;
            String home;
            String mobile;


            // loop through the result set
            while (rs.next()) {
                work = rs.getString("contactNumWork");
                home = rs.getString("contactNumHome");
                mobile = rs.getString("contactNumMobile");

                contactNumbers.add(new ContactNumbers(work, home, mobile));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return contactNumbers;
    }

    /**
     * Gets all email information stored in db
     *
     * @return a list of emails
     */
    public List<Emails> getEmails() {
        List<Emails> emails = new ArrayList<>();

        //Endre query tilbake til *
        String query = "SELECT * " +
                "FROM Emails;";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String personal;
            String work;

            // loop through the result set
            while (rs.next()) {
                personal = rs.getString("emailAdrPersonal");
                work = rs.getString("emailAdrWork");

                emails.add(new Emails(work, personal));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return emails;
    }

    /**
     * Gets all home addresses stored in db
     *
     * @return a list of home addresses
     */
    public List<HomeAddress> getHomes() {
        List<HomeAddress> addresses = new ArrayList<>();
        String query = "SELECT * " +
                "FROM HomeAddress;";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String streetAddress;
            String postCode;
            String country;
            String city;


            // loop through the result set
            while (rs.next()) {
                streetAddress = rs.getString("streetaddress");
                postCode = rs.getString("postcode");
                country = rs.getString("country");
                city = rs.getString("city");

                addresses.add(new HomeAddress(streetAddress, postCode, city, country));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return addresses;
    }

    public void printAllFamily() {
        String sql = "SELECT * FROM Family NATURAL JOIN Person ORDER BY personID;";

        try {
            Connection conn = dbHandler.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int personID;
            int relationID;
            String firstname;
            String lastname;
            String status;


            // loop through the result set
            System.out.println("\nTABLE: Family");
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "personID", "firstname", "lastname", "relationID", "status");
            while (rs.next()) {
                personID = rs.getInt("personID");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                relationID = rs.getInt("relationID");
                status = rs.getString("status");

                System.out.printf("%-10d %-10s %-10s %-10d %-10s\n", personID, firstname, lastname, relationID, status);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printPersons() {
        List<Person> personList = getPersons();

        System.out.println("\nTABLE: Person");
        System.out.printf("%-10s %-15s %-15s %-10s\n", "personid", "firstname", "lastname", "birth");
        for (Person p : personList)
            System.out.printf("%-10s %-15s %-15s %-10s\n", p.getPersonId(), p.getFirstName(), p.getLastName(), p.getBirthday());

    }

    public void printContacts() {
        List<ContactNumbers> contactNumbersList = getContacts();

        System.out.println("\nTABLE: Contact");
        System.out.printf("%-15s %-15s %-15s\n", "work", "home", "mobile");

        for (ContactNumbers c : contactNumbersList)
            System.out.printf("%-15s %-15s %-15s\n", c.getWork(), c.getHome(), c.getMobile());

    }

    public void printEmails() {
        List<Emails> emailsList = getEmails();

        System.out.println("\nTABLE: Emails");
        System.out.printf("%-20s %-25s\n", "work", "personal");

        for (Emails e : emailsList)
            System.out.printf("%-20s %-25s\n", e.getWork(), e.getPersonal());
    }

    public void printHomes() {
        List<HomeAddress> homeAddresses = getHomes();

        System.out.println("\nTABLE: HomeAddress");
        System.out.printf("%-25s %-15s %-20s %-20s\n", "address", "postcode", "city", "country");

        for (HomeAddress h : getHomes())
            System.out.printf("%-25s %-15s %-20s %-20s\n", h.getStreetAddress(), h.getPostCode(), h.getMailingAddress(), h.getCountry());

    }

    public boolean addPerson(Person person) {
        if (dbHandler.addPerson(person.getFirstName(), person.getLastName(), person.getBirthday())) {
            addMail(person.getFirstName(), person.getLastName(), person.getEmails() != null ? person.getEmails().getWork() : "", person.getEmails() != null ? person.getEmails().getPersonal() : "");
            addPhone(person.getFirstName(), person.getLastName(), person.getContactNumbers() != null ? person.getContactNumbers().getHome() : "", person.getContactNumbers() != null ? person.getContactNumbers().getWork() : "", person.getContactNumbers() != null ? person.getContactNumbers().getMobile() : "");
            addHomeAddress(person.getFirstName(), person.getLastName(), person.getHomeAddress() != null ? person.getHomeAddress().getStreetAddress() : "", person.getHomeAddress() != null ? person.getHomeAddress().getPostCode() : "", person.getHomeAddress() != null ? person.getHomeAddress().getMailingAddress() : "", person.getHomeAddress() != null ? person.getHomeAddress().getCountry() : "");
        }

        return true;
    }

    /**
     * Adds mail information into db for a given person
     *
     * @param firstname     Persons firstname
     * @param lastname      Persons lastname
     * @param emailWork     Persons work email
     * @param emailPersonal Persons personal email
     * @return Return boolean value, false if add did not happend, true if success
     */
    public boolean addMail(String firstname, String lastname, String emailWork, String emailPersonal) {
        int pid = findPerson(firstname, lastname);

        if (pid > 0) {
            return dbHandler.addMail(pid, emailWork, emailPersonal);
        }

        return false;
    }

    /**
     * Adds contact information into db for a given person
     *
     * @param firstname   Persons firstname
     * @param lastname    Persons lastname
     * @param phoneHome   Persons house number
     * @param phoneWork   Persons work number
     * @param phoneMobile Persons mobile number
     * @return Returns true if success, false if failed
     */
    public boolean addPhone(String firstname, String lastname, String phoneHome, String phoneWork, String phoneMobile) {
        int pid = findPerson(firstname, lastname);

        if (pid > 0) {
            return dbHandler.addPhone(pid, phoneHome, phoneWork, phoneMobile);
        }

        return false;
    }

    /**
     * Adds home address information into db for a given person
     *
     * @param firstname Persons firstname
     * @param lastname  Persons lastname
     * @param street    Persons street address
     * @param postCode  Persons postcode
     * @param city      Persons city
     * @param country   PErsons country
     * @return Return true if add was success, false if failed
     */
    public boolean addHomeAddress(String firstname, String lastname, String street, String postCode, String city, String country) {
        int pid = findPerson(firstname, lastname);

        if (pid > 0) {
            return dbHandler.addHomeAddress(pid, street, postCode, city, country);
        }

        return false;
    }

    /**
     * Adds relation between two persons into db
     *
     * @param firstname         Selected persons firstname
     * @param lastname          Selected persons lastname
     * @param relationFirstname Selected persons relatives firstname
     * @param relationLastname  Selected persons relatives lastname
     * @param personStatus      how first person is related to the related person
     * @param relationStatus    how the related person is related to the first person
     * @return Returns true if success, false if failed
     */
    public boolean addRelation(String firstname, String lastname, String relationFirstname, String relationLastname, String personStatus, String relationStatus) {
        int pid = findPerson(firstname, lastname);
        int rid = findPerson(relationFirstname, relationLastname);

        if (pid > 0 && rid > 0) {
            return dbHandler.addRelation(pid, rid, personStatus, relationStatus);
        }

        return false;
    }

    /**
     * Adds tests data to db
     */
    public void addTests(){
        HomeAddress address = new HomeAddress("Møllerparken 4", "0459", "Oslo", "Norge");
        Emails email = new Emails("test@work.no", "test@personal.no");

        Person jan = new Person("Jan", "Le", "1.3.2019");
        Person michael = new Person("Michael", "Kifle", "1.3.2019");
        Person kenneth = new Person("Kenneth", "Ngo", "1.3.2019");
        Person quoc = new Person("Quoc", "Tran", "1.3.2019");

        jan.setHomeAddress(address);
        michael.setHomeAddress(address);
        kenneth.setHomeAddress(address);
        quoc.setHomeAddress(address);

        jan.setContactNumbers(new ContactNumbers("+47 999 98 888", "", "+47 900 00 100"));
        michael.setContactNumbers(new ContactNumbers("+47 111 22 333", "", "+47 000 00 000"));
        kenneth.setContactNumbers(new ContactNumbers("+47 555 33 555", "", "+47 932 234 345"));
        quoc.setContactNumbers(new ContactNumbers("+47 888 98 333", "", "+47 332 123 123"));

        jan.setEmails(email);
        michael.setEmails(email);
        kenneth.setEmails(email);
        quoc.setEmails(email);

        addPerson(jan);
        addPerson(michael);
        addPerson(kenneth);
        addPerson(quoc);

        addRelation(jan.getFirstName(), jan.getLastName(), michael.getFirstName(), michael.getLastName(), "teammate", "teammate");
        addRelation(jan.getFirstName(), jan.getLastName(), kenneth.getFirstName(), kenneth.getLastName(), "teammate", "teammate");
        addRelation(jan.getFirstName(), jan.getLastName(), quoc.getFirstName(), quoc.getLastName(), "teammate", "teammate");
        addRelation(michael.getFirstName(), michael.getLastName(), quoc.getFirstName(), quoc.getLastName(), "teammate", "teammate");
        addRelation(michael.getFirstName(), michael.getLastName(), kenneth.getFirstName(), kenneth.getLastName(), "teammate", "teammate");
        addRelation(quoc.getFirstName(), quoc.getLastName(), kenneth.getFirstName(), kenneth.getLastName(), "teammate", "teammate");
    }

    public void updateContactHome(String firstname, String lastname, String homeNumber) {
        List<ContactNumbers> contactsList = getContacts();
        int personID = findPerson(firstname, lastname);


        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person cant be found, please try again");
            return;
        }
        if (contactsList.size() == 0) {
            System.out.println("Email list is empty");
            return;
        }


        String updateSQL = "UPDATE Contact SET contactNumHome = ? WHERE personID = ?;";


        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);
            pstmt.setString(1, homeNumber);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            contactsList.get(personID - 1).setHome(homeNumber);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }

        }
    }//End of updateContactHome

    public void updateContactWork(String firstname, String lastname, String workNumber) {
        List<ContactNumbers> contactsList = getContacts();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person cant be found, please try again");
            return;
        }
        if (contactsList.size() == 0) {
            System.out.println("Email list is empty");
            return;
        }

        String updateSQL = "UPDATE Contact SET contactNumWork = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);
            pstmt.setString(1, workNumber);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            contactsList.get(personID - 1).setWork(workNumber);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }
    }//End of updateContactWork

    public void updateContactMobile(String firstname, String lastname, String mobileNumber) {

        List<ContactNumbers> contactsList = getContacts();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person cant be found, please try again");
            return;
        }
        if (contactsList.size() == 0) {
            System.out.println("Email list is empty");
            return;
        }

        String updateSQL = "UPDATE Contact SET contactNumMobile = ? WHERE personID = ?;";

        try {

            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, mobileNumber);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            contactsList.get(personID - 1).setMobile(mobileNumber);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }
    }//End of updateContactMobile

    public void updateEmailWork(String firstname, String lastname, String emailWork) {


        List<Emails> emailsList = getEmails();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person cant be found, please try again");
            return;
        }
        if (emailsList.size() == 0) {
            System.out.println("Email list is empty");
            return;
        }

        String updateSQL = "UPDATE Emails SET emailAdrWork = ? WHERE personID = ?;";


        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, emailWork);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            emailsList.get(personID - 1).setWork(emailWork);
            System.out.println("Transaction successful executed!");


        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }

        }

    }//End of updateEmailWork

    public void updateEmailPersonal(String firstname, String lastname, String emailPersonal) {

        List<Emails> emailsList = getEmails();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person cant be found, please try again");
            return;
        }
        if (emailsList.size() == 0) {
            System.out.println("Email list is empty");
            return;
        }

        String updateSQL = "UPDATE Emails SET emailAdrPersonal = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);
            pstmt.setString(1, emailPersonal);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            emailsList.get(personID - 1).setPersonal(emailPersonal);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }
    }//End of updateEmailPersonal

    public void updatePersonFirstname(String firstname, String lastname, String newFirstname) {


        List<Person> personList = getPersons();
        int personID = findPerson(firstname, lastname);
        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person not found, therefore nothing to update");
            return;
        }

        String updateSQL = "UPDATE Person SET firstname = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);


            pstmt.setString(1, newFirstname);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            personList.get(personID - 1).setFirstName(newFirstname);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.println("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }

        }
    }//End of updatePersonLastname

    public void updatePersonLastname(String firstname, String lastname, String newLastname) {


        List<Person> personList = getPersons();
        int personID = findPerson(firstname, lastname);
        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person not found, therefore nothing to update");
            return;
        }

        String updateSQL = "UPDATE Person SET lastname = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, newLastname);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            personList.get(personID - 1).setLastName(newLastname);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.println("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }

        }
    }//End of updatePersonLastname

    public void updateBirthday(String firstname, String lastname, int day, int month, int year) {

        List<Person> personList = getPersons();
        int personID = findPerson(firstname, lastname);
        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person not found, therefore nothing to update");
            return;
        }
        String birthday = "00.00.0000";
        if ((day > 0 && day <= 31) && (month > 0 && month <= 12) && (year >= 1000 && year <= 9999)) {
            birthday = day + "." + month + "." + year;
        } else {
            System.out.println("Invalid birthdate");
            return;
        }


        String updateSQL = "UPDATE Person SET birth = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, birthday);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            personList.get(personID - 1).setBirthday(birthday);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.println("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }

        }
    }

    public void updatePostArea(String firstname, String lastname, String postcode, String city, String country) {

        List<HomeAddress> homeAddresses = getHomes();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person not found, therefore nothing to update");
            return;
        }

        String updateSQL = "UPDATE HomeAddress SET postcode = ? , city = ? , country = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, postcode);
            pstmt.setString(2, city);
            pstmt.setString(3, country);
            pstmt.setInt(4, personID);
            pstmt.executeUpdate();
            connect.commit();
            homeAddresses.get(personID - 1).setPostNumber(postcode);
            homeAddresses.get(personID - 1).setCountry(country);
            homeAddresses.get(personID - 1).setMailingAddress(city);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.println("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }

    }

    public void updateStreetAddress(String firstname, String lastname, String streetAddress) {

        List<HomeAddress> homeAddresses = getHomes();
        int personID = findPerson(firstname, lastname);

        Connection connect = dbHandler.getConn();
        if (personID == 0) {
            System.out.println("Person not found, therefore nothing to update");
            return;
        }

        String updateSQL = "UPDATE HomeAddress SET streetAddress = ? WHERE personID = ?;";

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(updateSQL);

            pstmt.setString(1, streetAddress);
            pstmt.setInt(2, personID);
            pstmt.executeUpdate();
            connect.commit();
            homeAddresses.get(personID - 1).setStreetAddress(streetAddress);
            System.out.println("Transaction successful executed!");

        } catch (SQLException e) {

            try {
                System.out.println(e.getMessage());
                System.err.println("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }
    }

    public void dropPerson(String firstname, String lastname) {


        String sql1 = "DELETE FROM Person WHERE personID = ?; ";
        String sql2 = "DELETE FROM Emails WHERE personID = ?; ";
        String sql3 = "DELETE FROM HomeAddress WHERE personID = ?; ";
        String sql4 = "DELETE FROM Contact WHERE personID = ?; ";
        String sql5 = "DELETE FROM Family WHERE relationID ?; ";


        Connection connect = dbHandler.getConn();
        int personID = findPerson(firstname, lastname);

        if (personID == 0) {
            System.out.println("Cant find person");
            return;
        }

        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(sql1);
            PreparedStatement pstmt2 = connect.prepareStatement(sql2);
            PreparedStatement pstmt3 = connect.prepareStatement(sql3);
            PreparedStatement pstmt4 = connect.prepareStatement(sql4);
            PreparedStatement pstmt5 = connect.prepareStatement(sql5);

            pstmt.setInt(1, personID);
            pstmt2.setInt(1, personID);
            pstmt3.setInt(1, personID);
            pstmt4.setInt(1, personID);
            pstmt5.setInt(1, personID);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            connect.commit();


            System.err.println("Transaction successful executed!");


        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                System.err.print("Transaction being roll backed");
                connect.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Rollback failed");
            }
        }
    }

}
