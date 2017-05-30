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
import model.TblPoupanca;
import model.TblRegmov;
import model.TblRoots;

/**
 *
 * @author joels
 */
public class TblPoupancaJpaController implements Serializable {

    public TblPoupancaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblPoupanca tblPoupanca) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblRoots popidTipo = tblPoupanca.getPopidTipo();
            if (popidTipo != null) {
                popidTipo = em.getReference(popidTipo.getClass(), popidTipo.getIdTipo());
                tblPoupanca.setPopidTipo(popidTipo);
            }
            em.persist(tblPoupanca);
            if (popidTipo != null) {
                popidTipo.getTblPoupancaList().add(tblPoupanca);
                popidTipo = em.merge(popidTipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblPoupanca tblPoupanca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblPoupanca persistentTblPoupanca = em.find(TblPoupanca.class, tblPoupanca.getIdPoupanca());
            TblRoots popidTipoOld = persistentTblPoupanca.getPopidTipo();
            TblRoots popidTipoNew = tblPoupanca.getPopidTipo();
            if (popidTipoNew != null) {
                popidTipoNew = em.getReference(popidTipoNew.getClass(), popidTipoNew.getIdTipo());
                tblPoupanca.setPopidTipo(popidTipoNew);
            }
            tblPoupanca = em.merge(tblPoupanca);
            if (popidTipoOld != null && !popidTipoOld.equals(popidTipoNew)) {
                popidTipoOld.getTblPoupancaList().remove(tblPoupanca);
                popidTipoOld = em.merge(popidTipoOld);
            }
            if (popidTipoNew != null && !popidTipoNew.equals(popidTipoOld)) {
                popidTipoNew.getTblPoupancaList().add(tblPoupanca);
                popidTipoNew = em.merge(popidTipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblPoupanca.getIdPoupanca();
                if (findTblPoupanca(id) == null) {
                    throw new NonexistentEntityException("The tblPoupanca with id " + id + " no longer exists.");
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
            TblPoupanca tblPoupanca;
            try {
                tblPoupanca = em.getReference(TblPoupanca.class, id);
                tblPoupanca.getIdPoupanca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblPoupanca with id " + id + " no longer exists.", enfe);
            }
            TblRoots popidTipo = tblPoupanca.getPopidTipo();
            if (popidTipo != null) {
                popidTipo.getTblPoupancaList().remove(tblPoupanca);
                popidTipo = em.merge(popidTipo);
            }
            em.remove(tblPoupanca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblPoupanca> findTblPoupancaEntities() {
        return findTblPoupancaEntities(true, -1, -1);
    }

    public List<TblPoupanca> findTblPoupancaEntities(int maxResults, int firstResult) {
        return findTblPoupancaEntities(false, maxResults, firstResult);
    }

    private List<TblPoupanca> findTblPoupancaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblPoupanca.class));
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

    public TblPoupanca findTblPoupanca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblPoupanca.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblPoupancaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblPoupanca> rt = cq.from(TblPoupanca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<TblPoupanca> find(String pesqMes, String pesqTipo) {
        
        String jpql = "select f from TblPoupanca f where "
                + "f.popmes like :popmes and f.popidTipo.descricaotipo like :dtipo";
        Query q = getEntityManager().createQuery(jpql);

         q.setParameter("popmes", "%"+pesqMes+ "%");
       
         q.setParameter("dtipo", "%"+ (String.valueOf(pesqTipo)+"%"));
        
        return q.getResultList();
                
    }
    
}
