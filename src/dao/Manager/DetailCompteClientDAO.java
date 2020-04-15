package dao.Manager;

import dao.Entity.DetailCompteClient;
import java.util.List;


public interface DetailCompteClientDAO {
	
	public  void add(DetailCompteClient e);
	
	public  DetailCompteClient edit(DetailCompteClient e);
	
	public  void delete(DetailCompteClient e); 
	
	public List<DetailCompteClient> findAll();
	
	public DetailCompteClient findById(int id);
        
        public List<DetailCompteClient> findDetailCompteClientByCode(String code);
        
}
