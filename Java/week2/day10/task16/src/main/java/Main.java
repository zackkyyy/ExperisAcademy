import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.select();
    }

    public void select(){
        String sql = "SELECT id, LastName, FirstName, City, Region, PostalCode, Country FROM Employee";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println("\nEmployees");
            System.out.printf("%s %20s %20s %20s %20s %20s %20s\n", "ID", "LastName", "FirstName", "City", "Region", "PostalCode", "Country");

            int id;
            String lastname;
            String firstname;
            String city;
            String region;
            String postalCode;
            String country;

            // loop through the result set
            while (rs.next()) {
                id = rs.getInt("id");
                lastname = rs.getString("LastName");
                firstname = rs.getString("FirstName");
                city = rs.getString("City");
                region = rs.getString("Region");
                postalCode = rs.getString("PostalCode");
                country = rs.getString("Country");

                System.out.printf("%s %20s %20s %20s %20s %20s %20s\n", id, lastname, firstname, city, region, postalCode, country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;

        try {
            // db parameters
            String url = "jdbc:sqlite:src\\main\\resources\\Northwind_small.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
