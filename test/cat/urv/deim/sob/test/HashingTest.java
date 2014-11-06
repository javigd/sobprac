/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.ByteUtils;
import cat.urv.deim.sob.util.URLConverter;
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
public class HashingTest {

    public HashingTest() {
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
    public void simpleHashScenario() {
        String userId = "123";
        String longUrl = "http://www.urbandictionary.com/define.php?term=asdasd";

        try {
            Long hashVal = URLConverter.getCombinedHashValue(longUrl, userId);
            System.out.println("Long output from resulting hash: " + hashVal);
        } catch (SOBException ex) {
            Logger.getLogger(HashingTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void trimTest() {
        String longUrl = "http://www.urbandictionary.com/define.php?term=asdasd";

        byte[] urlbytes = longUrl.getBytes();
        System.out.println("Before trim: " + urlbytes);
        byte[] trim = ByteUtils.trimByteArray(urlbytes, 25);
        String trimmed = new String(trim);
        System.out.println("After trim: " + trimmed);

    }

    @Test
    public void equalsHashScenario() {
        Long hashVal = null;
        Long hashVal2 = null;
        String userId = "123";
        String longUrl = "http://www.urbandictionary.com/define.php?term=asdasd";

        try {
            hashVal = URLConverter.getCombinedHashValue(longUrl, userId);
            hashVal2 = URLConverter.getCombinedHashValue(longUrl, userId);
            System.out.println("Long output from resulting hash: " + hashVal);
            System.out.println("Long output from resulting hash: " + hashVal2);
        } catch (SOBException ex) {
            Logger.getLogger(HashingTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(hashVal, hashVal2);
    }

    @Test
    public void sameUrlDifUsersHashScenario() {
        Long hashVal = null;
        Long hashVal2 = null;
        String userId = "123";
        String userId2 = "124";
        String longUrl = "http://www.urbandictionary.com/define.php?term=asdasd";

        try {
            hashVal = URLConverter.getCombinedHashValue(longUrl, userId);
            hashVal2 = URLConverter.getCombinedHashValue(longUrl, userId2);
            System.out.println("Long output from resulting hash: " + hashVal);
            System.out.println("Long output from resulting hash: " + hashVal2);
        } catch (SOBException ex) {
            Logger.getLogger(HashingTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertFalse(hashVal.equals(hashVal2));
    }
    
    @Test
    public void sameUsersDifUrlHashScenario() {
        Long hashVal = null;
        Long hashVal2 = null;
        String userId = "123";
        String longUrl = "http://www.urbandictionary.com/define.php?term=asdasd";
        String longUrl2 = "http://www.urbandictionary.com/define.php?term=holyshit";

        try {
            hashVal = URLConverter.getCombinedHashValue(longUrl, userId);
            hashVal2 = URLConverter.getCombinedHashValue(longUrl2, userId);
            System.out.println("Long output from resulting hash: " + hashVal);
            System.out.println("Long output from resulting hash: " + hashVal2);
        } catch (SOBException ex) {
            Logger.getLogger(HashingTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertFalse(hashVal.equals(hashVal2));
    }
}
