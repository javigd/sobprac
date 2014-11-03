/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.db.SOBUrlDAO;
import cat.urv.deim.sob.db.UrlDAO;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;

/**
 *
 * @author Max
 */
public class SOBUrlHandler implements IUrlHandler {

    private final UrlDAO urlDAO;

    public SOBUrlHandler(ConnectionPool pool) {
        super ();
        urlDAO = new SOBUrlDAO (pool);
    }
    
    @Override
    public String newUrl(SOBUrl url) throws SOBException {
        //TODO: Treat URL - compute ID - data in DB:
        urlDAO.add(url);
        return ("");
        
    }
    
    
}