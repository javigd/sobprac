/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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

/**
 * WEB Services Unit Test class: NOTE THAT this class will be executable properly
 * if and only if either the REST service included in the project or the whole
 * project has been previously deployed successfully.
 * 
 * @author javigd
 */
public class WebServicesTest {

    public WebServicesTest() {
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
    public void shortenUrlTest() {
        String longUrl = "http://www.mylongurlweb.com/justanotherrandomurlpath";
        String userId = "myUserId";
        
        System.out.println("[shortenUrlTest] Long URL: " + longUrl);

        /* Call the REST web service method in order to shorten the long URL */
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/sobpracsvces/webresources/shorten?longUrl=" + longUrl + "&userId=" + userId);
        String shortenedURL = target.request(MediaType.TEXT_PLAIN).get(String.class);
        
        System.out.println("[shortenUrlTest] Short URL id computed: " + shortenedURL);
    }
    
    @Test
    public void shortenBadUrlTest() {
        String longUrl = "www.notlongenough.com";
        String userId = "myUserId";
        
        System.out.println("[shortenBadUrlTest] Long URL: " + longUrl);

        /* Call the REST web service method in order to shorten the long URL */
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/sobpracsvces/webresources/shorten?longUrl=" + longUrl + "&userId=" + userId);
        String shortenedURL = target.request(MediaType.TEXT_PLAIN).get(String.class);
        
        System.out.println("[shortenBadUrlTest] Short URL id computed: " + shortenedURL);
    }
    
}
