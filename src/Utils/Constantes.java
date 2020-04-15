/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 
 */
public class Constantes {

    
    
    /*MESSAGE D'ERREUR */
   public static boolean DeveloppingMode=true;

    
    public static String  CHAMP_OBLIGATOIRE= "Champ Obligatoire";
    

    public static String C_ACHAT="/A";
    public static String C_VENTE="/V";
    public static String C_INITIAL_STOCK="/IS";
    public static String C_INITIAL_CAISSE="/IC";
    public static String C_DEPENCE="/D";
    public static String C_REGLEMENT="/R";
    public static String C_VIREMENT="/VI";
    public static String C_VIREMENT_ACHAT="/VA";
    public static String C_PERTE="/P";
    
  /*Etat d'Affichage Form*/
    public static final int POUR_RECHERCHER=0;
    public static final int POUR_AJOUTER=0;
    public static final int POUR_MOUDIFIER=1;
    
    public static String SIEGE= "Siege";
    public static String REGION= "Region";
    public static String DEPOT= "AGADIR";
    
    
    
    
    /*Compteur Sequenceur*/

    public static String OPERATION="OPR ";


    public static String DESIGNATION_VENTE="Vente sur Article N° ";
    public static String DESIGNATION_MONTANT="Achat sur Montant Payé";
    public static String DESIGNATION_FRAIS_TRANSPORT="Achat sur Frais Transport";
    public static String DESIGNATION_AUTRE_FRAIS="Achat sur Autre Frais";
    public static String DESIGNATION_TRANSIT="Achat sur Transit";
    public static String DESIGNATION_FOURNISSEUR="Achat sur Fournissuer";
    
    public static String DESIGNATION_CAISSE="Initial Caisse sur Operation N° ";
    public static String DESIGNATION_REGLEMENT="Reglement sur Operation N° ";
    public static String DESIGNATION_VIREMENT="Virement sur Operation N° ";
    public static String DESIGNATION_VIREMENT_ACHAT="Virement Achat sur Operation N° ";
    public static String DESIGNATION_DEPENSE="Depense sur Operation N° ";
    
    
    public static String PAIEMENT="sur Paiement: ";
    
    public static String ETAT_BROUILLONS="Brouillons";
    public static String ETAT_REEL="Reel";
    
    public static String OPERATION_NUM="Operation N° ";

    
    public static String INITIAL_STOCK= "Initial Stock";
    public static String ACHAT="Achat";
    public static String VENTE="Vente";
    public static String VIREMENT="Virement";
    public static String VIREMENT_ACHAT="Virement Achat";
    public static String DEPENSE="Depense";
    public static String REGLEMENT="Reglement";
    public static String INITIAL_CAISSE="Initial Caisse";
    public static String PERTE="Perte";
    
    public static String ETAT_STATUT_OVERTE="Ouverte";
    public static String ETAT_STATUT_FERMER="Fermer"; 
    
    public static String CLIENT="Client"; 
    public static String TRANSIT="Transit"; 
    public static String CAISSE="Caisse"; 
    public static String FOURNISSEUR="Fournisseur"; 
    
    public static String MESSAGE_ALERT_VALIDER_QTE_LIVRAISON ="Voulez-vous vraiment continuer le traitement ?";
    public static String MESSAGE_ALERT_FERMER_SESSION ="Voulez-vous vraiment fermer la session ?";
    public static String MESSAGE_ALERT_QUITTER_APP ="Voulez-vous vraiment quitter l'application ?";
    
    
    public static String COMMANDE_VALIDER = "Commande Valideé avec Succès";
    public static String COMMANDE_RECU = "Commande Reçu avec Succès";
    public static String COMMANDE_RETOUR_EN_ATTENTE ="Commande Retour En Attente";
    public static String COMMANDE_RETOUR ="Commande Retour Valideé avec Succès";
    public static String COMMANDE_RETOUR_REGLE ="Commande Retour Réglé avec Succès";
    public static String COMMANDE_GRATUITE ="Commande Gratuite Valideé";
    
    
    public static String MESSAGE_ALERT_AUCUN_TRAITEMENT = "Aucun Traitement n'a été réalisée pour ce compte ";
    public static String MESSAGE_ALERT_FAUX_DATE = "Vous devez Vérifier la Date que Vous avez Saisie SVP!!";
    public static String VERIFIER_ARTICLE="Veuillez s'il vous plaît vérifier l'Article que Vous avez Saisi !!";
    public static String MESSAGE_ALERT_SELECTIONNER="Veuillez selectionner un enregistrement SVP  !!";
    public static String VERIFIER_DATE_FERMETURE="Veuillez s'il vous plaît vérifier la table ne supporte aucun date Ouverte !!";
    public static String VERIFIER_DATE_JOURNEE="La date que vous avez saisi est déjà créé ou bien fermer !!";
    public static String TYPE_ALERT_INFORMATION="Information";
    public static String SELECTION_LIGNE_MODIFIER ="Il faut Séléctionner la ligne à modifier SVP !!";
    public static String SELECTION_ERREUR = "Erreur Seléction";
    public static String ERREUR_DATE ="Erreur Date";
    public static String SELECTION_MONTANT = "Tapez le Montant !!";
    public static String SELECTION_FOURNISSEUR = "Sélectionner un Fournisseur";
    public static String VERIFIER_QTE = "Veuillez s'il vous plaît vérifier la Qte que Vous avez Saisi !!";
    public static String VERIFIER_BON_GRATUITE_BON_ROUTEUR = "Vous devez sélectionner (Bon Retour/Gratuité ou Fournisseur). les champs Vide !!";
    public static String VERIFICATION_SELECTION_LIGNE ="Il faut selectionner une ligne SVP !!";
    public static String REMPLIR_CHAMPS = "Vous devez Remplir Tout les champs SVP !!";
    public static String REMPLIR_CHAMPS_DATE = "Vous devez Remplir les Dates SVP !!";
    public static String MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT ="Vous devez Vérifier la Date Fin et Supérieur du Date Debut SVP!!";
    public static String REMPLIR_COCHE = "Vous devez coché une cellule pour règle SVP!!";
    public static String VERIFIER_NUM_COMMANDE = "Veuillez s'il vous plaît vérifier votre NUM COMMANDE que Vous avez Saisi !!";
    public static String VERIFIER_BON_RETOUR_BON_GRATUITE = "Vous devez cocher un Type (Bon Retour/Bon Gratuité) SVP !!";
    public static String VERIFIER_FIX_VARIABLE ="Vous devez cochez un Type (Fix/Variable) SVP !!";
    public static String VERIFIER_COORDONEES_EXISTE ="Veuillez s'il vous plaît vérifier votre traitement les coordonnées et deja existe !!";
    public static String VERIFIER_AJOUTER_BOX_CARTON_FILMGOLD_FILMNORMAL_ADHESIF ="Les Prix que vous pouvez Ajouter jusqu'a maintenant sont: (Prix Box, Prix Carton, Prix Cellophane, Prix Adhesif) SVP !!";
    public static String VERIFICATION_DATE_SAISIE ="Vous devez saisir une Date Correcte par exemple: 2018/05/10 SVP!!";
    public static String VERIFIER_PRIX_SAISIE ="Le Prix que Vous avez Saisi pour ce Fournisseur n'existe pas !!";
    public static String VERIFIER_N_RETOUR="Veuillez s'il vous plaît vérifier votre NUM RETOUR !!";
    public static String VERIFIER_FOURNISSEUR_CLIENT="Veuillez s'il vous plaît vérifier que les champs (Client/Fournisseur) n'ont pas Vide !!"; 
    public static String SUPRIMER_ENREGISTREMENT = "l'Enregistrement à été Supprimée avec succés";
    public static String REGLER_ENREGISTREMENT = "l'Enregistrement à été Reglée avec succés";
    public static String MODIFIER_ENREGISTREMENT = "l'Enregistrement à été Modifiée avec succés";
    public static String AJOUTER_ENREGISTREMENT = "l'Enregistrement à été Ajoutée avec succés";
    public static String CONFIRMATION_ENREGISTREMENT = "La Modification réussie avec succès, Vous devez Valider la Commande Directement";
    public static String VERIFIER_QTE_PRIX = "Veuillez s'il vous plaît vérifier Les Champs Qte, Prix !!";
    public static String VERIFIER_FILTRE = "Veuillez s'il vous plaît vérifier votre Filtration (Sub Categorie, Catigorie, MP)!!";
     
    
    
    public static String VERIFIER_CompteClient = "Veuillez s'il vous plaît vérifier le Compte Client !!";
}
  