package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.SituationCaisse;
import dao.Entity.SituationStock;
import dao.Manager.SituationCaisseDAO;
import java.util.Date;


public class SituationCaisseDAOImpl implements SituationCaisseDAO {
	Session session=HibernateUtil.openSession();

	public void add(SituationCaisse e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SituationCaisse edit(SituationCaisse e) {
		
	session.beginTransaction();
	SituationCaisse p= (SituationCaisse)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SituationCaisse p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

          public SituationCaisse findDateBySituationCaisse(Date dateOper) {
		
		Query query = session.createQuery("select u from SituationCaisse u where u.dateOpration =:dateOper");
		query.setParameter("dateOper", dateOper);

	return (SituationCaisse)  query.uniqueResult();
        
				}
        

	public List<SituationCaisse> findAll() {
		return session.createQuery("select c from SituationCaisse c").list();
		}

	public SituationCaisse findById(int id) {
		return (SituationCaisse)session.get(SituationCaisse.class, id);
		}


   public List<SituationCaisse> findDateListBySituationCaisse(Date dateOper) {
		
		Query query = session.createQuery("select u from SituationCaisse u where u.dateOpration =:dateOper");
		query.setParameter("dateOper", dateOper);
         
                
	   return query.list();
        
				}

    public List<SituationCaisse> findFilterSituationCaisseByDateOperation(Date dateDebut,Date dateFin) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from SituationCaisse c where c.dateOpration BETWEEN :dateDebut and :dateFin Order by c.dateOpration");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         }
         
           return query.list();
    }

}
