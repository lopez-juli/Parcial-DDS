package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConeccionaBD {
    
    public static Connection initDb() throws SQLException {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/parcial";
            String username = "HOLA";
            String password = "****";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;
        } catch (Error error) {
            System.out.println(error);
        }
        return null;
    }
}
