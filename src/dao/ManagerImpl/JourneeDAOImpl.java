/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Journee;
import dao.Manager.JourneeDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */


public class JourneeDAOImpl implements JourneeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public Journee findById(int id) {

return (Journee)session.get(Journee.class, id);    }

    @Override
    public void add(Journee journee) {

               session.beginTransaction();
		session.save(journee);
		
		session.getTransaction().commit();    }

    @Override
    public Journee edit(Journee e) {

session.beginTransaction();
	Journee p= (Journee)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Journee e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Journee> findAll() {

return session.createQuery("select c from Journee c").list();


    }
    
            public Journee  findJourneeByDate(Date date, String etat) {
	Query query = session.createQuery("select c from Journee c where c.dateJournee =:date and c.statue=:etat ");
                query.setParameter("date",date);
                query.setParameter("etat",etat);

   
                        return (Journee) query.uniqueResult();
    
          }
    
    
      public Journee findJourneeByDetailOverte(String etat) {
	Query query = session.createQuery("select c from Journee c where c.statue=:etat");

                query.setParameter("etat",etat);
   
                 return (Journee) query.uniqueResult();
      }
      
         public  List<Journee>  findJourneeByUtilisateur(int utilisateur) {
	Query query = session.createQuery("select c from Journee c where c.utilisateurCreation.id =:utilisateur ");

                query.setParameter("utilisateur",utilisateur);

   
                 return query.list();
      }
}
