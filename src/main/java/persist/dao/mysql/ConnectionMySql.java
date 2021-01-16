package persist.dao.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMySql {

    /**
     *
     */
    public static Connection connection;

    private String MYSQL_DB_HOST,MYSQL_DB_NAME,MYSQL_DB_PORT,MYSQL_DB_USERNAME,MYSQL_DB_PW;

    public ConnectionMySql(){
        getConnection();
    }

    public Connection getConnection() {
        if(connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();
            }

            System.out.println("MySQL JDBC Driver Registered!");
            connection = null;

            try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");) {

                Properties prop = new Properties();

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                MYSQL_DB_HOST = prop.getProperty("MYSQL_DB_HOST");
                MYSQL_DB_NAME = prop.getProperty("MYSQL_DB_NAME");
                MYSQL_DB_PORT = prop.getProperty("MYSQL_DB_PORT");
                MYSQL_DB_USERNAME = prop.getProperty("MYSQL_DB_USERNAME");
                MYSQL_DB_PW = prop.getProperty("MYSQL_DB_PW");


            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                StringBuilder connectionURL = new StringBuilder();
                connectionURL.append("jdbc:mysql://")
                        .append(MYSQL_DB_HOST).append(":").append(MYSQL_DB_PORT).append("/")
                        .append(MYSQL_DB_NAME);
                connection = DriverManager.
                        getConnection(connectionURL.toString(), MYSQL_DB_USERNAME, MYSQL_DB_PW);
            } catch (SQLException e) {
                System.out.println("Connection Failed!:\n" + e.getMessage());
            }

            if (connection != null) {
                System.out.println("SUCCESS!!!! You made it, take control your database now!");
            } else {
                System.out.println("FAILURE! Failed to make connection!");
            }
        }
        return connection;
    }

}
