/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Operation;

import dao.Entity.DetailOperation;
import dao.Manager.DetailOperationDAO;
import dao.ManagerImpl.DetailOperationDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationOperationController implements Initializable {

    @FXML
    private TableView<DetailOperation> tableOperation;
    @FXML
    private TableColumn<DetailOperation, String> codeOperaColumn;
    @FXML
    private TableColumn<DetailOperation, String> OperaColumn;
    @FXML
    private TableColumn<DetailOperation, String> codeArtColumn;
    @FXML
    private TableColumn<DetailOperation, String> artColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> montColumn;
    @FXML
    private TableColumn<DetailOperation, String> numDossColumn;
    @FXML
    private Button btnRafaichir;
    @FXML
    private TextField txtCodeOperation;
    @FXML
    private TextField txtCodeArticle;
    @FXML
    private Label msgCode;

    /**
     * Initializes the controller class.
     */
      private final ObservableList<DetailOperation> listeDetailOperation =FXCollections.observableArrayList();
            
     DetailOperationDAO detailOperationDAO = new DetailOperationDAOImpl();
     navigation nav = new navigation();
     DetailOperation detailOperation = new DetailOperation();
    
    
        void setColumnProperties(){
        
        codeOperaColumn.setCellValueFactory(new PropertyValueFactory<>("CodeOperation"));
        codeArtColumn.setCellValueFactory(new PropertyValueFactory<>("codeArticle"));
        OperaColumn.setCellValueFactory(new PropertyValueFactory<>("typeAction"));
        artColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitair"));
        montColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        numDossColumn.setCellValueFactory(new PropertyValueFactory<>("NumDossier"));
     }
    
     void loadDetail(){
        
        listeDetailOperation.clear();
        listeDetailOperation.addAll(detailOperationDAO.findAll());
        tableOperation.setItems(listeDetailOperation);
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
    private void rafraichirOperation(ActionEvent event) {
        
        
        
    }

    @FXML
    private void searche(KeyEvent event) {
    }
    
}
