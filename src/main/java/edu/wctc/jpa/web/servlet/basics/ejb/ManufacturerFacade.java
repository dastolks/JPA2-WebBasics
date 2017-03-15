package edu.wctc.jpa.web.servlet.basics.ejb;

import edu.wctc.jpa.web.servlet.basics.model.Manufacturer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * This class is an imlementation of the AbstractFacade, typed to handle
 * Manufacturer entities. It is also a Stateless EJB Session Bean. EJBs have
 * many sophisticated features, but for this demo it's the ability to
 * auto handle JTA transactions (the EJB container does this).
 * 
 * @author jlombardo
 */
@Stateless
public class ManufacturerFacade extends AbstractFacade<Manufacturer> {
    @PersistenceContext(unitName = "sample_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManufacturerFacade() {
        super(Manufacturer.class);
    }
    public List<Manufacturer> findByState(String state){
        String jpql = "select m from Manufacturer m where m.state = :stateId";
        
        TypedQuery<Manufacturer> q = getEntityManager().createQuery(jpql, Manufacturer.class);
        q.setParameter("stateId", state);
        q.setMaxResults(10);
        return q.getResultList();
    }
    
    public int deleteById(int id){
        String jpql = "select m from Manufacturer m where m.manufacturerId = :id";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
