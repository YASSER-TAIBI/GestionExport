package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.DetailCompteClient;
import dao.Entity.DetailOperation;
import dao.Manager.DetailCompteClientDAO;


public class DetailCompteClientDAOImpl implements DetailCompteClientDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCompteClient e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCompteClient edit(DetailCompteClient e) {
		
	session.beginTransaction();
	DetailCompteClient p= (DetailCompteClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailCompteClient e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<DetailCompteClient> findAll() {
		return session.createQuery("select c from DetailCompteClient c").list();
		}

	public DetailCompteClient findById(int id) {
		return (DetailCompteClient)session.get(DetailCompteClient.class, id);
		}

          public List<DetailCompteClient> findDetailCompteClientByCode(String code) {
		
		Query query = session.createQuery("select u from DetailCompteClient u where u.code =:code");
		query.setParameter("code", code);
         
                
	   return query.list();
        
				}


}
