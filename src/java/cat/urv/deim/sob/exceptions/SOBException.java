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
public class SOBException extends Exception {

    private static final long serialVersionUID = 1L;
    private SOBError error;

    /**
     * Construct a SOBException with a detail message provided by a given Error
     *
     * @param error
     */
    public SOBException(SOBError error) {
        super(error.getMessage());
        this.error = error;
    }

    public SOBException(Exception e, SOBError error) {
        super(e);
        this.error = error;
    }

    public SOBException(String message) {
        super(message);
    }

    public SOBError getError() {
        return this.error;
    }

}
