package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class for managing database connections.
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the database connection using properties from database.properties file.
     * Throws SQLException if connection initialization fails.
     */
    private DBConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (in == null) {
                throw new IOException("database.properties file not found in classpath");
            }
            props.load(in);
            this.url = props.getProperty("jdbc.url");
            this.username = props.getProperty("jdbc.username");
            this.password = props.getProperty("jdbc.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Returns the singleton instance of DBConnection.
     * If the instance is null or the connection is closed, a new instance is created.
     * @return The singleton instance of DBConnection.
     * @throws SQLException If connection initialization fails.
     */
    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Returns the current database connection.
     * @return The current database connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
