package dao.Manager;

import dao.Entity.SituationStock;
import java.util.Date;
import java.util.List;


public interface SituationStockDAO {
	
	public  void add(SituationStock e);
	
	public  SituationStock edit(SituationStock e);
	
	public  void delete(int id); 
	
	public List<SituationStock> findAll();
	
	public SituationStock findById(int id);
	
        public SituationStock findDateCodeArtBySituationStock(Date dateOper, String codeArt) ;
        
        public List<SituationStock> findDateCodeArtListBySituationStock(Date dateOper, String codeArt);
        
        public List<SituationStock> findDateBySituationStock(Date dateOper);
          
        public List<SituationStock> findBySituationStock(String codeArt);
        
        public List<SituationStock> findFilterSituationStockByDateOperation(Date dateDebut,Date dateFin);
        
        public List<SituationStock> findFilterSituationStockByDateOperAndArt(Date dateDebut,Date dateFin, String codeArt);
        


	

}
