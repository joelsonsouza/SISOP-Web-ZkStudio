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
import model.TblAcessos;

/**
 *
 * @author joels
 */
public class TblAcessosJpaController implements Serializable {

    public TblAcessosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblAcessos tblAcessos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblAcessos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblAcessos tblAcessos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblAcessos = em.merge(tblAcessos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblAcessos.getIdAcesso();
                if (findTblAcessos(id) == null) {
                    throw new NonexistentEntityException("The tblAcessos with id " + id + " no longer exists.");
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
            TblAcessos tblAcessos;
            try {
                tblAcessos = em.getReference(TblAcessos.class, id);
                tblAcessos.getIdAcesso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblAcessos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblAcessos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblAcessos> findTblAcessosEntities() {
        return findTblAcessosEntities(true, -1, -1);
    }

    public List<TblAcessos> findTblAcessosEntities(int maxResults, int firstResult) {
        return findTblAcessosEntities(false, maxResults, firstResult);
    }

    private List<TblAcessos> findTblAcessosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblAcessos.class));
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

    public TblAcessos findTblAcessos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblAcessos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblAcessosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblAcessos> rt = cq.from(TblAcessos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
