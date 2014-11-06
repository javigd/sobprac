/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

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
public class badCharsUrlTest {
    
    public badCharsUrlTest() {
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
    public void weirdCharsTest() {
        String shortUrl = "e86MSP2CTWk";
        String badShortUrl = "_e86MSP2CTWk";
        
        assertFalse(containsWeirdChars(shortUrl));
        assertTrue(containsWeirdChars(badShortUrl));
    }
    
    private boolean containsWeirdChars(String str) {
        str = str.replaceAll("[a-zA-Z0-9]", "");
        System.out.println(str);
        return str.length() > 0;
    }
}
