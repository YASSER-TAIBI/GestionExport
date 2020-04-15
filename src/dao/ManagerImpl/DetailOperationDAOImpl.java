package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.DetailOperation;
import dao.Manager.DetailOperationDAO;
import java.math.BigDecimal;


public class DetailOperationDAOImpl implements DetailOperationDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailOperation e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailOperation edit(DetailOperation e) {
		
	session.beginTransaction();
	DetailOperation p= (DetailOperation)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailOperation p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<DetailOperation> findAll() {
		return session.createQuery("select c from DetailOperation c").list();
		}

	public DetailOperation findById(int id) {
		return (DetailOperation)session.get(DetailOperation.class, id);
		}


        public List<Object[]> findBySituationStock(String compte) {
        Query query=  session.createQuery("select sum( case when c.typeAction= 'Initial Caisse' then c.montant else 0 end),sum( case when c.typeAction= 'Vente' then c.montant else 0 end), sum( case when c.typeAction= 'Depense' then c.montant else 0 end), sum( case when c.typeAction= 'Virement' then c.montant else 0 end), sum( case when c.typeAction= 'Virement Achat' then c.montant else 0 end),(sum( case when c.typeAction= 'Initial Caisse' then c.montant else 0 end)+ sum( case when c.typeAction= 'Vente' then c.montant else 0 end))-(sum( case when c.typeAction= 'Depense' then c.montant else 0 end)+ sum( case when c.typeAction= 'Virement' then c.montant else 0 end)+ sum( case when c.typeAction= 'VirementAchat' then c.montant else 0 end)) FROM DetailOperation c where c.codeCompte =:compte");
        query.setParameter("compte", compte);

        return query.list();
    }

          public List<DetailOperation> findDetailOperationsByCompte() {
        Query query=  session.createQuery("select distinct c.codeCompte from DetailOperation c where c.codeCompte is not null");

        return query.list();
    }
          
          
  public  BigDecimal findBySommeAchat(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select Sum(c.quantite) from DetailOperation c where c.CodeOperation =:code");
		query.setParameter("code", code);
		
		return (BigDecimal) query.uniqueResult();
		
	}
  
    public  BigDecimal findBySommeMontant(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select sum(c.montant) from DetailOperation c where c.CodeOperation =:code");
		query.setParameter("code", code);
		
		return (BigDecimal) query.uniqueResult();
		
	}
    
        public List<DetailOperation> findDateByDetailOperation(String code) {
		
		Query query = session.createQuery("select c from DetailOperation c where c.CodeOperation =:code");
		query.setParameter("code",code);
         
                
	   return query.list();
        
				}
          
}
