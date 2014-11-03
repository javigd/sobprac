/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.ConnectionPool;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author javigd
 */
public class SOBUserDAO extends SOBPersistence implements UserDAO {

    public SOBUserDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public SOBUser get(String email) throws SOBException, NoResultException {
        // Get a new EntityManager (connection to database)
        EntityManager em = pool.getConnection();
        // Simple query to get a user from the DB given his ID
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.email = :email");
        q.setParameter("email", email);
        try {
            SOBUser u = (SOBUser) q.getSingleResult();
            em.close();
            return u;
        } catch (NoResultException e) {
            em.close();
            throw new SOBException(SOBError.USER_NOT_FOUND);
        }
    }

    @Override
    public void add(SOBUser user) throws SOBException {
        if (!user.isValid()) {
            throw new SOBException(SOBError.USER_NOT_VALID);
        }
        // Create a new EntityManager
        EntityManager em = pool.getConnection();
        // Execute a query to make sure that the new user has not signed up already
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.email = :email");
        // Users will be referenced by email
        q.setParameter("email", user.getEmail());
        // Email does not exist in DB
        if (q.getResultList().isEmpty()) {
            // Add new user to database
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } else {
            // Throw new DAOException otherwise
            throw new SOBException(SOBError.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public Integer getNUsers() throws SOBException {
        EntityManager em = pool.getConnection();
        // Perform a simple query for all the SOBUser entities
        Query q = em.createQuery("select u from SOBUser u");
        // Get number of results produced by the query and return
        int nUsers = q.getResultList().size();
        em.close();
        return nUsers;
    }

}
