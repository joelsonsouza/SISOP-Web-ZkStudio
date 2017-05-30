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
import model.TblDetdesc;

/**
 *
 * @author joels
 */
public class TblDetdescJpaController implements Serializable {

    public TblDetdescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblDetdesc tblDetdesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblDetdesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblDetdesc tblDetdesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblDetdesc = em.merge(tblDetdesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblDetdesc.getIdDet();
                if (findTblDetdesc(id) == null) {
                    throw new NonexistentEntityException("The tblDetdesc with id " + id + " no longer exists.");
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
            TblDetdesc tblDetdesc;
            try {
                tblDetdesc = em.getReference(TblDetdesc.class, id);
                tblDetdesc.getIdDet();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblDetdesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblDetdesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblDetdesc> findTblDetdescEntities() {
        return findTblDetdescEntities(true, -1, -1);
    }

    public List<TblDetdesc> findTblDetdescEntities(int maxResults, int firstResult) {
        return findTblDetdescEntities(false, maxResults, firstResult);
    }

    private List<TblDetdesc> findTblDetdescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblDetdesc.class));
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

    public TblDetdesc findTblDetdesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblDetdesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblDetdescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblDetdesc> rt = cq.from(TblDetdesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
