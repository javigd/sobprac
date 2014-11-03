/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.exceptions.SOBException;

/**
 *
 * @author javigd
 */
public interface UserDAO {
    
    public SOBUser get(String email) throws SOBException;
    
    public void add(SOBUser user) throws SOBException;
    
    public Integer getNUsers() throws SOBException;
    
}
