package sawczuk.AutoCenter.dao;

import sawczuk.AutoCenter.exception.DatabaseException;
import sawczuk.AutoCenter.model.User;

public interface UserDAO {

    Long findIdByUsernameIgnoreCase(String username) throws DatabaseException;

    User findByUsernameIgnoreCase(String username) throws DatabaseException;

}
