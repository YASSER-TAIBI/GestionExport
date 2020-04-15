/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Journee;


import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Journee;
import dao.Entity.SituationStock;
import dao.Manager.ArticleDAO;
import dao.Manager.JourneeDAO;
import dao.Manager.SituationStockDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.JourneeDAOImpl;
import dao.ManagerImpl.SituationStockDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CreationJourneeController implements Initializable {

    @FXML
    private TableView<Journee> tableJournee;
    @FXML
    private TableColumn<Journee, Date> dateJourneeColumn;
    @FXML
    private TableColumn<Journee, String> statueColumn;
    @FXML
    private Button btnOuvert;
    @FXML
    private Button btnFerme;
    @FXML
    private Label msgCode;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private DatePicker dateOper;

    /**
     * Initializes the controller class.
     */
    
         private final ObservableList<Journee> listeJournee=FXCollections.observableArrayList();
         private final ObservableList<Article> listeArticle = FXCollections.observableArrayList();
            
           List<SituationStock> listSituationStock = new ArrayList<SituationStock>();
         
     JourneeDAO journeeDAO = new JourneeDAOImpl();
     SituationStockDAO situationStockDAO = new SituationStockDAOImpl();
        ArticleDAO articleDAO = new ArticleDAOImpl();
     navigation nav = new navigation();
   
     
       void setColumnProperties(){
        
        dateJourneeColumn.setCellValueFactory(new PropertyValueFactory<>("dateJournee"));
        statueColumn.setCellValueFactory(new PropertyValueFactory<>("statue"));
    
     }
    
     void loadDetail(){
        
        listeJournee.clear();
        listeJournee.addAll(journeeDAO.findAll());
        tableJournee.setItems(listeJournee);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      setColumnProperties();
      loadDetail();
        
    }    

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void ouvertOnAction(ActionEvent event) throws ParseException {
        
           if(dateOper.getValue()== null){
          nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);
     }
        else {
 //_______________________________________________________________ Création Journée __________________________________________________________________________             

           Journee journeeTmp = journeeDAO.findJourneeByDetailOverte(Constantes.ETAT_STATUT_OVERTE);
         
           if (journeeTmp == null){
 
          LocalDate localDate=dateOper.getValue();
          Date dateJournee=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
               Journee journee = new Journee();
               
        journee.setUtilisateurCreation(nav.getUtilisateur());
        journee.setDateOvertute(new Date());
        journee.setDateJournee(dateJournee);
        journee.setStatue(Constantes.ETAT_STATUT_OVERTE);

        journeeDAO.add(journee);
        
          
          
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Ajout avec succès");
          setColumnProperties();
            loadDetail();
          
          
          
    }else {
               BigDecimal Final = BigDecimal.ZERO;
               String codeArt = "";
               String art = "";
               BigDecimal prixMoyen = BigDecimal.ZERO;
               
             LocalDate localDate=dateOper.getValue();
             Date dateJournee=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
         
             if (dateJournee.compareTo(journeeTmp.getDateJournee())>0){
 
                     Journee journee = new Journee();
               
        journee.setUtilisateurCreation(nav.getUtilisateur());
        journee.setDateOvertute(new Date());
        journee.setDateJournee(dateJournee);
        journee.setStatue(Constantes.ETAT_STATUT_OVERTE);

        journeeDAO.add(journee);
                 
        
        journeeTmp.setDateFermeture(new Date());
        journeeTmp.setStatue(Constantes.ETAT_STATUT_FERMER);

        journeeDAO.edit(journeeTmp);   
        
   
        
            boolean exist = false;     
        
         List<SituationStock> listSituationStock = situationStockDAO.findDateBySituationStock(journeeTmp.getDateJournee());
              System.out.println("journeeTmp.getDateJournee() "+journeeTmp.getDateJournee());      
         
               for (int i = 0; i < listSituationStock.size(); i++) {
                   
                    SituationStock situationStockTmp = listSituationStock.get(i);
                    
                    if (situationStockTmp.getDateOpration().equals(journeeTmp.getDateJournee())){
                   
                   Final =   situationStockTmp.getQuantiteTotalFinal();
                   codeArt =  situationStockTmp.getCodeArticle();
                   art= situationStockTmp.getArticle();
                   prixMoyen = situationStockTmp.getPrixMoyen();
                   
                      exist = true;
      }
                       if (exist == true){
             
             SituationStock situationStock = new SituationStock();
        
        
        situationStock.setArticle(art);
        situationStock.setCodeArticle(codeArt);
        situationStock.setDateOpration(dateJournee);
        situationStock.setPrixMoyen(prixMoyen);
        situationStock.setQuantiteTotalAchat(BigDecimal.ZERO);
        situationStock.setQuantiteTotalInitial(Final);
        situationStock.setQuantiteTotalVente(BigDecimal.ZERO);
        situationStock.setQuantiteTotalFinal(Final);
        situationStock.setUtilisateurCreation(nav.getUtilisateur());
        situationStock.setDateCreation(new Date());
       
                situationStockDAO.add(situationStock);
          
        
       }else{
         SituationStock situationStock = new SituationStock();
        
        
        situationStock.setArticle(art);
        situationStock.setCodeArticle(codeArt);
        situationStock.setDateOpration(dateJournee);
        situationStock.setPrixMoyen(BigDecimal.ZERO);
        situationStock.setQuantiteTotalAchat(BigDecimal.ZERO);
        situationStock.setQuantiteTotalInitial(BigDecimal.ZERO);
        situationStock.setQuantiteTotalVente(BigDecimal.ZERO);
        situationStock.setQuantiteTotalFinal(BigDecimal.ZERO);
        situationStock.setUtilisateurCreation(nav.getUtilisateur());
        situationStock.setDateCreation(new Date());
       
                situationStockDAO.add(situationStock);
       }
                    
               }
       
            setColumnProperties();
            loadDetail();
       
             }else {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, "LA Date que vous avez saise et inferireur a la date de fermeture !!");
                return;
               }   
    }
           }
    }
    
    
    @FXML
    private void fermeOnAction(ActionEvent event) throws ParseException {

    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
    }
    
}
