package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.user.UserExistException;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    /**
     * Returns user from database.
     *
     * @param lang current language
     * @param username user username
     * @param password user password
     *
     * @return {@link User}
     *
     * @throws DAOException if error in database occurred
     */
    User getUserInformation(String lang, String username, String password) throws DAOException;

    /**
     * Returns users from database.
     *
     * @param lang current language
     *
     * @return {@link List} of {@link User}
     *
     * @throws DAOException if error in database occurred
     */
    List<User> getUsers(String lang) throws DAOException;

    /**
     * Returns reasons ob blocking {@link User} in system from database.
     *
     * @param lang current language
     *
     * @return {@link Map} of reasons
     *
     * @throws DAOException if error in database occurred
     */
    Map<Integer, String> getReasons(String lang) throws DAOException;

    /**
     * Return current {@link User#discount}
     *
     * @param lang current language
     * @param id user id
     *
     * @return {@link User#discount}
     *
     * @throws DAOException if error in database occurred
     */
    int getUserDiscount(String lang, int id) throws DAOException;

    /**
     * Checks if the {@link User} exists in the database.
     *
     * @param lang current language
     * @param data new user data
     * @param bundle query bundle

     * @throws DAOException if error in database occurred
     * @throws UserExistException if user with this data exist in database
     */
    void checkUser(String lang, String data, String bundle) throws DAOException, UserExistException;

    /**
     * Adds new user to database.
     *
     * @param lang current language
     * @param username user username
     * @param password user password
     * @param name user name
     * @param lastName user last name
     * @param surname user surname
     * @param email user email

     * @throws DAOException if error in database occurred
     * @throws UserExistException if user with this data exist in database
     */
    void addUser(String lang, String username, String password, String name, String lastName, String surname, String email) throws DAOException, UserExistException;

    /**
     * Blocks {@link User} in database.
     *
     * @param id user id
     * @param reason block reason
     *
     * @throws DAOException if error in database occurred
     */
    void blockUser(int id, int reason) throws DAOException;

    /**
     * Unlocks {@link User} in database.
     *
     * @param id user id
     *
     * @throws DAOException if error in database occurred
     */
    void unlockUser(int id) throws DAOException;

    /**
     * Changes {@link User#discount} in database.
     *
     * @param userId user id
     * @param userDiscount new discount
     *
     * @throws DAOException if error in database occurred
     */
    void changeUserDiscount(int userId, int userDiscount) throws DAOException;

    /**
     * Changes {@link User} data in database.
     *
     * @param id user id
     * @param dataToUpdate new user data
     *
     * @throws DAOException if error in database occurred
     */
    void changeUserData(int id, List<String> dataToUpdate) throws DAOException;

    /**
     * Changes {@link User} password in database.
     *
     * @param id user id
     * @param password new password
     *
     * @throws DAOException if error in database occurred
     */
    void changePassword(int id, String password) throws DAOException;

    /**
     * Deletes {@link User} from database.
     *
     * @param id user id
     *
     * @throws DAOException if error in database occurred
     */
    void deleteUser(int id) throws  DAOException;
}