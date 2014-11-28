/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.exceptions;

import cat.urv.deim.sob.util.Config;

/**
 *
 * @author javigd
 */
public enum SOBError {

    /* DAO Errors Relate triggered errors to HTTP codes */
    // users
    USER_NOT_FOUND(400, "Sorry, the user could not be found."),
    USER_ALREADY_EXISTS(409, "Sorry, this user already exists."),
    USER_NOT_VALID(410, "User or password incorrect. Please check required fields."),
    TICKET_NOT_VALID(400, "Sorry, your password reset ticket is no longer valid. Try reset your password again."),
    
    // server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INTERNAL_PERSISTENCE_ERROR(500, "Attempt to close persistence factory failed."),
    
    //URL
    URL_NOT_FOUND(1, "URL not found."),
    URL_NOT_VALID(1, "URL already exists!"),
    URL_TOO_SHORT(1, "Sorry, your URL is not long enough to be shortened!"),
    URL_TOO_LONG(1, "Sorry, your URL can not be longer than " + Config.MAX_LONG_URL_LENGTH +" characters!"),
    SHORT_URL_TOO_SHORT(1, "Sorry, your short URL cannot be shorter than " + Config.MIN_SHORTENED_URL_LENGTH + " characters."),
    SHORT_URL_TOO_LONG(1, "Sorry, your short URL cannot be longer than " + Config.MAX_SHORTENED_URL_LENGTH + " characters."),
    SHORT_URL_BAD_CHARS(1, "Sorry, your short URL should only contain characters combining a-z, A-Z and 0-9!"),
    URL_UNVALID(1, "URL not valid. Please check required fields."),
    URL_ALREADY_SHORTENED(1, "Sorry, this goShort! URL already exists, try with a new one please."),
    URLS_NOT_LOADED(1, "You haven't shortened any URL yet, try adding a new one!"),
    BAD_GOSHORT_URL(1, "Sorry, the goShort! URL you tried to reach is either not available or no longer exists!"),
    INVALID_URL_PAGE(1, "The requested page contains no URLs"),
    
    /* Utils Errors */
    BASE_NOT_SUPPORTED(500, "Base not Supported"),
    
    /* Client-side specific feedback Errors */
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
