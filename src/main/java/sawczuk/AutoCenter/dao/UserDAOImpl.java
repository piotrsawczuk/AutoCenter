package sawczuk.AutoCenter.dao;

import org.springframework.stereotype.Repository;
import sawczuk.AutoCenter.dbconnection.DBConnector;
import sawczuk.AutoCenter.exception.DatabaseException;
import sawczuk.AutoCenter.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public Long findIdByUsernameIgnoreCase(String username) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "select id from users where lower(username) = lower(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            Long id = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (id == null) {
                throw new DatabaseException("No user found with username " + username);
            }
            return id;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with username " + username, e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public User findByUsernameIgnoreCase(String username) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "select * from users where lower(username) = lower(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Long roleId = resultSet.getLong("role_id");
                Boolean active = resultSet.getBoolean("active");
                user = new User(id, username, password, roleId, active);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (user == null) {
                throw new DatabaseException("No user found with username: " + username);
            }
            return user;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with username: " + username, e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }
}
