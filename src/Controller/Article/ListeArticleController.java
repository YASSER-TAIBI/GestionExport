/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Manager.ArticleDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeArticleController implements Initializable {

    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Article, String> codeColumn;
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, String> uniteColumn;
    @FXML
    private TextField txtUnite;
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

    /**
     * Initializes the controller class.
     */
    
     private final ObservableList<Article> listeArticle=FXCollections.observableArrayList();
            
     ArticleDAO articleDAO = new ArticleDAOImpl();
     navigation nav = new navigation();
     Article article = new Article();
    
    
     
       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        uniteColumn.setCellValueFactory(new PropertyValueFactory<>("unite"));
     }
    
     void loadDetail(){
        
        listeArticle.clear();
        listeArticle.addAll(articleDAO.findAll());
        tableArticle.setItems(listeArticle);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      setColumnProperties();
      loadDetail();
    }    

    @FXML
    private void ajouterVille(ActionEvent event) {
        
            article =new Article();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
        
     }else {
       
       article.setCode(txtCode.getText());
       article.setLibelle(txtLibelle.getText());
       article.setUnite(txtUnite.getText());
       article.setUtilisateurCreation(nav.getUtilisateur());
       article.setDateCreation(new Date());
      
        articleDAO.add(article);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       clear();
    }
    }

    @FXML
    private void ModifierVille(ActionEvent event) {
        
           if (tableArticle.getSelectionModel().getSelectedItem() != null) {
              
               article= tableArticle.getSelectionModel().getSelectedItem();
               article.setCode(txtCode.getText());
               article.setLibelle(txtLibelle.getText());
               article.setUnite(txtUnite.getText());
      
               
               
          articleDAO.edit(article);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
      setColumnProperties();
      loadDetail();
       clear();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
        
    }

    @FXML
    private void SupprimerVille(ActionEvent event) {
            if(tableArticle.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Article article=tableArticle.getSelectionModel().getSelectedItem();
        articleDAO.delete(article);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();
       clear();
    }
    }

    void clear(){
    
    txtCode.clear();
    txtLibelle.clear();
    txtUnite.clear();
    }
    
    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
           Integer val= tableArticle.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));
              txtUnite.setText(uniteColumn.getCellData(val));

          }
    }
    
}
