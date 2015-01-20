/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.util.Config;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author javigd
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public SOBUser setTicket(Long id, Long ticket) {
        SOBUser u = (SOBUser) getEntityManager().find(entityClass, id);
        u.setResetTicket(ticket.toString());
        // create a java calendar instance
        Calendar calendar = Calendar.getInstance();
        // get a java date (java.util.Date) from the Calendar instance.
        // this java date will represent the current date, or "now".
        java.util.Date currentDate = calendar.getTime();
        // set ticket emission
        u.setTicketEmission(currentDate);
        getEntityManager().merge(u);
        return u;
    }

    public SOBUser resetPassword(Long id, String ticket, String newPassword) {
        SOBUser u = (SOBUser) getEntityManager().find(entityClass, id);
        if (u.getResetTicket().equals(ticket)) {
            // Check the ticket availability and validate it
            Long ticketEmission = u.getTicketEmission().getTime();
            if (!u.getResetTicket().equals(ticket)
                    || System.currentTimeMillis() - ticketEmission > Config.MAX_TICKET_TIME) {
                return null;
            }
            u.setPassword(newPassword);
            u.setResetTicket("");
            u.setTicketEmission(null);
            getEntityManager().merge(u);
            return u;
        }
        return null;
    }

}
