/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.CompteClient;
import dao.Manager.ArticleDAO;
import dao.Manager.CompteClientDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import dao.Manager.DetailCompteClientDAO;
import dao.ManagerImpl.CompteClientDAOImpl;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeCompteController implements Initializable {

    @FXML
    private TableView<CompteClient> tableCompte;
    @FXML
    private TableColumn<CompteClient, String> codeColumn;
    @FXML
    private TableColumn<CompteClient, String> libelleColumn; 
    @FXML
    private TableColumn<CompteClient, String> typeCompteColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtLibelle;
    @FXML
    private Label msgCode;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private RadioButton radioClient;
    @FXML
    private RadioButton radioTranit;
    @FXML
    private ToggleGroup tCompte;
    @FXML
    private RadioButton radioCaisse;
    @FXML
    private RadioButton radioFournisseur;
    @FXML
    private ToggleGroup tCompte1;
    /**
     * Initializes the controller class.
     */
      private final ObservableList<CompteClient> listeCompteClient=FXCollections.observableArrayList();
            
     CompteClientDAO compteClientDAO = new CompteClientDAOImpl();
     navigation nav = new navigation();
     CompteClient compteClient = new CompteClient();
    


   
    
     
       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        typeCompteColumn.setCellValueFactory(new PropertyValueFactory<>("typeCompte"));
    
     }
    
     void loadDetail(){
        
        listeCompteClient.clear();
        listeCompteClient.addAll(compteClientDAO.findAll());
        tableCompte.setItems(listeCompteClient);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          setColumnProperties();
      loadDetail();
        // TODO
        
    }    

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
        
           Integer val= tableCompte.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              if (typeCompteColumn.getCellData(val).equals(Constantes.CLIENT))
              {
              radioClient.setSelected(true);
              }
              else if (typeCompteColumn.getCellData(val).equals(Constantes.TRANSIT))
              {
              radioTranit.setSelected(true);
              }
               else if (typeCompteColumn.getCellData(val).equals(Constantes.CAISSE))
              {
              radioCaisse.setSelected(true);
              }
                 else if (typeCompteColumn.getCellData(val).equals(Constantes.FOURNISSEUR))
              {
              radioFournisseur.setSelected(true);
              }
              
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));
            

          }
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
           compteClient =new CompteClient();
           
     if(txtCode.getText().equalsIgnoreCase("")){
         
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
        
     }else {
         
         if (radioClient.isSelected()){
         
         compteClient.setTypeCompte(Constantes.CLIENT);

         }else if (radioTranit.isSelected()){
             
         compteClient.setTypeCompte(Constantes.TRANSIT);
         
         }else if (radioCaisse.isSelected()){
             
         compteClient.setTypeCompte(Constantes.CAISSE);
         
         }else if (radioFournisseur.isSelected()){
             
         compteClient.setTypeCompte(Constantes.FOURNISSEUR);
         }
         
       compteClient.setCode(txtCode.getText());
       compteClient.setLibelle(txtLibelle.getText());
       compteClient.setUtilisateurCreation(nav.getUtilisateur());
       compteClient.setDateCreation(new Date());
      
        compteClientDAO.add(compteClient);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       clear();
    }
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        
           if (tableCompte.getSelectionModel().getSelectedItem() != null) {
              
               compteClient= tableCompte.getSelectionModel().getSelectedItem();
               
         if (radioClient.isSelected()){
         
         compteClient.setTypeCompte(Constantes.CLIENT);

         }else if (radioTranit.isSelected()){
             
         compteClient.setTypeCompte(Constantes.TRANSIT);
         
         }else if (radioCaisse.isSelected()){
             
         compteClient.setTypeCompte(Constantes.CAISSE);
         
         }else if (radioFournisseur.isSelected()){
             
         compteClient.setTypeCompte(Constantes.FOURNISSEUR);
         }
               
               compteClient.setCode(txtCode.getText());
               compteClient.setLibelle(txtLibelle.getText());
      
               
               
          compteClientDAO.edit(compteClient);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
      setColumnProperties();
      loadDetail();
       clear();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
        
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        
             if(tableCompte.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       CompteClient compteClient=tableCompte.getSelectionModel().getSelectedItem();
        compteClientDAO.delete(compteClient);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
      setColumnProperties();
      loadDetail();
      clear();
    }
        
    }

     void clear(){
    
    txtCode.clear();
    txtLibelle.clear();
    radioClient.setSelected(false);
    radioTranit.setSelected(false);
    radioCaisse.setSelected(false);
    radioFournisseur.setSelected(false);
    }
    
    
    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        clear();
        
    }
    
}
