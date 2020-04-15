package dao.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="COMPTE_CLIENT")
@NamedQuery(name="CompteClient.findAll", query="SELECT e FROM CompteClient e")
public class CompteClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE_COMPTE")
	private String code;

	@Column(name="LIBELLE")
	private String  libelle;
	
        @Column(name="TYPE_COMPTE")
	private String  typeCompte;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
	
        
	public CompteClient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

        public String getTypeCompte() {
        return typeCompte;
        }

        public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
        }

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

        public Date getDateCreation() {
        return dateCreation;
        }

        public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
        }

        public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
        }

        public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
        }


	
	

}