package dao.Manager;

import dao.Entity.CompteClient;
import java.util.List;


public interface CompteClientDAO {
	
	public  void add(CompteClient e);
	
	public  CompteClient edit(CompteClient e);
	
	public  void delete(CompteClient e); 
	
	public List<CompteClient> findAll();
	
	public CompteClient findById(int id);
        
        public CompteClient findByNotCompteCaisse();
        
        public CompteClient findDateCodeClientByDetailCompteClient(String codeClient);

        public List<CompteClient> findByCompteTransit();
         
        public List<CompteClient> findByNotCompteTransit();
        
         public List<CompteClient> findByCompteFournisseur();

}
