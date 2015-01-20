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
import cat.urv.deim.sob.util.Config;
import java.util.Calendar;
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
    public SOBUser add(SOBUser user) throws SOBException {
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
//            em.getTransaction().begin();
            em.persist(user);
//            em.getTransaction().commit();
//            em.close();
        } else {
            // Throw new DAOException otherwise
            throw new SOBException(SOBError.USER_ALREADY_EXISTS);
        }
        return user;
    }

    @Override
    public Integer getNUsers() throws SOBException {
        EntityManager em = pool.getConnection();
        // Perform a simple query for all the SOBUser entities
        Query q = em.createQuery("SELECT u FROM SOBUser u");
        // Get number of results produced by the query and return
        int nUsers = q.getResultList().size();
        em.close();
        return nUsers;
    }

    @Override
    public SOBUser setResetPassTicket(String email, Long ticket) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        // Execute a query to get the user id
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.email = :email");
        // Users will be referenced by email
        q.setParameter("email", email);
        try {
            // Look for the table object and update the new values
            SOBUser u = (SOBUser) q.getSingleResult();
            SOBUser usr = (SOBUser) em.find(SOBUser.class, u.getId());
            em.getTransaction().begin();
            // set ticket generated value
            usr.setResetTicket(ticket.toString());
            // create a java calendar instance
            Calendar calendar = Calendar.getInstance();
            // get a java date (java.util.Date) from the Calendar instance.
            // this java date will represent the current date, or "now".
            java.util.Date currentDate = calendar.getTime();
            // set ticket emission
            usr.setTicketEmission(currentDate);
            em.getTransaction().commit();
            em.close();
            return usr;
        } catch (NoResultException e) {
            throw new SOBException(SOBError.USER_NOT_FOUND);
        }
    }

    @Override
    public SOBUser checkResetTicket(Long userId, String ticket, String newPassword) throws SOBException {
        //Create a new Entity Manager
        EntityManager em = pool.getConnection();
        // Execute a query to get the user
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.id = :userId");
        // User will be referenced by id
        q.setParameter("userId", userId);
        try {
            // Get the user
            SOBUser usr = (SOBUser) q.getSingleResult();
            // Check the ticket availability and validate it
            Long ticketEmission = usr.getTicketEmission().getTime();
            if (!usr.getResetTicket().equals(ticket)
                    || System.currentTimeMillis() - ticketEmission > Config.MAX_TICKET_TIME) {
                throw new SOBException(SOBError.TICKET_NOT_VALID);
            }
            // Delete all track of the ticket that has been used and change password
            em.getTransaction().begin();
            usr.setResetTicket("");
            usr.setTicketEmission(null);
            usr.setPassword(newPassword);
            em.getTransaction().commit();
            em.close();
            return usr;
        } catch (NoResultException | NullPointerException e) {
            throw new SOBException(SOBError.TICKET_NOT_VALID);
        }
    }

    @Override
    public SOBUser getUserByEmail(String email) throws SOBException {
        EntityManager em = pool.getConnection();
        // Perform a simple query for all the SOBUser entities
        Query q = em.createQuery("SELECT u FROM SOBUser u WHERE u.email = :userEmail");
        // User will be referenced by id
        q.setParameter("userEmail", email);
        try {
            // Look for the user in Database
            SOBUser u = (SOBUser) q.getSingleResult();
            em.close();
            return u;
        } catch (NoResultException e) {
            em.close();
            throw new SOBException(SOBError.USER_NOT_FOUND);
        }
    }
}
