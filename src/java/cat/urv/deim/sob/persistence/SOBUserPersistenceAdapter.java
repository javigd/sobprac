/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.db.SOBUserDAO;
import cat.urv.deim.sob.db.UserDAO;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;

/**
 *
 * @author javigd
 */
public class SOBUserPersistenceAdapter extends PersistenceAdapter implements SOBUserAdapter {

    private final UserDAO userDAO;

    public SOBUserPersistenceAdapter() {
        super();
        userDAO = new SOBUserDAO(this.factory);
    }

    @Override
    public void newUser(SOBUser user) throws SOBException {
        // Save user in DB:
        this.userDAO.add(user);
    }

    @Override
    public Integer numberUsers() throws SOBException {
        // Get Number users from DB:
        return this.userDAO.getNUsers();

    }

}
