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
import cat.urv.deim.sob.util.URLConverter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class SOBUrlHandler implements IUrlHandler {

    private final UrlDAO urlDAO;

    public SOBUrlHandler(ConnectionPool pool) {
        super();
        urlDAO = new SOBUrlDAO(pool);
    }

    @Override
    public String newUrl(UrlBean url) throws SOBException {
        /* Make sure URL is long enough, raise exception notifying user otherwise */
        if (url.getLongUrl().length() < Config.DEFAULT_MIN_URL_LENGTH) {
            throw new SOBException(SOBError.URL_TOO_SHORT);
        }
        SOBUrl newUrl = new SOBUrl(null, url.getLongUrl(), url.getShortUrl(), url.getUserId(), 0L);
        // Validate this URL
        newUrl.validate();
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
        for (SOBUrl url : urls) {
            urlBeans.add(new UrlBean(url.getLongUrl(), url.getShortUrl(), null, url.getNvisits()));
        }
        return urlBeans;
    }

    @Override
    public String getShortenedUrl(UrlBean url) throws SOBException {
        String longUrl = url.getLongUrl();
        String userId = url.getUserId().toString();
        
        /* Make sure URL is long enough, raise exception notifying user otherwise */
        if (url.getLongUrl().length() < Config.DEFAULT_MIN_URL_LENGTH) {
            throw new SOBException(SOBError.URL_TOO_SHORT);
        }

        /* Get a Long value resulting from a combined hash between long URL and user ID */
        Long combinedHashVal = URLConverter.getCombinedHashValue(longUrl, userId);
        String shortenedUrl = URLConverter.convert(Config.DEFAULT_CONVERT_BASE, combinedHashVal);

        /* Make sure short URL provided is short enough, trim it otherwise */
        if(shortenedUrl.length() > Config.MAX_SHORTENED_URL_LENGTH) {
            shortenedUrl = shortenedUrl.substring(0, Config.MAX_SHORTENED_URL_LENGTH - 1);
        }
        
        return shortenedUrl;
    }

    @Override
    public String visit(String shortUrl) throws SOBException {
        shortUrl = shortUrl.replace("/", "");
        //get the long url
        SOBUrl url = urlDAO.getUrlFromShort(shortUrl);
        //update number of visits
        Long nvisits = url.getNvisits();
        nvisits+=1;
        url.setNvisits(nvisits);
        urlDAO.updateVisits(url);
        
        return url.getLongUrl();
    }

}
