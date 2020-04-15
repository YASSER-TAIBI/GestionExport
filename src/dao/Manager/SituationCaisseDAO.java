package dao.Manager;

import dao.Entity.SituationCaisse;
import java.util.Date;
import java.util.List;


public interface SituationCaisseDAO {
	
	public  void add(SituationCaisse e);
	
	public  SituationCaisse edit(SituationCaisse e);
	
	public  void delete(int id); 
	
	public List<SituationCaisse> findAll();
	
	public SituationCaisse findById(int id);
	
        public List<SituationCaisse> findDateListBySituationCaisse(Date dateOper);
        
        public SituationCaisse findDateBySituationCaisse(Date dateOper);
            
	public List<SituationCaisse> findFilterSituationCaisseByDateOperation(Date dateDebut,Date dateFin);

}
