/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javigd
 */
public class PersistenceAdapter {
    protected static final String PERSISTENCE_UNIT_NAME = "test";
    protected final EntityManagerFactory factory;
    
    public PersistenceAdapter() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
}
