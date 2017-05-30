/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.TblContbank;

/**
 *
 * @author joels
 */
public class TblContbankJpaController implements Serializable {

    public TblContbankJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblContbank tblContbank) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblContbank);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblContbank tblContbank) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblContbank = em.merge(tblContbank);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblContbank.getIdContbank();
                if (findTblContbank(id) == null) {
                    throw new NonexistentEntityException("The tblContbank with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblContbank tblContbank;
            try {
                tblContbank = em.getReference(TblContbank.class, id);
                tblContbank.getIdContbank();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblContbank with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblContbank);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblContbank> findTblContbankEntities() {
        return findTblContbankEntities(true, -1, -1);
    }

    public List<TblContbank> findTblContbankEntities(int maxResults, int firstResult) {
        return findTblContbankEntities(false, maxResults, firstResult);
    }

    private List<TblContbank> findTblContbankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblContbank.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TblContbank findTblContbank(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblContbank.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblContbankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblContbank> rt = cq.from(TblContbank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
