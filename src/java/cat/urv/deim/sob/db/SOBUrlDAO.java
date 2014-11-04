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
import cat.urv.deim.sob.util.URLConverter;
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
    public SOBUrl get(String id) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        //Simple query to get a URL from de DB given his ID
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.id = :id");
        q.setParameter("id", id);
        SOBUrl url = (SOBUrl) q.getSingleResult();
        if (url == null) {
            // Trigger DAOException URL_Not_FOUND if no url has been found with the given url
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
                + "AND url.useremail = :usremail");
        q.setParameter("longUrl", url.getLongUrl());
        q.setParameter("usremail", url.getUseremail());
        // URL does not exist in DB
        if (q.getResultList().isEmpty()) {
            //Add new URL to the database
            em.getTransaction().begin();
            em.persist(url);
            em.flush();
            url.setShortUrl(URLConverter.convert(62, url.getId()));
            em.getTransaction().commit();
            em.close();
            return url;
        } else {
            //Throw new DAOException otherwise
            throw new SOBException(SOBError.URL_ALREADY_SHORTENED);
                    
        }
    }

}
