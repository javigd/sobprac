/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;

/**
 *
 * @author javigd
 */
public interface IUserHandler {

    public void doSignUp(SOBUser user) throws SOBException;
    
    public Integer numberUsers() throws SOBException;
    
    public SOBUser doLogin(SOBUser user) throws SOBException;
    
}
