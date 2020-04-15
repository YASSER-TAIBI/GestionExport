package dao.Manager;

import dao.Entity.DetailOperation;
import java.math.BigDecimal;
import java.util.List;


public interface DetailOperationDAO {
	
	public  void add(DetailOperation e);
	
	public  DetailOperation edit(DetailOperation e);
	
	public  void delete(int id); 
	
	public List<DetailOperation> findAll();
	
	public DetailOperation findById(int id);

        public List<Object[]> findBySituationStock(String compte) ;
        
        public List<DetailOperation> findDetailOperationsByCompte();
	
        public BigDecimal findBySommeAchat(String code);
        
        public  BigDecimal findBySommeMontant(String code);
	
          public List<DetailOperation> findDateByDetailOperation(String code) ;

}
