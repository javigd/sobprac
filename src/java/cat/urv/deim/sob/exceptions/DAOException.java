/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.exceptions;

import cat.urv.deim.sob.db.DAOError;

/**
 *
 * @author javigd
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;
    private DAOError error;

    /**
     * Construct a DAOException with a detail message provided by a given DAOError
     *
     * @param error
     */
    public DAOException(DAOError error) {
        super(error.getMessage());
        this.error = error;
    }

    public DAOException(Exception e, DAOError error) {
        super(e);
        this.error = error;
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOError getError() {
        return this.error;
    }

}
