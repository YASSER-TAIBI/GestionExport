/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Caisse;

import Utils.Constantes;
import dao.Entity.CompteClient;
import dao.Entity.DetailOperation;
import dao.Entity.SituationCaisse;
import dao.Entity.SituationGlobalCaisse;
import dao.Manager.CompteClientDAO;
import dao.Manager.DetailOperationDAO;
import dao.ManagerImpl.CompteClientDAOImpl;
import dao.ManagerImpl.DetailOperationDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SutiationGlobalCaisseController implements Initializable {

    @FXML
    private TableView<SituationGlobalCaisse> tableSituationCaisse;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantInitialColumn;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantVenteColumn;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantDepenceColumn;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantVirementColumn;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantVirementAchatColumn;
    @FXML
    private TableColumn<SituationGlobalCaisse,BigDecimal> montantFinalColumn;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private ComboBox<String> codeCompteCombo;

    navigation nav = new navigation();   
    /**
     * Initializes the controller class.
     */
      CompteClientDAO compteClientDAO = new CompteClientDAOImpl();
      DetailOperationDAO detailOperationDAO = new DetailOperationDAOImpl();
      
            private Map< String, CompteClient> mapCompteClient = new HashMap<>();
            private Map< String, String> mapCode = new HashMap<>();
            private Map< String, String> mapLibelle = new HashMap<>();
            
      List<CompteClient>  listCompteClient = compteClientDAO.findAll();	
      List<CompteClient>  listCompteClientTMP = compteClientDAO.findAll();	
      List<DetailOperation>  listDetailOperation = detailOperationDAO.findDetailOperationsByCompte();	
    
     ObservableList<String> listeCompteLibelle = FXCollections.observableArrayList();
    private final  ObservableList<SituationGlobalCaisse> listeSituationGlobalCaisse = FXCollections.observableArrayList();      
    
         void setColumnProperties() {

         montantInitialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantInitial()));
                }
                
             });
         
          montantVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVente()));
                }
                
             });
          
           montantDepenceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantDepence()));
                }
                
             });
           
           
            montantVirementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVirement()));
                }
                
             });
      
            montantVirementAchatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVirementAchat()));
                }
                
             });
            
            montantFinalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantFinal()));
                }
                
             });
      

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        for (int i = 0; i <listDetailOperation.size() ; i++) {
//            DetailOperation detailOperation = listDetailOperation.get(i);
//            
//            for (int j = 0; j <listCompteClient.size(); j++) {
//                
//             CompteClient compteClient = listCompteClient.get(i);
//                
//                if (detailOperation.getCodeCompte().equals(compteClient.getCode()))
//                {
//                
//                listCompteClientTMP.add (compteClient);
//                
//                
//                }
//                
//            }
//            
//            
//        }
//     
        
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
    }    

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {

        codeCompteCombo.getSelectionModel().select(-1);
        tableSituationCaisse.getItems().clear();
        
    }

    @FXML
    private void typeOperOnAction(ActionEvent event) {
        
              
                
         String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
        
        List <Object[]> listeObject =detailOperationDAO.findBySituationStock(code);


             listeSituationGlobalCaisse.clear();
         
            for(int i=0; i<listeObject.size(); i++) {

                    Object[] object=listeObject.get(i);
                   
                    BigDecimal initial =(BigDecimal)object[0];
                    BigDecimal vente = (BigDecimal)object[1]; 
                    BigDecimal depense = (BigDecimal)object[2]; 
                    BigDecimal virement = (BigDecimal)object[3]; 
                    BigDecimal virementAchat = (BigDecimal)object[4]; 
                    BigDecimal Final = (BigDecimal)object[5]; 
                    
                    
                    if(initial == null && vente == null && depense == null&& virement == null && virementAchat == null && Final == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               SituationGlobalCaisse situationGlobalCaisse = new SituationGlobalCaisse();
               
                  situationGlobalCaisse.setMontantInitial(initial);
                  situationGlobalCaisse.setMontantVente(vente);
                  situationGlobalCaisse.setMontantDepence(depense);
                  situationGlobalCaisse.setMontantVirement(virement);
                  situationGlobalCaisse.setMontantVirementAchat(virementAchat);
                  situationGlobalCaisse.setMontantFinal(Final);
                  
                  listeSituationGlobalCaisse.add(situationGlobalCaisse);
                    }
            }
            
            
            tableSituationCaisse.setItems(listeSituationGlobalCaisse);
            setColumnProperties();
   }
    
}
