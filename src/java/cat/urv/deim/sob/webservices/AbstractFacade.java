/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.webservices;

import cat.urv.deim.sob.models.SOBUrl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

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

    public void create(T entity) {
        getEntityManager().persist(entity);
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

    public void visit(Long id) {
        SOBUrl u = (SOBUrl) getEntityManager().find(entityClass, id);
        Long nvisits = u.getNvisits() + 1;
        u.setNvisits(nvisits);
        getEntityManager().merge(u);
    }

    public List<SOBUrl> findUrlsByUser(Long userId) {
        Query query = getEntityManager().createQuery("select u from SOBUrl u where u.userId = :userId", SOBUrl.class);
        query.setParameter("userId", userId);
        return (List<SOBUrl>) query.getResultList();
    }
    
    public String findByShort(String shortUrl) {
        Query query = getEntityManager().createQuery("select u from SOBUrl u where u.shortUrl = :shortUrl", SOBUrl.class);
        query.setParameter("shortUrl", shortUrl);
        try {
            SOBUrl myUrl = (SOBUrl) query.getSingleResult();
            return myUrl.getLongUrl();
        } catch (NonUniqueResultException | NoResultException e) {
            return "";
        }
    }

}