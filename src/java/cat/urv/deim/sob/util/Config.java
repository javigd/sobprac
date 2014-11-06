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
    public static final String SERVER_PREFIX = "http://goshort/";

    /* Controller default values */
    public static final String DEFAULT_OP_LOGIN = "logininit";
    public static final String DEFAULT_OP_SIGNUP = "signupinit";
    public static final String DEFAULT_OP_URL = "newurlinit";
    public static final String DEFAULT_OP_INDEX = "index";
    public static final String DEFAULT_ACTION = DEFAULT_OP_INDEX;

    /* Default miscellaneous APP configuration values */
    public static final int DEFAULT_MIN_URL_LENGTH = 26;
    public static final int TRIM_BYTEARRAY_SIZE = 25;
    public static final int DEFAULT_CONVERT_BASE = 62;
    public static final int SESSION_MAX_TIME = 30 * 60;

}
