/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.SOBPersistenceHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author javigd
 */
public class PersistenceTest {
    
    public PersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        SOBPersistenceHandler dbHandler = new SOBPersistenceHandler();
        SOBUser testUser = new SOBUser(null, "Javier", "Garcia", "javi@urv.cat", "123456789");
        dbHandler.newUser(testUser);
    }
}
