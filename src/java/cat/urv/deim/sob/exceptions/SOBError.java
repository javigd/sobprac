/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.exceptions;

/**
 *
 * @author javigd
 */
public enum SOBError {

    /* DAO Errors Relate triggered errors to HTTP codes */
    // users
    USER_NOT_FOUND(400, "User not found."),
    USER_ALREADY_EXISTS(409, "User already exists!"),
    USER_NOT_VALID(410, "User not valid. Please check required fields."),
    
    // server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    
    //URL
    URL_NOT_FOUND (0, "URL not found."),
    URL_NOT_VALID (0, "URL already exists!"),
    URL_ALREADY_SHORTENED (0, "You have already shortened this URL"),

    /* Utils Errors */
    BASE_NOT_SUPPORTED(0, "Base not Supported"),
    
    /* Client-side feedback Errors */
    REPEAT_PASSWORD(0, "The passwords you introduced do not match."),
    INCOMPLETE_FIELDS(0, "Please make sure you fill all required fields.");
    
    private final int code;
    private final String message;

    SOBError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
