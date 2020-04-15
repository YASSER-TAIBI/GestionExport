package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="DETAIL_OPERATION")
@NamedQuery(name="DetailOperation.findAll", query="SELECT e FROM DetailOperation e")
public class DetailOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="CODE_OPERATION")
	private String  CodeOperation;
        
	@Column(name="DATE_OPERATION")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOpration;

	@Column(name="DESIGNATION")
	private String Designation;
        
        @Column(name="CODE_ARTICLE")
	private String codeArticle;
        
        @Column(name="CODE_COMPTE")
	private String codeCompte;
        
        @Column(name="CODE_COMPTE_FOUR")
	private String codeCompteFour;
        
        @Column(name="CODE_COMPTE_TRANSIT")
	private String codeCompteTransit;
        
        @Column(name="ARTICLE")
	private String article;
        
        @Column(name="TYPE_ACTION")
	private String typeAction;
        
        @Column(name="NUM_DOSSIER")
	private String NumDossier;
	
        @Column(name="QUANTITE")
	private BigDecimal quantite;
        
        @Column(name="PRIX_UNITAIR")
	private BigDecimal prixUnitair;
        
        @Column(name="MONTANT")
	private BigDecimal montant;
        
        @Column(name="PROFIL_OPERATION")
	private BigDecimal profilOperation;
	
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
        
        @Column(name="ETAT")
	private String etat;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        
        
        
        
        
        
	public DetailOperation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Date getDateOpration() {
        return dateOpration;
    }

    public String getArticle() {
        return article;
    }

    public String getCodeCompteFour() {
        return codeCompteFour;
    }

    public void setCodeCompteFour(String codeCompteFour) {
        this.codeCompteFour = codeCompteFour;
    }

    public String getCodeCompteTransit() {
        return codeCompteTransit;
    }

    public void setCodeCompteTransit(String codeCompteTransit) {
        this.codeCompteTransit = codeCompteTransit;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getCodeOperation() {
        return CodeOperation;
    }

    public void setCodeOperation(String CodeOperation) {
        this.CodeOperation = CodeOperation;
    }

    public String getNumDossier() {
        return NumDossier;
    }

    public void setNumDossier(String NumDossier) {
        this.NumDossier = NumDossier;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getCodeCompte() {
        return codeCompte;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
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

    public void setDateOpration(Date dateOpration) {
        this.dateOpration = dateOpration;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitair() {
        return prixUnitair;
    }

    public void setPrixUnitair(BigDecimal prixUnitair) {
        this.prixUnitair = prixUnitair;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getProfilOperation() {
        return profilOperation;
    }

    public void setProfilOperation(BigDecimal profilOperation) {
        this.profilOperation = profilOperation;
    }

        

	
	

}