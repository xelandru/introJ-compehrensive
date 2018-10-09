package ch32;


import java.sql.*;

public class TestCallableStatement {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3307/javabook";
        String user = "root";
        String password = "root";
        Class.forName("com.mysql.jdbc.Driver");
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
        ) {
            CallableStatement callableStatement = connection.prepareCall("{? = call studentFound(?, ?)}");
            callableStatement.setString(2, "Jacob");
            callableStatement.setString(3, "Smith");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();

            if (callableStatement.getInt(1) > 0)
                System.out.println("User is in data base");
            else
                System.out.println("User not in database");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
