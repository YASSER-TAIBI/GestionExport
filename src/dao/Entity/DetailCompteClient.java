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
@Table(name="DETAIL_COMPTE_CLIENT")
@NamedQuery(name="DetailCompteClient.findAll", query="SELECT e FROM DetailCompteClient e")
public class DetailCompteClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="CODE_COMPTE")
	private String code;
        
        @Column(name="DATE_OPERATION")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOpration;
        
	@Column(name="DESIGNATION")
	private String  Designation;

	@Column(name="MONTANT_CREDIT")
         private BigDecimal montantCredit;
    
        @Column(name="MONTANT_DEBIT")
        private BigDecimal montantDebit;
	
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
	
        
        
	public DetailCompteClient() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }

    public BigDecimal getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(BigDecimal montantDebit) {
        this.montantDebit = montantDebit;
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