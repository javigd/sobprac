/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.db.SOBUserDAO;
import cat.urv.deim.sob.db.UserDAO;
import cat.urv.deim.sob.exceptions.DAOException;
import cat.urv.deim.sob.models.SOBUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author javigd
 */
public class DatabaseSOBHandler implements SOBHandler {

    private static final Logger logger = Logger.getLogger(DatabaseSOBHandler.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "test";
    private EntityManagerFactory factory;
    private UserDAO userDAO;

    public DatabaseSOBHandler() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        userDAO = new SOBUserDAO(factory);
    }

    @Override
    public void newUser(SOBUser user) {
        try {
            // Save user in DB:
            this.userDAO.add(user);
        } catch (DAOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
