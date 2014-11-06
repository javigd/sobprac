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
    USER_NOT_FOUND(400, "Sorry, the user could not be found. You must register first."),
    USER_ALREADY_EXISTS(409, "Sorry, this user already exists."),
    USER_NOT_VALID(410, "User not valid. Please check required fields."),
    
    // server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INTERNAL_PERSISTENCE_ERROR(500, "Attempt to close persistence factory failed."),
    
    //URL
    URL_NOT_FOUND (1, "URL not found."),
    URL_NOT_VALID (1, "URL already exists!"),
    URL_TOO_SHORT (1, "Sorry, your URL is not long enough to be shortened!"),
    URL_UNVALID (1, "URL not valid. Please check required fields."),
    URL_ALREADY_SHORTENED (1, "You have already shortened this URL"),
    URLS_NOT_LOADED(1, "Sorry, no URLs could be loaded. Try adding a new one!"),

    /* Utils Errors */
    BASE_NOT_SUPPORTED(500, "Base not Supported"),
    
    /* Client-side feedback Errors */
    REPEAT_PASSWORD(1, "The passwords you introduced do not match."),
    INCOMPLETE_FIELDS(1, "Please make sure you fill all required fields.");
    
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
