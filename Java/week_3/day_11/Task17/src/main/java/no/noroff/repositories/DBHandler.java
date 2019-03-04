package no.noroff.repositories;

import java.sql.*;

public class DBHandler {
    private Connection conn;

    public DBHandler() {
        conn = connectDB();
        createTables();
    }

    public Connection connectDB() {
        // SQLite connection URL to DB
        String url = "jdbc:sqlite:src\\main\\resources\\db.sqlite"; //Change to Database url

        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    Connection getConn() {
        try {
            if(conn.isClosed()) {
                conn = connectDB();
            }
        } catch (SQLException e) {
            System.out.println("getConn() fucked up");
        }

        return conn;
    }

    public boolean closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void createTables(){
        final String sqlPerson = ""
                + "CREATE TABLE Person "
                + "(personID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "firstname TEXT NOT NULL, "
                + "lastname TEXT NOT NULL, "
                + "birth TEXT NOT NULL);";

        final String sqlEmail = ""
                + "CREATE TABLE Emails "
                + "(personID INTEGER NOT NULL UNIQUE, "
                + "emailAdrWork TEXT, "
                + "emailAdrPersonal TEXT, "
                + "FOREIGN KEY (personID) REFERENCES Person(personID));";

        final String sqlContact = ""
                + "CREATE TABLE Contact ( "
                + "personID INTEGER NOT NULL UNIQUE, "
                + "contactNumHome TEXT, "
                + "contactNumWork TEXT, "
                + "contactNumMobile TEXT, "
                + "FOREIGN KEY (personID) REFERENCES Person(personID));";

        final String sqlHomeAddress = ""
                + "CREATE TABLE HomeAddress ("
                + "personID INTEGER NOT NULL UNIQUE, "
                + "streetAddress TEXT, "
                + "postCode TEXT, "
                + "city TEXT, "
                + "country TEXT, "
                + "FOREIGN KEY (personID) REFERENCES Person(personID));";

        final String sqlFamily = "" +
                "CREATE TABLE Family (" +
                "personID INTEGER NOT NULL, " +
                "relationID INTEGER NOT NULL, " +
                "status TEXT NOT NULL, " +
                "PRIMARY KEY (personID, relationID));";

        try {
            Connection conn = getConn();
            Statement stmt  = conn.createStatement();
            stmt.addBatch(sqlPerson);
            stmt.addBatch(sqlEmail);
            stmt.addBatch(sqlContact);
            stmt.addBatch(sqlHomeAddress);
            stmt.addBatch(sqlFamily);
            stmt.executeBatch();
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    boolean addPerson(String firstname, String lastname, String birth) {
        String query = String.format("INSERT INTO Person (firstname, lastname, birth) " +
                "VALUES (\"%s\", \"%s\", \"%s\");", firstname, lastname, birth);

        try {
            Connection conn = this.getConn();
            conn.setAutoCommit(false);
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    boolean addMail(int personid, String emailWork, String emailPersonal) {
        String query = String.format("INSERT INTO Emails " +
                "VALUES (%d, \"%s\", \"%s\");", personid, emailWork, emailPersonal);

        try {
            Connection conn = this.getConn();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    boolean addPhone(int personid, String phoneHome, String phoneWork, String phoneMobile) {
        String query = String.format("INSERT INTO Contact " +
                "VALUES (%d, \"%s\", \"%s\", \"%s\");", personid, phoneHome, phoneWork, phoneMobile);

        try {
            Connection conn = this.getConn();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    boolean addHomeAddress(int personid, String street, String postCode, String city, String country) {
        String query = String.format("INSERT INTO HomeAddress " +
                "VALUES (%d, \"%s\", \"%s\", \"%s\", \"%s\");", personid, street, postCode, city, country);

        try {
            Connection conn = this.getConn();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    boolean addRelation(int personid, int relationid, String personStatus, String relationStatus) {
        String query1 = String.format("INSERT INTO Family " +
                "VALUES (%d, %d, \"%s\");", personid, relationid, relationStatus);

        String query2 = String.format("INSERT INTO Family " +
                "VALUES (%d, %d, \"%s\");", relationid, personid, personStatus);

        try {
            Connection conn = this.getConn();
            Statement stmt  = conn.createStatement();
            stmt.addBatch(query1);
            stmt.addBatch(query2);

            stmt.executeBatch();

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
