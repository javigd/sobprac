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

    public SOBUser doSignUp(SOBUser user) throws SOBException;

    public Integer numberUsers() throws SOBException;

    public SOBUser doLogin(SOBUser user) throws SOBException;

    public SOBUser rememberPassword(String email) throws SOBException;

    public SOBUser resetPassword(String userId, String ticket, String newPassword) throws SOBException;

}
