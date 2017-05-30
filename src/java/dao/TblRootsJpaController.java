/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.TblPoupanca;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.TblRoots;

/**
 *
 * @author joels
 */
public class TblRootsJpaController implements Serializable {

    public TblRootsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblRoots tblRoots) {
        if (tblRoots.getTblPoupancaList() == null) {
            tblRoots.setTblPoupancaList(new ArrayList<TblPoupanca>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TblPoupanca> attachedTblPoupancaList = new ArrayList<TblPoupanca>();
            for (TblPoupanca tblPoupancaListTblPoupancaToAttach : tblRoots.getTblPoupancaList()) {
                tblPoupancaListTblPoupancaToAttach = em.getReference(tblPoupancaListTblPoupancaToAttach.getClass(), tblPoupancaListTblPoupancaToAttach.getIdPoupanca());
                attachedTblPoupancaList.add(tblPoupancaListTblPoupancaToAttach);
            }
            tblRoots.setTblPoupancaList(attachedTblPoupancaList);
            em.persist(tblRoots);
            for (TblPoupanca tblPoupancaListTblPoupanca : tblRoots.getTblPoupancaList()) {
                TblRoots oldPopidTipoOfTblPoupancaListTblPoupanca = tblPoupancaListTblPoupanca.getPopidTipo();
                tblPoupancaListTblPoupanca.setPopidTipo(tblRoots);
                tblPoupancaListTblPoupanca = em.merge(tblPoupancaListTblPoupanca);
                if (oldPopidTipoOfTblPoupancaListTblPoupanca != null) {
                    oldPopidTipoOfTblPoupancaListTblPoupanca.getTblPoupancaList().remove(tblPoupancaListTblPoupanca);
                    oldPopidTipoOfTblPoupancaListTblPoupanca = em.merge(oldPopidTipoOfTblPoupancaListTblPoupanca);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblRoots tblRoots) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblRoots persistentTblRoots = em.find(TblRoots.class, tblRoots.getIdTipo());
            List<TblPoupanca> tblPoupancaListOld = persistentTblRoots.getTblPoupancaList();
            List<TblPoupanca> tblPoupancaListNew = tblRoots.getTblPoupancaList();
            List<String> illegalOrphanMessages = null;
            for (TblPoupanca tblPoupancaListOldTblPoupanca : tblPoupancaListOld) {
                if (!tblPoupancaListNew.contains(tblPoupancaListOldTblPoupanca)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TblPoupanca " + tblPoupancaListOldTblPoupanca + " since its popidTipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TblPoupanca> attachedTblPoupancaListNew = new ArrayList<TblPoupanca>();
            for (TblPoupanca tblPoupancaListNewTblPoupancaToAttach : tblPoupancaListNew) {
                tblPoupancaListNewTblPoupancaToAttach = em.getReference(tblPoupancaListNewTblPoupancaToAttach.getClass(), tblPoupancaListNewTblPoupancaToAttach.getIdPoupanca());
                attachedTblPoupancaListNew.add(tblPoupancaListNewTblPoupancaToAttach);
            }
            tblPoupancaListNew = attachedTblPoupancaListNew;
            tblRoots.setTblPoupancaList(tblPoupancaListNew);
            tblRoots = em.merge(tblRoots);
            for (TblPoupanca tblPoupancaListNewTblPoupanca : tblPoupancaListNew) {
                if (!tblPoupancaListOld.contains(tblPoupancaListNewTblPoupanca)) {
                    TblRoots oldPopidTipoOfTblPoupancaListNewTblPoupanca = tblPoupancaListNewTblPoupanca.getPopidTipo();
                    tblPoupancaListNewTblPoupanca.setPopidTipo(tblRoots);
                    tblPoupancaListNewTblPoupanca = em.merge(tblPoupancaListNewTblPoupanca);
                    if (oldPopidTipoOfTblPoupancaListNewTblPoupanca != null && !oldPopidTipoOfTblPoupancaListNewTblPoupanca.equals(tblRoots)) {
                        oldPopidTipoOfTblPoupancaListNewTblPoupanca.getTblPoupancaList().remove(tblPoupancaListNewTblPoupanca);
                        oldPopidTipoOfTblPoupancaListNewTblPoupanca = em.merge(oldPopidTipoOfTblPoupancaListNewTblPoupanca);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblRoots.getIdTipo();
                if (findTblRoots(id) == null) {
                    throw new NonexistentEntityException("The tblRoots with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TblRoots tblRoots;
            try {
                tblRoots = em.getReference(TblRoots.class, id);
                tblRoots.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblRoots with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TblPoupanca> tblPoupancaListOrphanCheck = tblRoots.getTblPoupancaList();
            for (TblPoupanca tblPoupancaListOrphanCheckTblPoupanca : tblPoupancaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TblRoots (" + tblRoots + ") cannot be destroyed since the TblPoupanca " + tblPoupancaListOrphanCheckTblPoupanca + " in its tblPoupancaList field has a non-nullable popidTipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tblRoots);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblRoots> findTblRootsEntities() {
        return findTblRootsEntities(true, -1, -1);
    }

    public List<TblRoots> findTblRootsEntities(int maxResults, int firstResult) {
        return findTblRootsEntities(false, maxResults, firstResult);
    }

    private List<TblRoots> findTblRootsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblRoots.class));
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

    public TblRoots findTblRoots(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblRoots.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblRootsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblRoots> rt = cq.from(TblRoots.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TblRoots> find(String pesqTipo, String pesqDesc, String pesqPer ) {
        String jpql = "select f from TblRoots f where "
                + "f.tipo like :tipo and f.descricaotipo like :desc and f.periodo like :per";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("tipo", "%"+ pesqTipo +"%");
        q.setParameter("desc", "%"+ pesqDesc+"%") ;
        q.setParameter("per", "%"+ pesqPer+"%") ;
        
        
//        if(pesqTipo !=null){
//         q.setParameter("de", pesqTipo);
//        }else{
//            q.setParameter("de", "%");
//        }
       
        return q.getResultList();
                
    }
    
}
