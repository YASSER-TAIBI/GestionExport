/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Utilisateur;

import Utils.Constantes;
import dao.Entity.Utilisateur;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.UtilisateurDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */


public class ModifierUtilisateurController implements Initializable {

    @FXML
    private Button btnModif;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField codeDepotField;


  UtilisateurDAO utilisateurDAO =  new UtilisateurDAOImpl();
     navigation nav = new navigation();

    
    public Utilisateur utilisateur;
   
   
  
    
    public void chargerLesDonnees(){
        
        codeDepotField.setText(utilisateur.getCodeUtilisateur());
        nomField.setText(utilisateur.getNom());
        loginField.setText(utilisateur.getLogin());
        passwordField.setText(utilisateur.getPassword()); 
  
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       }    

    @FXML
    private void ModifierUtilisateurAction(ActionEvent event) {

        
       utilisateur.setCodeUtilisateur(codeDepotField.getText());
       utilisateur.setNom(nomField.getText());
       utilisateur.setLogin(loginField.getText());
       utilisateur.setPassword(passwordField.getText());
       utilisateurDAO.edit(utilisateur);
      
       Stage stage = (Stage) btnModif.getScene().getWindow();
       stage.close();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.MODIFIER_ENREGISTREMENT);
//       
//       
       
        
//   
    }}
    

