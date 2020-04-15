package dao.Manager;

import java.util.List;

import dao.Entity.Utilisateur;

public interface UtilisateurDAO {
	
		public  Utilisateur findUtilisateurByLoginMotPasse(String login,String motPasse);
		
		public Utilisateur findById(int id);
		
		public void add(Utilisateur utilisateur);
		
		
		public  Utilisateur edit(Utilisateur e);
		
		public  void delete(Utilisateur e); 
		
		public List<Utilisateur> findAll();

		List<Utilisateur> findAllSaufAdmin();
		
                public List<Utilisateur> findUtiliByRechercheCodeDepot(String codeDepot);
                
                public List<Utilisateur> findUtiliByRechercheNom(String nom);
                
                public List<Utilisateur> findUtiliByNom(String nom);

}
