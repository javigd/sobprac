/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import java.util.concurrent.atomic.LongAccumulator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Max
 */
public class SOBUrlDAO extends SOBPersistence implements UrlDAO {

    public SOBUrlDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public SOBUrl get(String id) throws SOBException {
        EntityManager em = factory.createEntityManager();
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
    public void add(SOBUrl url) throws SOBException {
        if (!url.isValid()) {
            throw new SOBException(SOBError.URL_NOT_VALID);
        }
        //Create a new Entity Manager
        EntityManager em = factory.createEntityManager();
        //Execute a query to make sore that the new URL has not be saved already
        Query q = em.createQuery("SELECT url FROM SOBUrl url WHERE url.longUrl = :longUrl");
        q.setParameter("longUrl", url.getLongUrl());
        // URL does not exist in DB
        if (q.getResultList().isEmpty()) {
            //Add new URL to the database
            em.getTransaction().begin();
            em.persist(url);
            em.getTransaction().commit();
            em.close();
        } else {
            //Throw new DAOException otherwise
            throw new SOBException(SOBError.URL_ALREADY_SHORTERED);
                    
        }
    }

}
