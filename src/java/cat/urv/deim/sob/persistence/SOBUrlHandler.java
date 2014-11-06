/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.beans.UrlBean;
import cat.urv.deim.sob.db.SOBUrlDAO;
import cat.urv.deim.sob.db.UrlDAO;
import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.util.Config;
import java.util.ArrayList;
import java.util.List;

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
    public String newUrl(UrlBean url) throws SOBException {
        /* Make sure URL is long enough, raise exception notifying user otherwise */
        if (url.getLongUrl().length() < Config.DEFAULT_MIN_URL_LENGTH) {
            throw new SOBException(SOBError.URL_TOO_SHORT);
        }
        SOBUrl newUrl = new SOBUrl(null, url.getLongUrl(), null, url.getUserId(), null);
        //Compute short URL and store it
        SOBUrl shortenedUrl = urlDAO.add(newUrl);
        return shortenedUrl.getShortUrl();
    }

    @Override
    public List<UrlBean> getUserUrls(String userId) throws SOBException {      
        Long id = Long.parseLong(userId);
        List<SOBUrl> urls = urlDAO.getUrlsByUserId(id);
        // Translate SOBUrl model objects to simple bean objects to be managed by the controller
        List<UrlBean> urlBeans = new ArrayList<UrlBean>();
        for(SOBUrl url : urls) {
            urlBeans.add(new UrlBean(url.getLongUrl(), url.getShortUrl(), null, url.getNvisits()));
        }
        return urlBeans;
    }

    @Override
    public String getShortenedUrl(UrlBean url) throws SOBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
