package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.SituationStock;
import dao.Manager.SituationStockDAO;
import java.util.Date;


public class SituationStockDAOImpl implements SituationStockDAO {
	Session session=HibernateUtil.openSession();

	public void add(SituationStock e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SituationStock edit(SituationStock e) {
		
	session.beginTransaction();
	SituationStock p= (SituationStock)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SituationStock p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<SituationStock> findAll() {
		return session.createQuery("select c from SituationStock c").list();
		}

        
        
        public SituationStock findDateCodeArtBySituationStock(Date dateOper, String codeArt) {
		
		Query query = session.createQuery("select u from SituationStock u where u.dateOpration =:dateOper and u.codeArticle =:codeArt");
		query.setParameter("dateOper", dateOper);
                query.setParameter("codeArt", codeArt);
                
	return (SituationStock)  query.uniqueResult();
        
				}
        
          public List<SituationStock> findDateCodeArtListBySituationStock(Date dateOper, String codeArt) {
		
		Query query = session.createQuery("select u from SituationStock u where u.dateOpration =:dateOper and u.codeArticle =:codeArt");
		query.setParameter("dateOper", dateOper);
                query.setParameter("codeArt", codeArt);
                
	return query.list();
        
				}
        
        
           public List<SituationStock> findDateBySituationStock(Date dateOper) {
		
		Query query = session.createQuery("select u from SituationStock u where u.dateOpration =:dateOper");
		query.setParameter("dateOper", dateOper);
         
                
	   return query.list();
        
				}

    public List<SituationStock> findFilterSituationStockByDateOperation(Date dateDebut,Date dateFin) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from SituationStock c where c.dateOpration BETWEEN :dateDebut and :dateFin Order by c.dateOpration");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         }
         
           return query.list();
    }
           
             public List<SituationStock> findBySituationStock(String codeArt) {
		
		Query query = session.createQuery("select u from SituationStock u where u.codeArticle =:codeArt ");
		query.setParameter("codeArt", codeArt);
         
                
	   return query.list();
        
				}
      
             public List<SituationStock> findFilterSituationStockByDateOperAndArt(Date dateDebut,Date dateFin, String codeArt) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from SituationStock c where c.dateOpration BETWEEN :dateDebut and :dateFin and c.codeArticle =:codeArt Order by c.dateOpration");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("codeArt", codeArt);
         }
         
           return query.list();
    }
        
	public SituationStock findById(int id) {
		return (SituationStock)session.get(SituationStock.class, id);
		}

}
