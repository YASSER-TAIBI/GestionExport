/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.SituationStock;
import dao.Manager.SituationStockDAO;
import dao.ManagerImpl.SituationStockDAOImpl;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationStockPeriodeController implements Initializable {

    @FXML
    private TableView<SituationStock> tableSituationStock;
    @FXML
    private TableColumn<SituationStock, String> codeColumn;
    @FXML
     private TableColumn<SituationStock, Date> DateColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> qteAchatColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> qteVenteColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> qtePerteColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> prixMoyenColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> qteFinalColumn;
    @FXML
    private DatePicker dateOperDebut;
    @FXML
    private DatePicker dateOperFin;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField montantTotalField;

    /**
     * Initializes the controller class.
     */
      navigation nav = new navigation();   
    SituationStockDAO situationStockDAO = new SituationStockDAOImpl();
    
     private final  ObservableList<SituationStock> listeSituationStock = FXCollections.observableArrayList();
    @FXML
    private ImageView btnRafrechaire;

    
       void setColumnProperties() {

    
      codeColumn.setCellValueFactory(new PropertyValueFactory<>("codeArticle"));
      
      DateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOpration"));
      
 qteVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteTotalVente()));
                }
                
             });
      

       qteAchatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteTotalAchat()));
                }
                
             });

       
       qteInitialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteTotalInitial()));
                }
                
             });
         
  qtePerteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteTotalPerte()));
                }
                
             });
       
       qteFinalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteTotalFinal()));
                }
                
             });
           

      
       prixMoyenColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationStock, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationStock, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrixMoyen()));
                }
                
             });
      
       }
      
       
          void loadDetail() {

        listeSituationStock.clear();
        listeSituationStock.addAll(situationStockDAO.findAll());
        tableSituationStock.setItems(listeSituationStock);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
        
               if (txtCode.getText().equalsIgnoreCase("") && dateOperDebut.getValue() == null && dateOperFin.getValue() == null)
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
        
        }else if (txtCode.getText().equalsIgnoreCase("") && dateOperDebut.getValue() != null && dateOperFin.getValue() != null ){

                LocalDate localDate=dateOperDebut.getValue();
            
                Date dateDebut=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                              
                 localDate=dateOperFin.getValue();
            
                Date dateFin=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            List<SituationStock> listSituationStock = situationStockDAO.findFilterSituationStockByDateOperation(dateDebut, dateFin);

              listeSituationStock.clear();
               listeSituationStock.addAll(listSituationStock);
                  tableSituationStock.setItems(listeSituationStock);
                  setColumnProperties();
              
          }else if (!txtCode.getText().equalsIgnoreCase("") && dateOperDebut.getValue() == null && dateOperFin.getValue() == null){
          
             List<SituationStock> situationStock = situationStockDAO.findBySituationStock(txtCode.getText());
           listeSituationStock.clear();
   listeSituationStock.addAll(situationStock);
   tableSituationStock.setItems(listeSituationStock);
    setColumnProperties();
   
          }else if (!txtCode.getText().equalsIgnoreCase("") &&dateOperDebut.getValue() != null && dateOperFin.getValue() != null){
          
                LocalDate localDate=dateOperDebut.getValue();
            
                Date dateDebut=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                              
                 localDate=dateOperFin.getValue();
            
                Date dateFin=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
               List<SituationStock> situationStock = situationStockDAO.findFilterSituationStockByDateOperAndArt(dateDebut, dateFin, txtCode.getText());
               
               
   listeSituationStock.clear();
   listeSituationStock.addAll(situationStock);
   tableSituationStock.setItems(listeSituationStock);
    setColumnProperties();
          
   
          }
        
        
    }

    
    void clear(){
    
    dateOperDebut.setValue(null);
    dateOperFin.setValue(null);
    txtCode.clear();
    tableSituationStock.getItems().clear();
        
    }
    
    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        clear();

        
    }
    
}
