package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    public static Connection getConnection() {

        Properties props = DatabaseConfig.LoadProperties();

        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
