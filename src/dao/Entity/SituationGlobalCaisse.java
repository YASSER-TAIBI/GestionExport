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

public class SituationGlobalCaisse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private BigDecimal  montantVente;

	private BigDecimal  montantInitial;

	private BigDecimal  montantDepence;

	private BigDecimal  montantVirement;

	private BigDecimal  montantVirementAchat;

	private BigDecimal  montantFinal;

	
        
	public SituationGlobalCaisse() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(BigDecimal montantFinal) {
        this.montantFinal = montantFinal;
    }

}