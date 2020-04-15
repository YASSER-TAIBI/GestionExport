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
@Table(name="SITUATION_STOCK")
@NamedQuery(name="SituationStock.findAll", query="SELECT e FROM SituationStock e")
public class SituationStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_OPERATION")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOpration;
        
        @Column(name="CODE_ARTICLE")
	private String  codeArticle;
	
        @Column(name="ARTICLE")
	private String  article;
        
        @Column(name="QUANTIT_TOTAL_VENTE")
	private BigDecimal  quantiteTotalVente;
        
        @Column(name="QUANTIT_TOTAL_PERTE")
	private BigDecimal  quantiteTotalPerte;
        
        @Column(name="QUANTITE_TOTAL_ACHAT")
	private BigDecimal  quantiteTotalAchat;
         
        @Column(name="QUANTITE_TOTAL_INITIAL")
	private BigDecimal  quantiteTotalInitial;
          
        @Column(name="QUANTITE_TOTAL_FINAL")
	private BigDecimal  quantiteTotalFinal;

        @Column(name="PRIX_MOYEN")
	private BigDecimal  prixMoyen;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
	
        
        
        
        
	public SituationStock() {
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public BigDecimal getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(BigDecimal prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public BigDecimal getQuantiteTotalVente() {
        return quantiteTotalVente;
    }

    public void setQuantiteTotalVente(BigDecimal quantiteTotalVente) {
        this.quantiteTotalVente = quantiteTotalVente;
    }

    public BigDecimal getQuantiteTotalAchat() {
        return quantiteTotalAchat;
    }

    public BigDecimal getQuantiteTotalPerte() {
        return quantiteTotalPerte;
    }

    public void setQuantiteTotalPerte(BigDecimal quantiteTotalPerte) {
        this.quantiteTotalPerte = quantiteTotalPerte;
    }

    public void setQuantiteTotalAchat(BigDecimal quantiteTotalAchat) {
        this.quantiteTotalAchat = quantiteTotalAchat;
    }

    public BigDecimal getQuantiteTotalInitial() {
        return quantiteTotalInitial;
    }

    public void setQuantiteTotalInitial(BigDecimal quantiteTotalInitial) {
        this.quantiteTotalInitial = quantiteTotalInitial;
    }

    public BigDecimal getQuantiteTotalFinal() {
        return quantiteTotalFinal;
    }

    public void setQuantiteTotalFinal(BigDecimal quantiteTotalFinal) {
        this.quantiteTotalFinal = quantiteTotalFinal;
    }


        

	
	

}