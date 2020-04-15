package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.CompteClient;
import dao.Entity.DetailCompteClient;
import dao.Entity.DetailOperation;
import dao.Manager.CompteClientDAO;


public class CompteClientDAOImpl implements CompteClientDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteClient e) {
            
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		//return p;
	}

	public CompteClient edit(CompteClient e) {
		
	session.beginTransaction();
	CompteClient p= (CompteClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(CompteClient e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<CompteClient> findAll() {
		return session.createQuery("select c from CompteClient c").list();
		}

        public List<CompteClient> findByCompteTransit() {
		return session.createQuery("select c from CompteClient c where c.typeCompte = 'Transit'").list();
		}
        
        public List<CompteClient> findByCompteFournisseur() {
		return session.createQuery("select c from CompteClient c where c.typeCompte = 'Fournisseur'").list();
		}
        
          public List<CompteClient> findByNotCompteTransit() {
		return session.createQuery("select c from CompteClient c where c.typeCompte in('Client','Caisse')").list();
		}
        
          public CompteClient findByNotCompteCaisse() {
		Query query = session.createQuery("select c from CompteClient c where c.typeCompte = 'Caisse'");
                return (CompteClient)  query.uniqueResult();
		}
          
	public CompteClient findById(int id) {
		return (CompteClient)session.get(CompteClient.class, id);
		}

        
               public CompteClient findDateCodeClientByDetailCompteClient(String codeClient) {
		
		Query query = session.createQuery("select u from CompteClient u where u.code=:codeClient");
                query.setParameter("codeClient", codeClient);
                
	return (CompteClient)  query.uniqueResult();
        
				}


}
