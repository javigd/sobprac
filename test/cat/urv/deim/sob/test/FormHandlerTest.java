/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.handlers.FormHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author javigd
 */
public class FormHandlerTest {

    public FormHandlerTest() {
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

    @Test
    public void emptyFormHandlerTest() {
        FormHandler fh = new FormHandler();
        try {
            fh.validate();
        } catch (SOBException ex) {
            // Check that incomplete fields will be triggered on validation
            assertTrue(ex.getError().equals(SOBError.INCOMPLETE_FIELDS));
        }
    }

    @Test
    public void incompleteFormHandlerTest() {
        FormHandler fh = new FormHandler();

        // Set only fields username and email
        fh.setEmail("myemail@email.com");
        fh.setUsername("myusername");

        try {
            fh.validate();
        } catch (SOBException ex) {
            // Check that incomplete fields will be triggered on validation
            assertTrue(ex.getError().equals(SOBError.INCOMPLETE_FIELDS));
        }
    }

    @Test
    public void completedFormHandlerTest() {
        FormHandler fh = new FormHandler();

        // Set all required fields
        fh.setEmail("myemail@email.com");
        fh.setUsername("myusername");
        fh.setPassword("mypass");
        fh.setPasswordRepeat("mypass");

        try {
            assertTrue(fh.validate());
        } catch (SOBException ex) {
            Logger.getLogger(FormHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void passwordCheckFormHandlerTest() {
        FormHandler fh = new FormHandler();

        // Set different fields for password and passwordRepeat
        fh.setEmail("myemail@email.com");
        fh.setUsername("myusername");
        fh.setPassword("mypass");
        fh.setPasswordRepeat("anotherpass");

        try {
            fh.validate();
        } catch (SOBException ex) {
            // Check that incomplete fields will be triggered on validation
            assertTrue(ex.getError().equals(SOBError.REPEAT_PASSWORD));
        }
    }

    @Test
    public void passwordEncryptionFormHandlerTest() {
        FormHandler fh = new FormHandler();

        fh.setEmail("myemail@email.com");
        fh.setUsername("myusername");
        fh.setPassword("mypass");
        fh.setPasswordRepeat("mypass");
        
        try {
            fh.validate();
        } catch (SOBException ex) {
            Logger.getLogger(FormHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Encrypt our password
        fh.encryptPassword();
        System.out.println("Password (" + fh.getPassword() + ") has been encrypted and produced the output: " + new String(fh.getEncryptedPassword()));
    }

}
