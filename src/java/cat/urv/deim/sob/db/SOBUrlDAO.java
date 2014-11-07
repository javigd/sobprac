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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.shortUrl = :shortUrl");
        q.setParameter("shortUrl", url.getShortUrl());
        // URL does not exist in DB
        if (q.getResultList().isEmpty()) {
            //Add new URL to the database
            em.getTransaction().begin();
            em.persist(url);
            em.getTransaction().commit();
            em.close();
            return url;
        } else {
            //Throw new DAOException otherwise
            throw new SOBException(SOBError.URL_ALREADY_SHORTENED);

        }
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

    @Override
    public SOBUrl getUrlFromShort(String shortUrlId) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        //Simple query to get a URL from de DB given his ID
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.shortUrl = :urlId");
        q.setParameter("urlId", shortUrlId);
        try {
            SOBUrl url = (SOBUrl) q.getSingleResult();
            em.close();
            return (url);
        } catch (NoResultException e) {
            em.close();
            throw new SOBException(SOBError.BAD_GOSHORT_URL);
        }
    }

    @Override
    public SOBUrl updateVisits(SOBUrl url) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        // Look for the table object and update it
        SOBUrl myUrl = (SOBUrl) em.find(SOBUrl.class, url.getId());
        em.getTransaction().begin();
        myUrl.setNvisits(url.getNvisits());
        em.getTransaction().commit();
        em.close();

        return url;
    }
}
