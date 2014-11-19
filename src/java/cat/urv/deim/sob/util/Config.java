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

    /* Server Parameters */
    public static final String HOST = "smtp.gmail.com";
    public static final String SERVER_REDIR_PREFIX = "http://gosho/rt/";
    public static final String SERVER_PREFIX = "http://gosho/";
    // NOTE that this variable will have to be properly set when deployed in a real environment
    public static final String SERVER_MAIL_PREFIX = "https://localhost:8181/sobprac/";
    public static final String DEFAULT_MAIL_ADDR = "goshortnoreply@gmail.com";
    public static final String DEFAULT_MAIL_PASSWD = "$sobadmin$";
    public static final String DEFAULT_MAIL_PORT = "465";
    public static final String RESET_EMAIL_SUBJECT = "[goShort!] Reset your password now!";
    
    /* Controller default values */
    public static final String DEFAULT_OP_LOGIN = "login";
    public static final String DEFAULT_OP_SIGNUP = "signup";
    public static final String DEFAULT_OP_URL = "newurl";
    public static final String DEFAULT_OP_INDEX = "index";
    public static final String DEFAULT_OP_REDIRECT = "redir";
    public static final String DEFAULT_ACTION = DEFAULT_OP_INDEX;

    /* Default miscellaneous APP configuration values */
    public static final int DEFAULT_MIN_URL_LENGTH = 26;
    public static final int TRIM_BYTEARRAY_SIZE = 25;
    public static final int DEFAULT_CONVERT_BASE = 62;
    public static final int SESSION_MAX_TIME = 30 * 60;
    public static final int MIN_SHORTENED_URL_LENGTH = 3;
    public static final int MAX_SHORTENED_URL_LENGTH = 6;
    public static final int MAX_URL_DISPLAYED = 10;
    public static final int DEFAULT_URL_SET_SIZE = 10;
    public static final int DEFAULT_URL_PAGES = 5;

    // Five days will be the maximum default time for a reset pass ticket to be valid
    public static final long MAX_TICKET_TIME = 5 * 24 * 60 * 60 * 1000;

}
