/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import cat.urv.deim.sob.util.Config;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * WARNING. The following test class will only work when web app context provided.
 * Not implemented due to extra out-of-topic code required (on this course).
 * This testing class will hence be held only for code references.
 * 
 * @author javigd
 */
public class PersistenceTest {
    
    static EntityManagerFactory emf;
    static ConnectionPool pool;

    public PersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        pool = new ConnectionPool(false);
        try {
            pool.setEntityManagerFactory(emf);
        } catch (SOBException ex) {
            Logger.getLogger(ResetPassURLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Simple Database Scenario. To specify.
     */
    @Test
    public void simpleDatabaseTestScenario() {
        IUserHandler dbHandler = new SOBUserHandler(pool);
        SOBUser testUser = new SOBUser(null, "username", "javi@urv.cat", "password");
        try {
            dbHandler.doSignUp(testUser);
        } catch (SOBException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
