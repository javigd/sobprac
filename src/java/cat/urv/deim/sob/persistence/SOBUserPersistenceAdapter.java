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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javigd
 */
public class SOBUserPersistenceAdapter implements SOBUserAdapter {

    private static final Logger logger = Logger.getLogger(SOBUserPersistenceAdapter.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "test";
    private final EntityManagerFactory factory;
    private final UserDAO userDAO;

    public SOBUserPersistenceAdapter() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        userDAO = new SOBUserDAO(factory);
    }

    @Override
    public void newUser(SOBUser user) throws SOBException {
        // Save user in DB:
        this.userDAO.add(user);
    }

    @Override
    public Integer numberUsers() {
        int nusers = 0;

        try {
            // Save user in DB:
            nusers = this.userDAO.getNUsers();
        } catch (SOBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return nusers;
    }

}
