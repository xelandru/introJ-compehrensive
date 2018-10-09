package ch32;


import java.sql.*;

public class SimpleJdbc {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        final String sql = "SELECT firstName, mi, lastName FROM Student WHERE lastName = 'Smith'";

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javabook", "root", "root")

        ) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString(1) + " "
                        + result.getString(2) + " "
                        + result.getString(3));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
