/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.db.SOBUrlDAO;
import cat.urv.deim.sob.db.UrlDAO;
import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.util.Config;

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
        /* Make sure URL is long enough, raise exception notifying user otherwise */
        if (url.getLongUrl().length() < Config.DEFAULT_MIN_URL_LENGTH) {
            throw new SOBException(SOBError.URL_TOO_SHORT);
        }
        //Compute short URL and store it
        SOBUrl shortenedUrl = urlDAO.add(url);
        return shortenedUrl.getShortUrl();
    }
    
    
}
