/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import dao.Entity.CompteClient;
import dao.Entity.DetailCompteClient;
import dao.Entity.SituationCaisse;
import dao.Manager.CompteClientDAO;
import dao.Manager.DetailCompteClientDAO;
import dao.ManagerImpl.CompteClientDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationCompteClientController implements Initializable {

    @FXML
    private TableView<DetailCompteClient> tableDetailCompte;
    @FXML
    private TableColumn<DetailCompteClient, Date> dateOperationColumn;
    @FXML
    private TableColumn<DetailCompteClient, String> designationColumn;
    @FXML
    private TableColumn<DetailCompteClient, BigDecimal> montantCreditColumn;
    @FXML
    private TableColumn<DetailCompteClient, BigDecimal> montantDebitColumn;
    @FXML
    private ComboBox<String> codeCompteCombo;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private Label totalCredit;
    @FXML
    private Label totalDebit;
    @FXML
    private Label solde;

    /**
     * Initializes the controller class.
     */
    
       DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();
     CompteClientDAO compteClientDAO = new CompteClientDAOImpl();
    
      private Map< String, CompteClient> mapCompteClient = new HashMap<>();
        private Map< String, String> mapCode = new HashMap<>();
        private Map< String, String> mapLibelle = new HashMap<>();
         
  List<CompteClient>  listCompteClient = compteClientDAO.findAll();	
 
     ObservableList<String> listeCompteLibelle = FXCollections.observableArrayList();
    
     private final ObservableList<DetailCompteClient> listeDetailCompteClient=FXCollections.observableArrayList();
       
  
     
     navigation nav = new navigation();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             int i=0;
	      	while(i<listCompteClient.size())
	      		{	
	      			CompteClient compteClient=listCompteClient.get(i);
	      			mapCompteClient.put(compteClient.getLibelle(), compteClient);
	      			mapCode.put(compteClient.getCode(), compteClient.getLibelle());
		      		mapLibelle.put(compteClient.getLibelle(),compteClient.getCode());
	      			
                                listeCompteLibelle.add(compteClient.getLibelle());

	      			i++;
                        }
                
                
        codeCompteCombo.setItems(listeCompteLibelle);

        // TODO
      setColumnProperties();
      loadDetail();  
        
    calcule ();

    }    

    
    
        void calcule (){
    
          BigDecimal calculCreditTotal= BigDecimal.ZERO;
             BigDecimal calculDebitTotal= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailCompteClient.size() ;rows++ ){

               DetailCompteClient detailCompte = listeDetailCompteClient.get(rows);
               
            calculCreditTotal = calculCreditTotal.add(detailCompte.getMontantCredit());  

    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         totalCredit.setText(df.format(calculCreditTotal));  
        
     
           for( int rows = 0;rows<listeDetailCompteClient.size() ;rows++ ){
               
                  DetailCompteClient detailCompte = listeDetailCompteClient.get(rows);

            calculDebitTotal = calculDebitTotal.add(detailCompte.getMontantDebit());  
        
    }
           
                  
           
        totalDebit.setText(df.format(calculDebitTotal));  

         somme = calculCreditTotal.subtract(calculDebitTotal) ;
          
         solde.setText(df.format(somme));  
        
    }
    
              void setColumnProperties(){
        
    
        dateOperationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOpration"));
        
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("Designation"));

        montantDebitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompteClient, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompteClient, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantDebit()));
                }
                
             });

        montantCreditColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompteClient, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompteClient, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantCredit()));
                }
                
             });
        

    }
    
    void loadDetail(){
        
        listeDetailCompteClient.clear();
        listeDetailCompteClient.addAll(detailCompteClientDAO.findAll());
        tableDetailCompte.setItems(listeDetailCompteClient);
    }
        
        

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
      codeCompteCombo.getSelectionModel().select(-1);
        loadDetail();  
      setColumnProperties();
      calcule();
      
    }

    @FXML
    private void codeCompteOnAction(ActionEvent event) {
        
        String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
        
        List<DetailCompteClient> detailCompteClients = detailCompteClientDAO.findDetailCompteClientByCode(code);
        
        if (detailCompteClients != null){
        
        listeDetailCompteClient.clear();
        listeDetailCompteClient.addAll(detailCompteClients);
        tableDetailCompte.setItems(listeDetailCompteClient);
           calcule ();
        }
        
        
    }
    
}
