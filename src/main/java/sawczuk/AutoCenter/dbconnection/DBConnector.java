package sawczuk.AutoCenter.dbconnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sawczuk.AutoCenter.exception.DatabaseException;

import java.sql.*;

@Component
public class DBConnector {

    public static String driverClassName;
    public static String url;
    public static String username;
    public static String password;

    @Value("${spring.datasource.driver-class-name}")
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        this.url = url;
    }
    @Value("${spring.datasource.username}")
    public void setUsername(String username) {
        this.username = username;
    }
    @Value("${spring.datasource.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    public static Connection getConnection() throws DatabaseException {
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Problem with database driver. ", e);
        } catch (SQLException e) {
            throw new DatabaseException("Problem with database connection. ", e);
        }
        return connection;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            resultSet = null;
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            statement = null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            connection = null;
        }
    }

}