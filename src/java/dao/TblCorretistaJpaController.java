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
import model.TblCorretista;

/**
 *
 * @author joels
 */
public class TblCorretistaJpaController implements Serializable {

    public TblCorretistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblCorretista tblCorretista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblCorretista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblCorretista tblCorretista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblCorretista = em.merge(tblCorretista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblCorretista.getIdCorretista();
                if (findTblCorretista(id) == null) {
                    throw new NonexistentEntityException("The tblCorretista with id " + id + " no longer exists.");
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
            TblCorretista tblCorretista;
            try {
                tblCorretista = em.getReference(TblCorretista.class, id);
                tblCorretista.getIdCorretista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblCorretista with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblCorretista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblCorretista> findTblCorretistaEntities() {
        return findTblCorretistaEntities(true, -1, -1);
    }

    public List<TblCorretista> findTblCorretistaEntities(int maxResults, int firstResult) {
        return findTblCorretistaEntities(false, maxResults, firstResult);
    }

    private List<TblCorretista> findTblCorretistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblCorretista.class));
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

    public TblCorretista findTblCorretista(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblCorretista.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblCorretistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblCorretista> rt = cq.from(TblCorretista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
