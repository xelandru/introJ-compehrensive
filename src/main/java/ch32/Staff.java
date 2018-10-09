package ch32;


import java.sql.*;

public class Staff {

    private static final String className = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3307/javabook";
    private static final String user = "root";
    private static final String password = "root";


    public static void viewRecord(String id) {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Staff WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstName = resultSet.getString(1);
                String mi = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String city = resultSet.getString(5);
                String state = resultSet.getString(6);
                String telephone = resultSet.getString(7);
                String email = resultSet.getString(8);
                System.out.println(firstName + " " + mi + " " + lastName + " " + address + " " +
                        city + " " + state + " " + telephone + " " + email);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertRecord(Record record) {

        String sql = "INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, record.getId());
            statement.setString(2, record.getLastName());
            statement.setString(3, record.getFirstName());
            statement.setString(4, record.getMi());
            statement.setString(5, record.getAddress());
            statement.setString(6, record.getCity());
            statement.setString(7, record.getState());
            statement.setString(8, record.getTelephone());
            statement.setString(9, record.getEmail());

            if (statement.executeUpdate() == 1)
                System.out.println("User added to the database");
            else
                System.out.println("User NOT added to the database");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void UpdateRecord(Record record) {

        String sql = "UPDATE Staff SET " +
                "lastName   = ?, " +
                "firstName  = ?, " +
                "mi         = ?, " +
                "address    = ?, " +
                "city       = ?, " +
                "state      = ?, " +
                "telephone  = ?, " +
                "email      = ?  " +
                "WHERE id  = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, record.getLastName());
            statement.setString(2, record.getFirstName());
            statement.setString(3, record.getMi());
            statement.setString(4, record.getAddress());
            statement.setString(5, record.getCity());
            statement.setString(6, record.getState());
            statement.setString(7, record.getTelephone());
            statement.setString(8, record.getEmail());
            statement.setString(9, record.getId());

            if (statement.executeUpdate() == 1)
                System.out.println("User Updated to the database");
            else
                System.out.println("User NOT Updated to the database");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private static class Record {
        private String id;
        private String firstName;
        private String lastName;
        private String mi;
        private String address;
        private String city;
        private String state;
        private String telephone;
        private String email;

        public Record(String id, String firstName, String lastName, String mi, String address,
                      String city, String state, String telephone, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.mi = mi;
            this.address = address;
            this.city = city;
            this.state = state;
            this.telephone = telephone;
            this.email = email;
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

        public String getMi() {
            return mi;
        }

        public void setMi(String mi) {
            this.mi = mi;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName(className);

        Staff.viewRecord("4");
        Staff.UpdateRecord(new Record("4", "Constantin", "Nastasia", "I",
                "Heracleea 4", "Babadag", "RO", "021562530", ""));
        Staff.viewRecord("4");


    }

}
