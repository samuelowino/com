/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package SystemSecurity;

/**
 *
 * @author samuel owino
 */
import java.sql.DriverManager;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsersAuthentication {

    
    public static void main(String[] args) {
        System.err.println(getUserPasswords().get("samue@fgd.bnm"));
    }
    public static HashMap<String, String> getUserPasswords() {
        HashMap<String, String> userAuthenticationDetails = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppos", "root", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password,email FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userAuthenticationDetails.put(resultSet.getString("email"), resultSet.getString("password"));
            }
            return userAuthenticationDetails;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DB Connection Error" + e.getMessage());
            HashMap<String, String> noValueMap = new HashMap<>();
            noValueMap.put("No Value", "DB ERROR");
            return noValueMap;
        }
    }
}
