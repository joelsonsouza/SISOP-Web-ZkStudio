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
import model.TblRegmov;
import model.TblRoots;

/**
 *
 * @author joels
 */
public class TblRegmovJpaController implements Serializable {

    public TblRegmovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblRegmov tblRegmov) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblRegmov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblRegmov tblRegmov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblRegmov = em.merge(tblRegmov);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblRegmov.getIdRegmov();
                if (findTblRegmov(id) == null) {
                    throw new NonexistentEntityException("The tblRegmov with id " + id + " no longer exists.");
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
            TblRegmov tblRegmov;
            try {
                tblRegmov = em.getReference(TblRegmov.class, id);
                tblRegmov.getIdRegmov();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblRegmov with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblRegmov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblRegmov> findTblRegmovEntities() {
        return findTblRegmovEntities(true, -1, -1);
    }

    public List<TblRegmov> findTblRegmovEntities(int maxResults, int firstResult) {
        return findTblRegmovEntities(false, maxResults, firstResult);
    }

    private List<TblRegmov> findTblRegmovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblRegmov.class));
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

    public TblRegmov findTblRegmov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblRegmov.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblRegmovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblRegmov> rt = cq.from(TblRegmov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TblRegmov> find(String pesqMes,  String pesqTip, String pesqDet) {
         String jpql = "select f from TblRegmov f where "
                + "f.mes like :mes and f.idTipo.descricaotipo like :dtipo and f.detalhamento like :det"; //
        Query q = getEntityManager().createQuery(jpql);
        
        q.setParameter("mes", "%"+ pesqMes +"%");
        q.setParameter("dtipo", "%"+ (String.valueOf(pesqTip)+"%"));
        q.setParameter("det", "%"+ pesqDet+"%") ;
        
        

        return q.getResultList();
    }
    
}

