/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.Config;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javigd
 */
public class ConnectionPool {
    protected final EntityManagerFactory factory;
    
    public ConnectionPool() {
        factory = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
    }
    
    public EntityManager getConnection() throws SOBException {
        try {
            return factory.createEntityManager();
        } catch (IllegalStateException e) {
            closeConnectionFactory();
            throw new SOBException(SOBError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public void closeConnectionFactory() {
        factory.close();
    }
    
}
