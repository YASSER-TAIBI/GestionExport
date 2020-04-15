/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Journee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface JourneeDAO {
              public Journee findById(int id);
		
		public void add(Journee journee);
		
		public  Journee edit(Journee e);
		
		public  void delete(Journee e); 
		
		public List<Journee> findAll();
                
                public Journee  findJourneeByDate(Date date, String etat);
                  
                public Journee findJourneeByDetailOverte(String etat) ;
                   
                      public  List<Journee>  findJourneeByUtilisateur(int utilisateur);
}
