/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.exceptions.DAOException;
import cat.urv.deim.sob.models.SOBUser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author javigd
 */
public class SOBUserDAO extends SOBDAO implements UserDAO {

    public SOBUserDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public SOBUser get(String id) throws DAOException {
        EntityManager em = factory.createEntityManager();
        // Simple query to get a user from the DB given his ID
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.id = :id");
        q.setParameter("id", id);
        SOBUser u = (SOBUser) q.getSingleResult();
        if (u == null) {
            // Trigger DAOException USER_NOT_FOUND if no user has been found with the given id 
            throw new DAOException(DAOError.USER_NOT_FOUND);
        }
        return u;
    }

    @Override
    public void add(SOBUser user) throws DAOException {
        // Create a new EntityManager
        EntityManager em = factory.createEntityManager();
        // Execute a query to make sure that the new user has not signed up already
        //Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.email = :email");
        // Users will be referenced by email
        //q.setParameter("email", user.getEmail());
        SOBUser u = null; //(SOBUser) q.getSingleResult();
        //TODO: Previous line throws weird same-class cast exception [review]
        // Email does not exist in DB
        if (u == null) {
            // Add new user to database
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } else {
            // Throw new DAOException otherwise
            throw new DAOException(DAOError.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public Integer getNUsers() throws DAOException {
        EntityManager em = factory.createEntityManager();
        // Perform a simple query for all the SOBUser entities
        Query q = em.createQuery("select u from SOBUser u");
        // Get number of results produced by the query and return
        int nUsers = q.getResultList().size();
        em.close();
        return nUsers;
    }

}
