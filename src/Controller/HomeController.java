/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Utils.Constantes;
import animasi.FadeInTransition;
import dao.Entity.Habilitation;
import dao.Entity.Utilisateur;
import dao.Manager.HabilitationDAO;
import dao.ManagerImpl.HabilitationDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author khatari-pc
 */


public class HomeController implements Initializable {
          navigation nav = new navigation(); 
    /**
     * Initializes the controller class.
     */
    
    @FXML    
    private AnchorPane centrePane;
    @FXML    
    private AnchorPane menuPane;
    @FXML
    private Label utilisateurConnecte;
    @FXML
    private Menu referentielMenu;
    @FXML
    private Menu parametreMenu;
    @FXML
    private ImageView deconnecterUtilisBtn;
    @FXML
    private MenuItem consultationStockMenu;
    @FXML
    private MenuItem ajouteUtilisateurMenu;
    @FXML
    private MenuItem gererAuthorisationMenu;
    @FXML
    private MenuItem listeArticleMenu;
    @FXML
    private Menu articleMenu;
    @FXML
    private Menu operationMenu;
    @FXML
    private Menu situationStockMenu;
    @FXML
    private Menu SituationCaisseMenu;
    @FXML
    private MenuItem consultationCaisseMenu;
    @FXML
    private MenuItem consultationCaissePeriodeMenu;
    @FXML
    private MenuItem compteClientMenu;
    @FXML
    private MenuItem consultationCompteClientMenu;
    @FXML
    private Menu journeeMenu;
    @FXML
    private MenuItem creationjourneeMenu;
    @FXML
    private MenuItem consultationStockPeriodeMenu;
    
    
    
    HabilitationDAO habilitationDAO = new HabilitationDAOImpl();
    Utilisateur utilisateur = new Utilisateur();
    @FXML
    private MenuItem consultationCaisseGlobalMenu;
    @FXML
    private MenuItem saisirOperationMenu;
    @FXML
    private MenuItem consultationOperationMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        utilisateur=nav.utilisateur;

     utilisateurConnecte.setText (LoginController.utilisateur.getNom());
     autoriseMenuUtilisateur();
     
   
    }    
 
   
    
    
     void autoriseMenuUtilisateur(){
	 
	 List<Habilitation> listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 
	 for(int i=0;i<listHabilitation.size();i++){
		 Habilitation habilitation =listHabilitation.get(i);
 
                 
//                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_RECEPTION)){
//			 receptionMenu.setDisable(habilitation.isAutorise());
//		 }
//                 
//                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_RETOUR_GRATUITE)){
//			 retourGratuiteMenu.setDisable(habilitation.isAutorise());
//		 }
//  
//                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_STOCK)){
//			 stockMenu.setDisable(habilitation.isAutorise());
//		 }
//                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_STOCK)){
//			 consultationStockMenu.setDisable(habilitation.isAutorise());
//		 }
//
//		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CLIENT)){
//			 clientMenu.setDisable(habilitation.isAutorise());
//		 }
//
//		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_REFERENTIEL)){
//			 referentielMenu.setDisable(habilitation.isAutorise());
//		 }
//
//		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_PARAMETRE)){
//			 parametreMenu.setDisable(habilitation.isAutorise());
//		 }
//               
//                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_AJOUTER_UTILISATEUR)){
//			 ajouteUtilisateurMenu.setDisable(habilitation.isAutorise());
//		 }
//                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_GERER_AUTHORISATION)){
//			 gererAuthorisationMenu.setDisable(habilitation.isAutorise());
//		 }

	 }
	 
 }

    
    public void menuDelete() throws IOException{
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getUser()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }

    @FXML
    private void menuAjouUtilisateur(ActionEvent event) {
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getListeUtilisateur()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }

    @FXML
    private void menuGererAuthorisation(ActionEvent event) {
                   try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getGererAuthUtilisateur()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }


    @FXML
    private void deconnecterUtilisOnMouseClick(MouseEvent event){
         Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_QUITTER_APP);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                      System.exit(0);

            } 
        
    }

    @FXML
    private void fermerAppOnMouseClick(MouseEvent event) throws IOException {
        
     
            
                  Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_FERMER_SESSION);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                  
                Stage stage = (Stage)
              deconnecterUtilisBtn.getScene().getWindow();
              stage.close();
                Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
                Stage stage1 = new Stage();
                Scene scence = new Scene(root);
                stage1.setScene(scence);
                
                 //supprimer la bar fermer reduire agrandir
                 
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();

            } 
    }

    @FXML
    private void homeOnMouseClick(MouseEvent event) throws IOException {

           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getHome()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
    }

          @FXML
    private void menuListeArticle(ActionEvent event) {
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getArticle()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }


    @FXML
    private void menuConsultationStock(ActionEvent event) {
        
                     try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationStock()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
    }



    @FXML
    private void menuSaisirOperation(ActionEvent event) {
        
               try {
                   
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getOperation()));
            centrePane.getChildren().setAll(pane1);
            
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuConsultationCaisse(ActionEvent event) {
        
                     try {
                         
           centrePane.getChildren().clear();
           centrePane.setOpacity(0);
           new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationCaisse()));
           centrePane.getChildren().setAll(pane1);
            
        } catch (Exception e) {
            
           System.out.println("Exception !!!!!!!!!");
           System.out.println(e);
            
        
        }
        
    }

    @FXML
    private void menuConsultationCaissePeriode(ActionEvent event) {
        
                       try {
                           
           centrePane.getChildren().clear();
           centrePane.setOpacity(0);
           new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationCaissePeriode()));
           centrePane.getChildren().setAll(pane1);
            
        } catch (Exception e) {
            
           System.out.println("Exception !!!!!!!!!");
           System.out.println(e);
            
        
        }
    }

    @FXML
    private void MenuCompteClient(ActionEvent event) {
                          try {
           centrePane.getChildren().clear();
           centrePane.setOpacity(0);
           new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getCompteClient()));
           centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
           System.out.println("Exception !!!!!!!!!");
           System.out.println(e);
            
        
        }
    }

    @FXML
    private void MenuConsultationCompteClient(ActionEvent event) {
                          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationCompteClient()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuCreationJournee(ActionEvent event) {
        
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getJournee()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuConsultationStockPeriode(ActionEvent event) {
        
        
                   try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationStockPeriode()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
    }

    @FXML
    private void menuConsultationCaisseGlobal(ActionEvent event) {
        
                        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationCaisseGlobal()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
    }

    @FXML
    private void menuConsultationOperation(ActionEvent event) {
        
                                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationOperation()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
        
        
    }

   
    
}
