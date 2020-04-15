/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Caisse;

import Utils.Constantes;
import dao.Entity.SituationCaisse;
import dao.Manager.SituationCaisseDAO;
import dao.ManagerImpl.SituationCaisseDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationCaissePeriodeController implements Initializable {

    @FXML
    private TableView<SituationCaisse> tableSituationCaisse;
    @FXML
    private TableColumn<SituationCaisse, Date> dateOperationColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantInitialColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantAchatColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantVenteColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantDepenceColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantVirementColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantVirementAchatColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantProfilOperationColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantFinalColumn;
    @FXML
    private TableColumn<SituationCaisse, BigDecimal> montantReglementColumn;
    @FXML
    private DatePicker dateOperDebut;
    @FXML
    private DatePicker dateOperFin;
    @FXML
    private TextField montantTotalField;
   
    /**
     * Initializes the controller class.
     */
    
      navigation nav = new navigation();   
    SituationCaisseDAO situationCaisseDAO = new SituationCaisseDAOImpl();
    
     private final  ObservableList<SituationCaisse> listeSituationCaisse = FXCollections.observableArrayList();
    @FXML
    private ImageView btnRafrechaire;

    
    
    
       void setColumnProperties() {

            dateOperationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOpration"));

            montantAchatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantAchat()));
                }
                
             });
            
               montantDepenceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantDepence()));
                }
                
             });
                  
               montantFinalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantFinal()));
                }
                
             });
                        
               montantReglementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getReglement()));
                }
                
             });
                              
              montantInitialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantInitial()));
                }
                
             });
                                    
              montantProfilOperationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantProfilOperation()));
                }
                
             });
                                          
             montantVirementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVirement()));
                }
                
             });
                                                
              montantVirementAchatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVirementAchat()));
                }
                
             });
                                                      
             montantVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationCaisse, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationCaisse, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantVente()));
                }
                
             });
              
      
       }
      
       
          void loadDetail() {

        listeSituationCaisse.clear();
        listeSituationCaisse.addAll(situationCaisseDAO.findAll());
        tableSituationCaisse.setItems(listeSituationCaisse);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
         listeSituationCaisse.clear();
                                     
		    		if(!dateOperDebut.getValue().equals(dateOperFin.getValue()))
		    		{
		    			if(dateOperFin.getValue().compareTo(dateOperDebut.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateOperDebut.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateOperFin.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
       
        List<SituationCaisse> situationCaisses = situationCaisseDAO.findFilterSituationCaisseByDateOperation(dateOperaDebut, dateOperaFin);
        
         BigDecimal initial = BigDecimal.ZERO;
         BigDecimal Final = BigDecimal.ZERO;
        
        for (int i = 0; i < situationCaisses.size(); i++) {
            
            SituationCaisse situationCaisseTmp = situationCaisses.get(i);
            
            if (i >0){
            
             initial = situationCaisses.get(i-1).getMontantFinal();
            
            situationCaisseTmp.setMontantInitial(initial);
            
             Final = (situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence()).add(situationCaisseTmp.getMontantVirementAchat())));
           
             situationCaisseTmp.setMontantFinal(Final);
             
             situationCaisses.set(i, situationCaisseTmp);

            }
        }

        listeSituationCaisse.addAll(situationCaisses);
        tableSituationCaisse.setItems(listeSituationCaisse);
        setColumnProperties();
    
        
        
        
        
    }

      void clear(){
    
    dateOperDebut.setValue(null);
    dateOperFin.setValue(null);
    tableSituationCaisse.getItems().clear();
        
    }
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        clear();
        
    }
    
}
