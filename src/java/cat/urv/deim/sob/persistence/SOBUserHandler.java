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
import java.util.Random;

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
    public SOBUser doSignUp(SOBUser user) throws SOBException {
        // Save user in DB:
        return this.userDAO.add(user);
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

    @Override
    public SOBUser rememberPassword(String email) throws SOBException {
        Random random = new Random();
        
        // Check parameters
        if(email == null){
            throw new SOBException(SOBError.USER_NOT_FOUND);
        }
        // Generate a ticket
        Long ticket = Math.abs(random.nextLong());
        
        // Save the generated ticket and return values back to Controller
        return userDAO.setResetPassTicket(email, ticket);
    }
    
    @Override
    public SOBUser resetPassword(String userId, String ticket, String newPassword) throws SOBException {
        // Forward this operation to the DAO so it executes the suitable tasks
        return userDAO.checkResetTicket(Long.parseLong(userId), ticket, newPassword);
    }

    @Override
    public SOBUser getUserByEmail(String email) throws SOBException {
        return userDAO.getUserByEmail(email);
    }
}
