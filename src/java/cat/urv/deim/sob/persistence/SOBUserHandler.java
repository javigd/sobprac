/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.db.SOBUserDAO;
import cat.urv.deim.sob.db.UserDAO;
import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;

/**
 *
 * @author javigd
 */
public class SOBUserHandler implements IUserHandler {

    private final UserDAO userDAO;

    public SOBUserHandler(ConnectionPool pool) {
        super();
        userDAO = new SOBUserDAO(pool);
    }

    @Override
    public void doSignUp(SOBUser user) throws SOBException {
        // Save user in DB:
        this.userDAO.add(user);
    }

    @Override
    public Integer numberUsers() throws SOBException {
        // Get Number users from DB:
        return this.userDAO.getNUsers();

    }

    @Override
    public SOBUser doLogin(SOBUser user) throws SOBException {
        SOBUser loggedUser = this.userDAO.get(user.getEmail());
        // Check that both password hash match
        if(!loggedUser.getPassword().equals(user.getPassword())) {
            throw new SOBException(SOBError.USER_NOT_VALID);
        }
        return loggedUser;
    }

}
