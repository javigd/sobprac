/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.Config;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javigd
 */
public class ConnectionPool {

    protected EntityManagerFactory factory;

    public ConnectionPool(boolean create) {
        if (create) {
            factory = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
            // Initialize persistence provider
            if (!factory.isOpen()) {
                factory.createEntityManager();
            }
        }
    }

    public EntityManager getConnection() throws SOBException {
        try {
            return factory.createEntityManager();
        } catch (IllegalStateException e) {
            closeConnectionFactory();
            throw new SOBException(SOBError.INTERNAL_SERVER_ERROR);
        }
    }

    public void closeConnectionFactory() throws SOBException {
        try {
            factory.close();
        } catch (IllegalStateException e) {
            throw new SOBException(SOBError.INTERNAL_PERSISTENCE_ERROR);
        }
    }

    public boolean isOpen() {
        return factory.isOpen();
    }

    /* Testing purposes */
    public void setEntityManagerFactory(EntityManagerFactory emf) throws SOBException {
        try {
            this.factory = emf;
        } catch (IllegalStateException e) {
            throw new SOBException(SOBError.INTERNAL_PERSISTENCE_ERROR);
        }
    }

}
