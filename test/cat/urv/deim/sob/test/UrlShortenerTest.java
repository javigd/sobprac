/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.URLConverter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author javigd
 */
public class UrlShortenerTest {
    
    public UrlShortenerTest() {
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
     public void shortenUrl() {
         Random random = new Random();
         int MAXGEN = 10;
         int BASE = 62;
         Long toConvert;
         String converted = "";
         
         System.out.println("Testing URL convert routine...");
         
         for(int i=0; i<MAXGEN; i++) {
             // get an unsigned Long
             toConvert = random.nextLong() & 0xffffffffL;
             try {
                 converted = URLConverter.convert(BASE, toConvert);
             } catch (SOBException ex) {
                 Logger.getLogger(UrlShortenerTest.class.getName()).log(Level.SEVERE, null, ex);
             }
             System.out.println("Converting value " + toConvert + " to base 62... \t" + converted);
         }
         
         System.out.println("DONE.");         
     }
}
