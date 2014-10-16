/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

/**
 *
 * @author javigd
 */
public enum DAOError {

    // DAOErrors Relate triggered errors to HTTP codes
    // users
    USER_NOT_FOUND(400, "User not found."),
    USER_ALREADY_EXISTS(409, "Internal Conflict. User already exists"),
    
    // server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    DAOError(int code, String message) {
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
