package service.AAADEVCRUD.Dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import service.AAADEVCRUD.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVCRUD.Entity.Facturacion;

/**
 *
 * @author umansilla
 */
public class FacturacionJpaController implements Serializable {

    public FacturacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturacion facturacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(facturacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturacion facturacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            facturacion = em.merge(facturacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturacion.getNumerodecuenta();
                if (findFacturacion(id) == null) {
                    throw new NonexistentEntityException("The facturacion with id " + id + " no longer exists.");
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
            Facturacion facturacion;
            try {
                facturacion = em.getReference(Facturacion.class, id);
                facturacion.getNumerodecuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(facturacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturacion> findFacturacionEntities() {
        return findFacturacionEntities(true, -1, -1);
    }

    public List<Facturacion> findFacturacionEntities(int maxResults, int firstResult) {
        return findFacturacionEntities(false, maxResults, firstResult);
    }

    private List<Facturacion> findFacturacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturacion.class));
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

    public Facturacion findFacturacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturacion> rt = cq.from(Facturacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
