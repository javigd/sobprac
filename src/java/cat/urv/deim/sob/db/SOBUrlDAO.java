/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.util.Config;
import cat.urv.deim.sob.util.URLConverter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Max
 */
public class SOBUrlDAO extends SOBPersistence implements UrlDAO {

    public SOBUrlDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public SOBUrl get(Long id) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        //Simple query to get a URL from de DB given his ID
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.id = :id");
        q.setParameter("id", id);
        SOBUrl url = (SOBUrl) q.getSingleResult();
        if (url == null) {
            // Trigger SOBException URL_Not_FOUND if no url has been found with the given id
            throw new SOBException(SOBError.URL_NOT_FOUND);
        }
        em.close();
        return (url);
    }

    @Override
    public SOBUrl add(SOBUrl url) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        //Execute a query to make sore that the new URL has not be saved already
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.longUrl = :longUrl "
                + "AND url.userId = :userId");
        q.setParameter("longUrl", url.getLongUrl());
        q.setParameter("userId", url.getUserId());
        // URL does not exist in DB
        if (q.getResultList().isEmpty()) {
            //Initialize visits counter
            url.setNvisits(0L);
            //Add new URL to the database
            em.getTransaction().begin();
            em.persist(url);
            em.flush();
            // Shorten long URL using its unique ID assigned
            url.setShortUrl(URLConverter.convert(Config.DEFAULT_CONVERT_BASE, url.getId()));
            em.getTransaction().commit();
            em.close();
            return url;
        } else {
            //Throw new DAOException otherwise
            throw new SOBException(SOBError.URL_ALREADY_SHORTENED);

        }
    }

    @Override
    public void addVisit(Long urlId) throws SOBException {
        //TODO: Add +1 to nvisits field of a given url
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SOBUrl> getUrlsByUserId(Long userId) throws SOBException {
        List<SOBUrl> urlList = new ArrayList<SOBUrl>();
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        //Simple query to get a URL from de DB given his ID
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.userId = :id");
        q.setParameter("id", userId);
        if (!q.getResultList().isEmpty()) {
            for (SOBUrl url : (List<SOBUrl>) q.getResultList()) {
                urlList.add(url);
            }
        } else {
            // Trigger SOBException if no url has been found with the given user id
            throw new SOBException(SOBError.URL_NOT_FOUND);
        }
        em.close();
        return (urlList);
    }
}
