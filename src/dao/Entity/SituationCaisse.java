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
@Table(name="SITUATION_CAISSE")
@NamedQuery(name="SituationCaisse.findAll", query="SELECT e FROM SituationCaisse e")
public class SituationCaisse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_OPERATION")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOpration;
	
        @Column(name="MONTANT_VENTE")
	private BigDecimal  montantVente;
        
        @Column(name="MONTANT_INITIAL")
	private BigDecimal  montantInitial;
        
         @Column(name="MONTANT_ACHAT")
	private BigDecimal  montantAchat;
         
          @Column(name="MONTANT_DEPENCE")
	private BigDecimal  montantDepence;
          
          @Column(name="MONTANT_VIREMENT")
	private BigDecimal  montantVirement;
          
         @Column(name="MONTANT_VIREMENT_ACHAT")
	private BigDecimal  montantVirementAchat;
         
           @Column(name="REGLEMENT")
	private BigDecimal  reglement;
          
        @Column(name="MONTANT_PROFIL_OPERATION")
	private BigDecimal  montantProfilOperation;
          
           @Column(name="MONTANT_FINAL")
	private BigDecimal  montantFinal;

           @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;

	
        
	public SituationCaisse() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOpration() {
        return dateOpration;
    }

    public void setDateOpration(Date dateOpration) {
        this.dateOpration = dateOpration;
    }

    public BigDecimal getMontantVente() {
        return montantVente;
    }

    public void setMontantVente(BigDecimal montantVente) {
        this.montantVente = montantVente;
    }

    public BigDecimal getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(BigDecimal montantInitial) {
        this.montantInitial = montantInitial;
    }

    public BigDecimal getReglement() {
        return reglement;
    }

    public void setReglement(BigDecimal reglement) {
        this.reglement = reglement;
    }

    public BigDecimal getMontantAchat() {
        return montantAchat;
    }

    public void setMontantAchat(BigDecimal montantAchat) {
        this.montantAchat = montantAchat;
    }

    public BigDecimal getMontantDepence() {
        return montantDepence;
    }

    public void setMontantDepence(BigDecimal montantDepence) {
        this.montantDepence = montantDepence;
    }

    public BigDecimal getMontantVirement() {
        return montantVirement;
    }

    public void setMontantVirement(BigDecimal montantVirement) {
        this.montantVirement = montantVirement;
    }

    public BigDecimal getMontantVirementAchat() {
        return montantVirementAchat;
    }

    public void setMontantVirementAchat(BigDecimal montantVirementAchat) {
        this.montantVirementAchat = montantVirementAchat;
    }

    public BigDecimal getMontantProfilOperation() {
        return montantProfilOperation;
    }

    public void setMontantProfilOperation(BigDecimal montantProfilOperation) {
        this.montantProfilOperation = montantProfilOperation;
    }

   

    public BigDecimal getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(BigDecimal montantFinal) {
        this.montantFinal = montantFinal;
    }

	


        

	
	

}