/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.util;

/**
 *
 * @author javigd
 */
public class Config {
    
    /* Persistence configuration parameters */
    /* NOTE that the following Config parameters match those defined in META-INF/persistence.xml file */
    public static final String PERSISTENCE_UNIT_NAME = "test";
    public static final String TRANSACTION_TYPE = "JPA";
    public static final String DATA_SOURCE = "jdbc/sample";
    
    /* Server shortened-url prefix */
    public static final String SERVER_PREFIX = "http://gosho.rt/";
    
}
