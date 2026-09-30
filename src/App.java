
import java.sql.*;

public class App {

    public static void main(String[] args) {

        // Database details
        String url = "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "root";

        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection con = DriverManager.getConnection(
                    url,
                    username,
                    password);

            System.out.println("Connected to MySQL successfully!");

            // Step 3: Insert data using PreparedStatement
            String insertQuery = "INSERT INTO student (id, name, branch) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insertQuery);

            // First record
            ps.setInt(1, 101);
            ps.setString(2, "Rahul");
            ps.setString(3, "CSE");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Inserted");
            }

            // Step 4: Insert another record
            ps.setInt(1, 102);
            ps.setString(2, "Amit");
            ps.setString(3, "IT");
            ps.executeUpdate();

            // Step 5: Insert another record
            ps.setInt(1, 103);
            ps.setString(2, "Neha");
            ps.setString(3, "ECE");
            ps.executeUpdate();

            // Close PreparedStatement
            ps.close();

            // Step 6: Create Statement for SELECT
            Statement st = con.createStatement();

            // Step 7: Execute SELECT query
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM student");

            System.out.println("\nStudent Records:");
            System.out.println("-------------------------");

            // Step 8: Read ResultSet
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("branch");

                System.out.println(
                        id + " " + name + " " + branch);
            }

            // Step 9: Close resources
            rs.close();
            st.close();
            con.close();

            System.out.println("-------------------------");
            System.out.println("Connection closed.");

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "MySQL JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println(
                    "Database Error!");
            e.printStackTrace();
        }
    }
}
