package by.tc.hostel_system.service.user;

import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * Returns user information from DAO layer if {@link User} exist.
     *
     * @param lang      current language
     * @param user      user
     * @param password  user password
     *
     * @return  {@link User} if it exist
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    User getUserInformation(String lang, Object user, String password) throws ServiceException;

    /**
     * Returns list of all {@link User} from DAO layer.
     *
     * @param lang  current language
     *
     * @return {@link List} of {@link User}
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    List<User> getUsers(String lang) throws ServiceException;

    /**
     * Returns list of blocking reasons from DAO layer.
     *
     * @param lang  current language
     *
     * @return {@link Map} of blocking reasons
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    Map<Integer, String> getReasons(String lang) throws ServiceException;

    /**
     * Returns {@link User#discount} from DAO layer.
     *
     * @param lang      current language
     * @param userId    user id
     *
     * @return current {@link User#discount}
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    int getUserDiscount(String lang, String userId) throws ServiceException;

    /**
     * Send user information to DAO layer to add {@link User} to database.
     *
     * @param lang      current language
     * @param username  user username
     * @param password  user password
     * @param name      user name
     * @param lastName  user last name
     * @param surname   user surname
     * @param email     user email
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void addUserInformation(String lang, String username, String password, String name, String lastName, String surname, String email) throws ServiceException;

    /**
     * Send information to DAO layer to block {@link User} in database.
     *
     * @param id        user id
     * @param reason    blocking reason
     * @param page      page number
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void blockUser(String id, String reason, String page) throws ServiceException;

    /**
     * Send information to DAO layer to unlock {@link User} in database.
     *
     * @param id    user id
     * @param page  page number
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void unlockUser(String id, String page) throws ServiceException;

    /**
     * Send new user data to DAO layer to change current {@link User} data in database.
     *
     * @param lang      current language
     * @param user      current user
     * @param username  new username
     * @param email     new email
     * @param name      new name
     * @param surname   new surname
     * @param lastName  new last name
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void changeUserData(String lang, User user, String username, String email, String name, String surname, String lastName) throws ServiceException;

    /**
     * Send new password to DAO layer to change current {@link User} password in database.
     *
     * @param id        current user id
     * @param password  new password
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void changePassword(int id, String password) throws ServiceException;

    /**
     * Send user id to DAO layer to delete that {@link User} from database.
     *
     * @param id    user id
     *
     * @throws ServiceException if error in DAO layer occurred
     */
    void deleteUser(int id) throws  ServiceException;

    /**
     * Send new discount to DAO layer to change current {@link User#discount} in database.
     *
     * @param userId        user id
     * @param userDiscount  new discount
     * @param sign          sign of coefficient ("+" if increase discount, "-" - otherwise)
     * @param page          page number
     *
     * @throws ServiceException if error in DAO layer occurred
     */
	void changeUserDiscount(int userId, int userDiscount, String sign, String page) throws ServiceException;
}