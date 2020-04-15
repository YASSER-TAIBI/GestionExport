/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Operation;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.CompteClient;
import dao.Entity.DetailCompteClient;
import dao.Entity.DetailOperation;
import dao.Entity.Journee;
import dao.Entity.Sequenceur;
import dao.Entity.SituationCaisse;
import dao.Entity.SituationStock;
import dao.Manager.ArticleDAO;
import dao.Manager.CompteClientDAO;
import dao.Manager.DetailOperationDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.SituationCaisseDAO;
import dao.Manager.SituationStockDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import dao.ManagerImpl.DetailOperationDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.SituationCaisseDAOImpl;
import dao.ManagerImpl.SituationStockDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import dao.Manager.DetailCompteClientDAO;
import dao.Manager.JourneeDAO;
import dao.ManagerImpl.CompteClientDAOImpl;
import dao.ManagerImpl.JourneeDAOImpl;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirOperationController implements Initializable {

    @FXML
    private TableView<DetailOperation> tableOpera;
     @FXML
    private TableColumn<DetailOperation, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailOperation, String> articleColumn;
    @FXML
    private TableColumn<DetailOperation, Date> dateOperaColumn;
    @FXML
    private TableColumn<DetailOperation, String> typeOperaColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailOperation, String> numOperColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailOperation, BigDecimal> prixColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtCode;
    @FXML
    private Label msgCode;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private DatePicker dateOper;
    @FXML
    private ComboBox<String> typeOperCombo;
    @FXML
    private TextField txtQte;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtMontant;
    @FXML
    private TextArea txtDesc;
    @FXML
    private TextField txtArticle;
    @FXML
    private TextField txtDossier;
    @FXML
    private TextField txtNumOper;
    @FXML
    private ComboBox<String> codeCompteCombo;
    @FXML
    private Label labQte;
    @FXML
    private Label labPrix;
    @FXML
    private Label labMont;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> transitCombo;
    @FXML
    private TextField txtMontTransit;
    @FXML
    private Button btnAjouterTable;
    @FXML
    private Label montantTransit;
    @FXML
    private Label montantTotal;
    @FXML
    private Label labMontTransit;
    @FXML
    private TextField txtMontantTotalPaye;
    @FXML
    private TextField txtMontantTotal;
    @FXML
    private TextField txtFraisTransport;
    @FXML
    private TextField txtAutreFais;
    @FXML
    private TextField txtFraisTransportPaye;
    @FXML
    private TextField txtAutreFaisPaye;
    @FXML
    private Label labMontTotalPaye;
    @FXML
    private Label lablabFraisTransportPaye;
    @FXML
    private Label labAutreFraisPaye;
    @FXML
    private TextField txtMontantFacture;
    @FXML
    private ImageView calculeFactureBtn;
    @FXML
    private Label labFraisTransport;
    @FXML
    private Label labAutreFrais;
    @FXML
    private ComboBox<String> fourCombo;
    
    String valeur="";
    DetailOperation operation = new DetailOperation();
    
    private BigDecimal prixMoyen = BigDecimal.ZERO;
    
    private BigDecimal montantTotalC = BigDecimal.ZERO;
    private BigDecimal montantTA = BigDecimal.ZERO;
    
    DetailOperation detailOperation = new DetailOperation();
    SituationStock situationStock = new SituationStock();
    SituationCaisse situationCaisse = new SituationCaisse();
    DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();
    CompteClientDAO compteClientDAO = new CompteClientDAOImpl();
    JourneeDAO journeeDAO = new JourneeDAOImpl();
    
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    DetailOperationDAO detailOperationDAO = new DetailOperationDAOImpl();
    SituationStockDAO situationStockDAO = new SituationStockDAOImpl();
    SituationCaisseDAO situationCaisseDAO = new SituationCaisseDAOImpl();
    ArticleDAO articleDAO = new ArticleDAOImpl();
    navigation nav = new navigation();   
      
    private final  ObservableList<DetailOperation> listeDetailOperation = FXCollections.observableArrayList();
  ObservableList<String> typeOperation =FXCollections.observableArrayList(Constantes.INITIAL_STOCK,Constantes.ACHAT,Constantes.VENTE,Constantes.DEPENSE,Constantes.PERTE,Constantes.VIREMENT,Constantes.VIREMENT_ACHAT,Constantes.REGLEMENT,Constantes.INITIAL_CAISSE);
  
        private Map< String, CompteClient> mapCompteClient = new HashMap<>();
        private Map< String, String> mapCode = new HashMap<>();
        private Map< String, String> mapLibelle = new HashMap<>();
         
  List<CompteClient>  listCompteClientTransit = compteClientDAO.findByCompteTransit();	
  List<CompteClient>  listCompteClientNotTransit = compteClientDAO.findByNotCompteTransit();
  List<CompteClient>  listCompteFournisseur = compteClientDAO.findByCompteFournisseur();
  
     ObservableList<String> listeCompteLibelleNotTransit = FXCollections.observableArrayList();
     ObservableList<String> listeCompteLibelleTransit = FXCollections.observableArrayList();
     ObservableList<String> listeCompteLibelleFournisseur = FXCollections.observableArrayList();
   
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
         Incrementation();
        
	      int i=0;
	      	while(i<listCompteClientNotTransit.size())
	      		{	
	      			CompteClient compteClient=listCompteClientNotTransit.get(i);
	      			mapCompteClient.put(compteClient.getLibelle(), compteClient);
	      			mapCode.put(compteClient.getCode(), compteClient.getLibelle());
		      		mapLibelle.put(compteClient.getLibelle(),compteClient.getCode());
	      			
                                listeCompteLibelleNotTransit.add(compteClient.getLibelle());

	      			i++;
                        }
                
                
                  int j=0;
	      	while(j<listCompteClientTransit.size())
	      		{	
	      			CompteClient compteClient=listCompteClientTransit.get(j);
	      			mapCompteClient.put(compteClient.getLibelle(), compteClient);
	      			mapCode.put(compteClient.getCode(), compteClient.getLibelle());
		      		mapLibelle.put(compteClient.getLibelle(),compteClient.getCode());
	      			
                                listeCompteLibelleTransit.add(compteClient.getLibelle());

	      			j++;
                        }
                
                 int k=0;
	      	while(k<listCompteFournisseur.size())
	      		{	
	      			CompteClient compteClient=listCompteFournisseur.get(k);
	      			mapCompteClient.put(compteClient.getLibelle(), compteClient);
	      			mapCode.put(compteClient.getCode(), compteClient.getLibelle());
		      		mapLibelle.put(compteClient.getLibelle(),compteClient.getCode());
	      			
                                listeCompteLibelleFournisseur.add(compteClient.getLibelle());

	      			k++;
                        }
                
        transitCombo.setItems(listeCompteLibelleTransit);
        codeCompteCombo.setItems(listeCompteLibelleNotTransit);
        fourCombo.setItems(listeCompteLibelleFournisseur);
        typeOperCombo.setItems(typeOperation);
        
        
        fourCombo.setDisable(true); 
        codeCompteCombo.setDisable(true); 
        txtDossier.setDisable(true);  
        txtCode.setDisable(true);
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(true);
        transitCombo.setDisable(true);
        txtMontTransit.setDisable(true);
        txtDesc.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
        setColumnProperties();
        loadDetail();
        
        List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());
        
        if (tableOpera.getItems().size()!=0)
        {

            
        
        DetailOperation detailOperation = listDetailOperations.get(0);
        LocalDate date = new java.util.Date( detailOperation.getDateOpration().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();    
        
        operation = detailOperation;
        
        
        typeOperCombo.setValue(detailOperation.getTypeAction());
        fourCombo.setValue(detailOperation.getCodeCompteFour());
        codeCompteCombo.setValue(detailOperation.getCodeCompte());
        txtDossier.setText(detailOperation.getNumDossier());
        dateOper.setValue(date);
        
        if (operation.getTypeAction().equals(Constantes.ACHAT)){
            
                 fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                codeCompteCombo.setDisable(true);
                txtDossier.setDisable(true);
                transitCombo.setDisable(false);
                txtMontTransit.setDisable(false);
                txtFraisTransport.setDisable(false);
                txtAutreFais.setDisable(false);
                txtMontantTotalPaye.setDisable(false);
                txtFraisTransportPaye.setDisable(false);
                txtAutreFaisPaye.setDisable(false);
                txtCode.setDisable(false);
                txtQte.setDisable(false);
                txtPrix.setDisable(false);
                txtMontant.setDisable(false);
                txtDesc.setDisable(false);
                
                txtCode.clear();
                txtArticle.clear();
                txtQte.clear();
                txtPrix.clear();
                txtMontant.clear();
                labQte.setText("");
                labPrix.setText("");
        }else{
          fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                codeCompteCombo.setDisable(true);
                txtDossier.setDisable(true);
                transitCombo.setDisable(true);
                txtMontTransit.setDisable(true);
                txtFraisTransport.setDisable(true);
                txtAutreFais.setDisable(true);
                txtMontantTotalPaye.setDisable(true);
                txtFraisTransportPaye.setDisable(true);
                txtAutreFaisPaye.setDisable(true);
                txtCode.setDisable(true);
                txtQte.setDisable(true);
                txtPrix.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                
                txtCode.clear();
                txtArticle.clear();
                txtQte.clear();
                txtPrix.clear();
                txtMontant.clear();
                labQte.setText("");
                labPrix.setText("");
        
        }
                
                
                //CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int y = 0; y < tableOpera.getItems().size(); y++) {
                       
                       DetailOperation detailOperationTmp = tableOpera.getItems().get(y);
                       
                        montTotal = montTotal.add(detailOperationTmp.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));
                
                
        }
        
     
        
    }    

     void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
          txtNumOper.setText(Constantes.OPERATION+(sequenceur.getValeur()+1));

   }
    
      void setColumnProperties() {

    
      numOperColumn.setCellValueFactory(new PropertyValueFactory<>("CodeOperation"));
      dateOperaColumn.setCellValueFactory(new PropertyValueFactory<>("dateOpration"));
      prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitair"));
      qteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      typeOperaColumn.setCellValueFactory(new PropertyValueFactory<>("typeAction"));
      articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
      codeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("codeArticle"));
      
         montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailOperation, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailOperation, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
         

           
         prixColumn.setCellFactory(column -> {
			return new TableCell<DetailOperation, BigDecimal>() {
				@Override
				protected void updateItem(BigDecimal item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
                                            
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                                            
                                            setText(df.format(item));
                                        }
						
				}
			};
		});
           

         qteColumn.setCellFactory(column -> {
			return new TableCell<DetailOperation, BigDecimal>() {
				@Override
				protected void updateItem(BigDecimal item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
                                            
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                                            
                                            setText(df.format(item));
                                        }
						
				}
			};
		});
     

    }

    void loadDetail() {

        listeDetailOperation.clear();
        listeDetailOperation.addAll(detailOperationDAO.findDateByDetailOperation(txtNumOper.getText()));
        tableOpera.setItems(listeDetailOperation);
    }
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
        String Operation = "";
        
           Integer val= tableOpera.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtQte.setText(qteColumn.getCellData(val)+"");
              txtPrix.setText(prixColumn.getCellData(val)+"");
              txtMontant.setText(montantColumn.getCellData(val)+"");
              txtArticle.setText(articleColumn.getCellData(val)+"");
              txtCode.setText(codeArticleColumn.getCellData(val)+"");

               }
          
          Operation = typeOperCombo.getSelectionModel().getSelectedItem();
          
        if(Operation == Constantes.INITIAL_STOCK || Operation == Constantes.ACHAT || Operation == Constantes.PERTE || Operation == Constantes.VENTE)  
        {
             txtQte.setDisable(false);
        txtPrix.setDisable(false);
        txtMontant.setDisable(true);
        txtCode.setDisable(false);
        
        }else{
        
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        txtCode.setDisable(false);
        
        }
          
    }

    private void ajouter(ActionEvent event) throws ParseException {
    }

    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();    
    }

    
    void clear(){
    
    listeDetailOperation.clear();    
        
    
    fourCombo.getSelectionModel().select(-1);
    codeCompteCombo.getSelectionModel().clearSelection();
    typeOperCombo.getSelectionModel().select(-1);
    transitCombo.getSelectionModel().select(-1);
    dateOper.setValue(null);
    txtArticle.clear();
    txtMontTransit.clear();
    txtDesc.clear();
    txtCode.clear();
    txtDossier.clear();
    txtMontant.clear();
    txtPrix.clear();
    txtQte.clear();
    txtAutreFais.clear();
    txtAutreFaisPaye.clear();
    txtFraisTransport.clear();
    txtFraisTransportPaye.clear();
    txtMontTransit.clear();
    txtMontantFacture.clear();
    txtMontantTotal.clear();
    txtMontantTotalPaye.clear();
    
    
        btnRafrechaire.setDisable(false);
        dateOper.setDisable(false);
        btnAjouterTable.setDisable(false);
        txtDesc.setDisable(true);
        typeOperCombo.setDisable(false);
        txtDossier.setDisable(true);            
        txtQte.setDisable(true);
        txtCode.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(true);
        codeCompteCombo.setDisable(true);
        transitCombo.setDisable(true);
        txtMontTransit.setDisable(true);
        
        labMont.setText("");
        labPrix.setText("");
        labQte.setText("");
        labMontTransit.setText("");
        labAutreFrais.setText("");
        labAutreFraisPaye.setText("");
        labFraisTransport.setText("");
        labMontTotalPaye.setText("");
        lablabFraisTransportPaye.setText("");
        
        
  

        
    }

    @FXML
    private void typeOperOnAction(ActionEvent event) {
        
        
        
      valeur= typeOperCombo.getSelectionModel().getSelectedItem();
            if ( valeur == Constantes.INITIAL_STOCK){
             
        txtDossier.setDisable(true);          
        txtQte.setDisable(false);
        txtCode.setDisable(false);
        txtPrix.setDisable(false);
        txtMontant.setDisable(true);
        codeCompteCombo.setDisable(true);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
           
            }   else if ( valeur == Constantes.INITIAL_CAISSE){
            
        txtDossier.setDisable(true);           
        txtQte.setDisable(true);
        txtCode.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);    
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
            }else if ( valeur == Constantes.PERTE ){
            
        txtDossier.setDisable(true);           
        txtQte.setDisable(false);
        txtCode.setDisable(false);
        txtPrix.setDisable(false);
        txtMontant.setDisable(true);
        codeCompteCombo.setDisable(true);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
             }else if ( valeur == Constantes.VENTE ){
            
        txtDossier.setDisable(true);            
        txtQte.setDisable(false);
        txtCode.setDisable(false);
        txtPrix.setDisable(false);
        txtMontant.setDisable(true);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
             }else if(valeur == Constantes.ACHAT){
                
        txtDossier.setDisable(false);   
        txtQte.setDisable(false);
        txtCode.setDisable(false);
        txtPrix.setDisable(false);
        txtMontant.setDisable(true);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(false);
        txtFraisTransport.setDisable(false);
        txtAutreFais.setDisable(false);
        txtMontantTotalPaye.setDisable(false);
        txtFraisTransportPaye.setDisable(false);
        txtAutreFaisPaye.setDisable(false);
        
       
                
             }else if( valeur == Constantes.VIREMENT){
             
        txtDossier.setDisable(true); 
        txtCode.setDisable(true);
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
         }else if( valeur == Constantes.DEPENSE){
         
         
        txtDossier.setDisable(true); 
        txtCode.setDisable(true);
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
        }else if( valeur == Constantes.REGLEMENT){
         
         
        txtDossier.setDisable(true); 
        txtCode.setDisable(true);
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false); 
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
        }else if (valeur == Constantes.VIREMENT_ACHAT){
         
        txtDossier.setDisable(false);         
        txtCode.setDisable(true);
        txtQte.setDisable(true);
        txtPrix.setDisable(true);
        txtMontant.setDisable(false);
        codeCompteCombo.setDisable(false);
        txtMontTransit.setDisable(true);
        transitCombo.setDisable(true);
        txtDesc.setDisable(false);
        fourCombo.setDisable(true);
        txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
             }
        
    }


    @FXML
    private void codeArtOnAction(KeyEvent event) {
        
            if (event.getCode().equals(KeyCode.ENTER)){
            

               Article article = articleDAO.findCodeArtByOperation(txtCode.getText());
                if (article!=null){
                txtArticle.setText(article.getLibelle());
                }else{
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.VERIFIER_ARTICLE);
                }
            }
    
    
}

    @FXML
    private void qteOnKeyReleased(KeyEvent event) {
        
          
   labQte.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

       
     labQte.setText(df.format(new BigDecimal(txtQte.getText()).setScale(2,RoundingMode.DOWN)));
     
        
    }

    @FXML
    private void prixOnKeyReleased(KeyEvent event) {
        
         labPrix.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

       
     labPrix.setText(df.format(new BigDecimal(txtPrix.getText()).setScale(2,RoundingMode.DOWN)));

     
//Calcule Montant Total 
     
       if (event.getCode().equals(KeyCode.ENTER) )
            {
     
        BigDecimal qte = BigDecimal.ZERO;
        BigDecimal prix = BigDecimal.ZERO;
        BigDecimal montant = BigDecimal.ZERO;
        montantTotalC= BigDecimal.ZERO;
        
        qte = new BigDecimal(txtQte.getText()) ;
        prix = new BigDecimal(txtPrix.getText()) ;
        
        montant = qte.multiply(prix);
        montantTotalC = montant;
   
        
        txtMontant.setText(df.format(montant)+"");
            }
 
    }

    @FXML
    private void montOnKeyReleased(KeyEvent event) {
        
         labMont.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     labMont.setText(df.format(new BigDecimal(txtMontant.getText()).setScale(2,RoundingMode.DOWN)));
     
        
    }

    @FXML
    private void valider(ActionEvent event) throws ParseException {
        

           LocalDate localDate=dateOper.getValue();
           Date dateSaisieOper=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
           if(operation.getTypeAction()!=null){
               
             if (operation.getTypeAction().equals(Constantes.ACHAT)){
                 
  if ( txtMontTransit.equals("")||transitCombo.getSelectionModel().getSelectedItem()==null ||
        txtFraisTransport.equals("")||
        txtMontantTotalPaye.equals("")||
        txtFraisTransportPaye.equals("")||
         txtMontantFacture.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                 
//###################################################### Detail Operation ############################################################################################################################################     
         
List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_ACHAT);
                      
                      detailOperationDAO.edit(detailOperation);
  
//###################################################### Situation Stock ############################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(operation.getDateOpration() ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                BigDecimal Final = BigDecimal.ZERO;
                    
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(operation.getDateOpration());
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalAchat(detailOperation.getQuantite());
                situationStockTmp.setPrixMoyen(detailOperation.getPrixUnitair().add(prixMoyen));
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                
                  Final= ((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                
                situationStockTmp.setQuantiteTotalFinal(Final);
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                    BigDecimal PrixMoyen = BigDecimal.ZERO;
                 BigDecimal valeur =situationStock.getQuantiteTotalAchat();

                 situationStock.setQuantiteTotalAchat(valeur.add(detailOperation.getQuantite()));
                 
                 PrixMoyen= ((situationStock.getQuantiteTotalFinal().multiply(situationStock.getPrixMoyen())).add(detailOperation.getQuantite()).multiply((detailOperation.getPrixUnitair()).add(prixMoyen))).divide(situationStock.getQuantiteTotalFinal().add(detailOperation.getQuantite()),2,RoundingMode.FLOOR);
                 
                 situationStock.setPrixMoyen(PrixMoyen);
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
//######################################################## Situation Caisse #########################################################################################################################################           


                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(detailOperation.getMontant());
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantAchat();

                situationCaisse.setMontantAchat(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }
                 }
//###################################################### Compte Client Montant Total Achat  ##################################################################################################################################

      String compteClientTm =mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                   DetailCompteClient detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(operation.getDateOpration());
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_MONTANT+" "+Constantes.OPERATION_NUM+operation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtMontantTotalPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);
//###################################################### Compte Client Frais Transport Achat  ##################################################################################################################################

    

                    detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(operation.getDateOpration());
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_FRAIS_TRANSPORT+" "+Constantes.OPERATION_NUM+operation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtFraisTransportPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);
//###################################################### Compte Client Autre Frais Achat  ##################################################################################################################################

 if (!txtAutreFais.getText().equals("") && !txtAutreFaisPaye.getText().equals("")){

                    detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(operation.getDateOpration());
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_AUTRE_FRAIS+" "+Constantes.OPERATION_NUM+operation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtAutreFaisPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);

  }
//###################################################### Compte Transit Achat ##################################################################################################################################
 

      String compteClientTmp =mapLibelle.get(transitCombo.getSelectionModel().getSelectedItem());
                 
                   DetailCompteClient detailCompteClientT = new DetailCompteClient();
                   
                   detailCompteClientT.setDateOpration(operation.getDateOpration());
                   detailCompteClientT.setCode(compteClientTmp);
                   detailCompteClientT.setDesignation(Constantes.DESIGNATION_TRANSIT+" "+Constantes.OPERATION_NUM+operation.getCodeOperation());
                   detailCompteClientT.setMontantCredit(new BigDecimal (txtMontTransit.getText()));
                   detailCompteClientT.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientT.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientT.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientT);
                   
//###################################################### Compte Fournisseur Achat ##################################################################################################################################
 

      String compteFour =mapLibelle.get(fourCombo.getSelectionModel().getSelectedItem());
                 
                   DetailCompteClient detailCompteClientF = new DetailCompteClient();
                   
                   detailCompteClientF.setDateOpration(operation.getDateOpration());
                   detailCompteClientF.setCode(compteFour);
                   detailCompteClientF.setDesignation(Constantes.DESIGNATION_FOURNISSEUR+" "+Constantes.OPERATION_NUM+operation.getCodeOperation());
                   detailCompteClientF.setMontantCredit(montantTA);
                   detailCompteClientF.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientF.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientF.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientF);
                   
                   
             nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                    Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                
                Incrementation();
                clear();
  }
             }
             
             else if  (operation.getTypeAction().equals(Constantes.VENTE)){ 
                 
                 String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
                 CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     

List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VENTE);
                      
                      detailOperationDAO.edit(detailOperation);

                 
//###################################################### Situation Stock ############################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(operation.getDateOpration() ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(operation.getDateOpration());
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalVente();

                 situationStock.setQuantiteTotalVente(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
//######################################################## Situation Caisse #########################################################################################################################################           
                
         
                 SituationStock situationStockTP = situationStockDAO.findDateCodeArtBySituationStock(operation.getDateOpration() ,detailOperation.getCodeArticle());

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(detailOperation.getMontant());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation((detailOperation.getPrixUnitair().subtract(situationStockTP.getPrixMoyen())).multiply(detailOperation.getQuantite()));
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantVente();

                 situationCaisse.setMontantVente(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantProfilOperation((detailOperation.getPrixUnitair().subtract(situationStockTP.getPrixMoyen())).multiply(detailOperation.getQuantite()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                 situationCaisseDAO.edit(situationCaisse);

                }
                
//###################################################### Compte Client Vente ##################################################################################################################################


          if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(operation.getDateOpration());
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_VENTE+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(detailOperation.getMontant());
                   detailCompteClient.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);

            }
                     }

                nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.INITIAL_STOCK)){
      
//###################################################### Detail Operation ############################################################################################################################################     
         
               List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_INITIAL_STOCK);
                      
                      detailOperationDAO.edit(detailOperation);
 
//###################################################### Situation Stock ######################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(operation.getDateOpration(),detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(operation.getDateOpration());
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalInitial();

                 situationStock.setQuantiteTotalInitial(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }  
             }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
             
             }else if  (operation.getTypeAction().equals(Constantes.INITIAL_CAISSE)){ 
         
                 String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
                 CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
              List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_INITIAL_CAISSE);
                      
                      detailOperationDAO.edit(detailOperation);

//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(detailOperation.getMontant());
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantInitial();

                 situationCaisse.setMontantInitial(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                 situationCaisseDAO.edit(situationCaisse);

                } 

//###################################################### Compte Client Caisse ##################################################################################################################################

          if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(operation.getDateOpration());
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_CAISSE+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(detailOperation.getMontant());
                   detailCompteClient.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);

            }
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.PERTE)){ 
                 
//###################################################### Detail Operation ############################################################################################################################################     
         
              List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_PERTE);
                      
                      detailOperationDAO.edit(detailOperation);

//###################################################### Situation Stock ######################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(operation.getDateOpration() ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(operation.getDateOpration());
                situationStockTmp.setQuantiteTotalPerte(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalPerte();

                 situationStock.setQuantiteTotalPerte(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
                    }
                    
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.DEPENSE)){
                 
      String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
          List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_DEPENCE);
                      
                      detailOperationDAO.edit(detailOperation);

//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(detailOperation.getMontant());
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantDepence();

                situationCaisse.setMontantDepence(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }      
                
//###################################################### Compte Client Depense ##################################################################################################################################

             if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(operation.getDateOpration());
                   detailCompteClient.setCode(code);
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_DEPENSE+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
             }
                    }
                 
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.REGLEMENT)){
     
      String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
              List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_REGLEMENT);
                      
                      detailOperationDAO.edit(detailOperation);
         
//######################################################## Situation Caisse #################################################################################################################################           
                     
                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setReglement(detailOperation.getMontant());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getReglement()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getReglement();

                situationCaisse.setReglement(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getReglement()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }      
                
//###################################################### Compte Client Reglement ##################################################################################################################################

    
    CompteClient compteClientTmp = compteClientDAO.findByNotCompteCaisse();


             if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(operation.getDateOpration());
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_REGLEMENT+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
                   
                   
              
                   DetailCompteClient detailCompteClientTmp = new DetailCompteClient();
                   
                   detailCompteClientTmp.setDateOpration(operation.getDateOpration());
                   detailCompteClientTmp.setCode(compteClientTmp.getCode());
                   detailCompteClientTmp.setDesignation(Constantes.DESIGNATION_VENTE+detailOperation.getCodeOperation());
                   detailCompteClientTmp.setMontantCredit(detailOperation.getMontant());
                   detailCompteClientTmp.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientTmp.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientTmp.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientTmp);
                
}
                 
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.VIREMENT)){
                 
    String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
           List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VIREMENT);
                      
                      detailOperationDAO.edit(detailOperation);
            
                 
//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(detailOperation.getMontant());
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                BigDecimal valeur =situationCaisse.getMontantVirement();

                situationCaisse.setMontantVirement(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }     

//###################################################### Compte Client Virement ##################################################################################################################################
  

        if (compteClient!=null){


                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                detailCompteClient.setDateOpration(operation.getDateOpration());
                detailCompteClient.setCode(compteClient.getCode());
                detailCompteClient.setDesignation(Constantes.DESIGNATION_VIREMENT+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeCompte());
                detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                detailCompteClient.setMontantDebit(detailOperation.getMontant());
                detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                detailCompteClient.setDateCreation(new Date());
                   
                detailCompteClientDAO.add(detailCompteClient);
          }
                    }
                
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (operation.getTypeAction().equals(Constantes.VIREMENT_ACHAT)){  
                 
  String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
  CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     

            List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VIREMENT_ACHAT);
                      
                      detailOperationDAO.edit(detailOperation);
                
//######################################################## Situation Caisse #################################################################################################################################           
                
                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(operation.getDateOpration());
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(operation.getDateOpration());
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(detailOperation.getMontant());
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantVirementAchat();

                 situationCaisse.setMontantVirementAchat(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }   
                
//###################################################### Compte Client Virement Achat ##################################################################################################################################


                if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(operation.getDateOpration());
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_VIREMENT_ACHAT+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
          }
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }
             
             
           }
             else{
              if (valeur.equals(Constantes.ACHAT)){
                 
  if ( txtMontTransit.equals("")||transitCombo.getSelectionModel().getSelectedItem()==null ||
        txtFraisTransport.equals("")||
        txtMontantTotalPaye.equals("")||
        txtFraisTransportPaye.equals("")||
         txtMontantFacture.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{
        
                 
//###################################################### Detail Operation ############################################################################################################################################     
         
List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_ACHAT);
                      
                      detailOperationDAO.edit(detailOperation);
  
//###################################################### Situation Stock ############################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(dateSaisieOper ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                BigDecimal Final = BigDecimal.ZERO;
                    
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(dateSaisieOper);
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalAchat(detailOperation.getQuantite());
                situationStockTmp.setPrixMoyen(detailOperation.getPrixUnitair().add(prixMoyen));
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                
                  Final= ((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                
                situationStockTmp.setQuantiteTotalFinal(Final);
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                    BigDecimal PrixMoyen = BigDecimal.ZERO;
                 BigDecimal valeur =situationStock.getQuantiteTotalAchat();

                 situationStock.setQuantiteTotalAchat(valeur.add(detailOperation.getQuantite()));
                 
                 PrixMoyen= ((situationStock.getQuantiteTotalFinal().multiply(situationStock.getPrixMoyen())).add(detailOperation.getQuantite()).multiply((detailOperation.getPrixUnitair()).add(prixMoyen))).divide(situationStock.getQuantiteTotalFinal().add(detailOperation.getQuantite()),2,RoundingMode.FLOOR);
                 
                 situationStock.setPrixMoyen(PrixMoyen);
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
//######################################################## Situation Caisse #########################################################################################################################################           


                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(detailOperation.getMontant());
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantAchat();

                situationCaisse.setMontantAchat(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }
                 }
//###################################################### Compte Client Montant Total Achat  ##################################################################################################################################

      String compteClientTm =mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                   DetailCompteClient detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(dateSaisieOper);
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_MONTANT+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtMontantTotalPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);
                   
//###################################################### Compte Client Frais Transport Achat  ##################################################################################################################################

    

                    detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(dateSaisieOper);
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_FRAIS_TRANSPORT+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtFraisTransportPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);
//###################################################### Compte Client Autre Frais Achat  ##################################################################################################################################

 if (!txtAutreFais.getText().equals("") && !txtAutreFaisPaye.getText().equals("")){

                    detailCompteClientC = new DetailCompteClient();
                   
                   detailCompteClientC.setDateOpration(dateSaisieOper);
                   detailCompteClientC.setCode(compteClientTm);
                   detailCompteClientC.setDesignation(Constantes.DESIGNATION_AUTRE_FRAIS+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClientC.setMontantCredit(new BigDecimal(txtAutreFaisPaye.getText()));
                   detailCompteClientC.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientC.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientC.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientC);

  }
//###################################################### Compte Transit Achat ##################################################################################################################################
 

      String compteClientTmp =mapLibelle.get(transitCombo.getSelectionModel().getSelectedItem());
                 
                   DetailCompteClient detailCompteClientT = new DetailCompteClient();
                   
                   detailCompteClientT.setDateOpration(dateSaisieOper);
                   detailCompteClientT.setCode(compteClientTmp);
                   detailCompteClientT.setDesignation(Constantes.DESIGNATION_TRANSIT+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClientT.setMontantCredit(new BigDecimal (txtMontTransit.getText()));
                   detailCompteClientT.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientT.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientT.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientT);
                   
//###################################################### Compte Fournisseur Achat ##################################################################################################################################
 

      String compteFour =mapLibelle.get(fourCombo.getSelectionModel().getSelectedItem());
                 
                   DetailCompteClient detailCompteClientF = new DetailCompteClient();
                   
                   detailCompteClientF.setDateOpration(dateSaisieOper);
                   detailCompteClientF.setCode(compteFour);
                   detailCompteClientF.setDesignation(Constantes.DESIGNATION_FOURNISSEUR+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClientF.setMontantCredit(montantTA);
                   detailCompteClientF.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientF.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientF.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientF);
                   
                   
             nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                    Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                
                Incrementation();
                clear();
  }
    
             }else if  (valeur.equals(Constantes.VENTE)){ 
                 
                 String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
                 CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     

List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VENTE);
                      
                      detailOperationDAO.edit(detailOperation);

                 
//###################################################### Situation Stock ############################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(dateSaisieOper ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(dateSaisieOper);
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalVente();

                 situationStock.setQuantiteTotalVente(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
//######################################################## Situation Caisse #########################################################################################################################################           
                
         
                 SituationStock situationStockTP = situationStockDAO.findDateCodeArtBySituationStock(dateSaisieOper ,detailOperation.getCodeArticle());

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(detailOperation.getMontant());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation((detailOperation.getPrixUnitair().subtract(situationStockTP.getPrixMoyen())).multiply(detailOperation.getQuantite()));
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantVente();

                 situationCaisse.setMontantVente(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantProfilOperation((detailOperation.getPrixUnitair().subtract(situationStockTP.getPrixMoyen())).multiply(detailOperation.getQuantite()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                 situationCaisseDAO.edit(situationCaisse);

                }
                
//###################################################### Compte Client Vente ##################################################################################################################################


          if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(dateSaisieOper);
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_VENTE+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(detailOperation.getMontant());
                   detailCompteClient.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);

            }
                     }

                nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.INITIAL_STOCK)){
      
//###################################################### Detail Operation ############################################################################################################################################     
         
                 List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_INITIAL_STOCK);
                      
                      detailOperationDAO.edit(detailOperation);
 
//###################################################### Situation Stock ######################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(dateSaisieOper,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setQuantiteTotalPerte(BigDecimal.ZERO);
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(dateSaisieOper);
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalInitial();

                 situationStock.setQuantiteTotalInitial(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }  
             }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
             
             }else if  (valeur.equals(Constantes.INITIAL_CAISSE)){ 
         
                 String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
                 CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
                    List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_INITIAL_CAISSE);
                      
                      detailOperationDAO.edit(detailOperation);

//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(detailOperation.getMontant());
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantInitial();

                 situationCaisse.setMontantInitial(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                 situationCaisseDAO.edit(situationCaisse);

                } 

//###################################################### Compte Client Caisse ##################################################################################################################################

          if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(dateSaisieOper);
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_CAISSE+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(detailOperation.getMontant());
                   detailCompteClient.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);

            }
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.PERTE)){ 
                 
//###################################################### Detail Operation ############################################################################################################################################     
         
                   List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_PERTE);
                      
                      detailOperationDAO.edit(detailOperation);

//###################################################### Situation Stock ######################################################################################################################################

                SituationStock situationStock = situationStockDAO.findDateCodeArtBySituationStock(dateSaisieOper ,detailOperation.getCodeArticle());
                
                if(situationStock ==null){
               
                SituationStock situationStockTmp= new SituationStock();
               
                situationStockTmp.setCodeArticle(detailOperation.getCodeArticle());
                situationStockTmp.setArticle(detailOperation.getArticle());
                situationStockTmp.setDateCreation(new Date());
                situationStockTmp.setDateOpration(dateSaisieOper);
                situationStockTmp.setQuantiteTotalPerte(detailOperation.getQuantite());
                situationStockTmp.setQuantiteTotalAchat(BigDecimal.ZERO);
                situationStockTmp.setPrixMoyen(BigDecimal.ZERO);
                situationStockTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationStockTmp.setQuantiteTotalInitial(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalVente(BigDecimal.ZERO);
                situationStockTmp.setQuantiteTotalFinal((situationStockTmp.getQuantiteTotalAchat().add(situationStockTmp.getQuantiteTotalInitial())).subtract(situationStockTmp.getQuantiteTotalVente().add(situationStockTmp.getQuantiteTotalPerte())));
                situationStockDAO.add(situationStockTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationStock.getQuantiteTotalPerte();

                 situationStock.setQuantiteTotalPerte(valeur.add(detailOperation.getQuantite()));
                 situationStock.setQuantiteTotalFinal((situationStock.getQuantiteTotalAchat().add(situationStock.getQuantiteTotalInitial())).subtract(situationStock.getQuantiteTotalVente().add(situationStock.getQuantiteTotalPerte())));
                 situationStockDAO.edit(situationStock);

                }
                    }
                    
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.DEPENSE)){
                 
      String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
               List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_DEPENCE);
                      
                      detailOperationDAO.edit(detailOperation);

//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(detailOperation.getMontant());
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantDepence();

                situationCaisse.setMontantDepence(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }      
                
//###################################################### Compte Client Depense ##################################################################################################################################

             if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(dateSaisieOper);
                   detailCompteClient.setCode(code);
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_DEPENSE+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
             }
                    }
                 
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.REGLEMENT)){
     
      String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
                  List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_REGLEMENT);
                      
                      detailOperationDAO.edit(detailOperation);
         
//######################################################## Situation Caisse #################################################################################################################################           
                     
                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setReglement(detailOperation.getMontant());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getReglement()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getReglement();

                situationCaisse.setReglement(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getReglement()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }      
                
//###################################################### Compte Client Reglement ##################################################################################################################################

    
    CompteClient compteClientTmp = compteClientDAO.findByNotCompteCaisse();


             if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(dateSaisieOper);
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_REGLEMENT+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
                   
                   
              
                   DetailCompteClient detailCompteClientTmp = new DetailCompteClient();
                   
                   detailCompteClientTmp.setDateOpration(dateSaisieOper);
                   detailCompteClientTmp.setCode(compteClientTmp.getCode());
                   detailCompteClientTmp.setDesignation(Constantes.DESIGNATION_VENTE+detailOperation.getCodeOperation());
                   detailCompteClientTmp.setMontantCredit(detailOperation.getMontant());
                   detailCompteClientTmp.setMontantDebit(BigDecimal.ZERO);
                   detailCompteClientTmp.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClientTmp.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClientTmp);
                
}
                 
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.VIREMENT)){
                 
    String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
      CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     
         
                  List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VIREMENT);
                      
                      detailOperationDAO.edit(detailOperation);
            
                 
//######################################################## Situation Caisse #################################################################################################################################           

                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(detailOperation.getMontant());
                situationCaisseTmp.setMontantVirementAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                }else {
                
                BigDecimal valeur =situationCaisse.getMontantVirement();

                situationCaisse.setMontantVirement(valeur.add(detailOperation.getMontant()));
                situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }     

//###################################################### Compte Client Virement ##################################################################################################################################
  

        if (compteClient!=null){


                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                detailCompteClient.setDateOpration(dateSaisieOper);
                detailCompteClient.setCode(compteClient.getCode());
                detailCompteClient.setDesignation(Constantes.DESIGNATION_VIREMENT+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeCompte());
                detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                detailCompteClient.setMontantDebit(detailOperation.getMontant());
                detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                detailCompteClient.setDateCreation(new Date());
                   
                detailCompteClientDAO.add(detailCompteClient);
          }
                    }
                
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }else if  (valeur.equals(Constantes.VIREMENT_ACHAT)){  
                 
  String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());
  CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

//###################################################### Detail Operation ############################################################################################################################################     

                     List<DetailOperation> listDetailOperations = detailOperationDAO.findDateByDetailOperation(txtNumOper.getText());

                 for (int i = 0; i< listDetailOperations.size(); i++) {
                     
                      DetailOperation detailOperation = listDetailOperations.get(i);
                      detailOperation.setEtat(Constantes.ETAT_REEL);
                      detailOperation.setCodeOperation(txtNumOper.getText()+Constantes.C_VIREMENT_ACHAT);
                      
                      detailOperationDAO.edit(detailOperation);
                
//######################################################## Situation Caisse #################################################################################################################################           
                
                SituationCaisse  situationCaisse = situationCaisseDAO.findDateBySituationCaisse(dateSaisieOper);
                
                if(situationCaisse ==null){
               
                SituationCaisse situationCaisseTmp= new SituationCaisse();
               
                situationCaisseTmp.setMontantAchat(BigDecimal.ZERO);
                situationCaisseTmp.setMontantDepence(BigDecimal.ZERO);
                situationCaisseTmp.setMontantInitial(BigDecimal.ZERO);
                situationCaisseTmp.setReglement(BigDecimal.ZERO);
                situationCaisseTmp.setDateOpration(dateSaisieOper);
                situationCaisseTmp.setDateCreation(new Date());
                situationCaisseTmp.setUtilisateurCreation(nav.getUtilisateur());
                situationCaisseTmp.setMontantVente(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirement(BigDecimal.ZERO);
                situationCaisseTmp.setMontantVirementAchat(detailOperation.getMontant());
                situationCaisseTmp.setMontantProfilOperation(BigDecimal.ZERO);
                situationCaisseTmp.setMontantFinal((situationCaisseTmp.getMontantInitial().add(situationCaisseTmp.getMontantVente()).add(situationCaisseTmp.getMontantProfilOperation())).subtract(situationCaisseTmp.getMontantAchat().add(situationCaisseTmp.getMontantVirement().add(situationCaisseTmp.getMontantDepence().add(situationCaisseTmp.getMontantVirementAchat())))));
                situationCaisseDAO.add(situationCaisseTmp);
                
                
                }else {
                
                 BigDecimal valeur =situationCaisse.getMontantVirementAchat();

                 situationCaisse.setMontantVirementAchat(valeur.add(detailOperation.getMontant()));
                 situationCaisse.setMontantFinal((situationCaisse.getMontantInitial().add(situationCaisse.getMontantVente()).add(situationCaisse.getMontantProfilOperation())).subtract(situationCaisse.getMontantAchat().add(situationCaisse.getMontantVirement().add(situationCaisse.getMontantDepence().add(situationCaisse.getMontantVirementAchat())))));
                situationCaisseDAO.edit(situationCaisse);

                }   
                
//###################################################### Compte Client Virement Achat ##################################################################################################################################


                if (compteClient!=null){

                   DetailCompteClient detailCompteClient = new DetailCompteClient();
                   
                   detailCompteClient.setDateOpration(dateSaisieOper);
                   detailCompteClient.setCode(compteClient.getCode());
                   detailCompteClient.setDesignation(Constantes.DESIGNATION_VIREMENT_ACHAT+detailOperation.getArticle()+" "+Constantes.OPERATION_NUM+detailOperation.getCodeOperation());
                   detailCompteClient.setMontantCredit(BigDecimal.ZERO);
                   detailCompteClient.setMontantDebit(detailOperation.getMontant());
                   detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
                   detailCompteClient.setDateCreation(new Date());
                   
                   detailCompteClientDAO.add(detailCompteClient);
          }
                    }
                 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OPERATION);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();
                clear();
                 
             }  
        
             
    }}

    @FXML
    private void rafraichir(ActionEvent event) {
        
    }

    @FXML
    private void transitOnAction(ActionEvent event) {
    }

    @FXML
    private void ajouterTable(ActionEvent event) throws ParseException {
        
 if (typeOperCombo.getSelectionModel().getSelectedIndex()== -1 || txtMontant.getText().isEmpty()){
          
           nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
          
          }else{
              
                   LocalDate localDate=dateOper.getValue();
                Date dateSaisieOper=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                
                Journee journee = journeeDAO.findJourneeByDate(dateSaisieOper, Constantes.ETAT_STATUT_OVERTE);
                
                                  if (journee == null ){
                     
                     nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.MESSAGE_ALERT_FAUX_DATE);
                     
                 } 
                 
                 else{
                     
                                      System.out.println("operation.getTypeAction()"+operation.getTypeAction());  
                                      
                   if (operation.getTypeAction()!=null){       
          if ( operation.getTypeAction().equals(Constantes.ACHAT)){
             
               if ( txtCode.equals("")|| dateOper.getValue()== null|| txtQte.equals("") || txtPrix.equals("")|| txtDossier.equals("")|| txtNumOper.equals("")|| txtArticle.equals("")|| codeCompteCombo.getSelectionModel().getSelectedItem()==null){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(operation.getDateOpration());
                detailOperation.setCodeArticle(txtCode.getText());
                detailOperation.setCodeCompte(operation.getCodeCompte());
                detailOperation.setCodeCompteFour(operation.getCodeCompteFour());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setNumDossier(operation.getNumDossier());
                detailOperation.setArticle(txtArticle.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setEtat(Constantes.ETAT_BROUILLONS);
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
                detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
                detailOperation.setTypeAction(operation.getTypeAction());
                detailOperation.setMontant(montantTotalC);
                 
                detailOperationDAO.add(detailOperation);


                setColumnProperties();
                loadDetail();

//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));
                   
                   
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                codeCompteCombo.setDisable(true);
                txtDossier.setDisable(true);
                transitCombo.setDisable(false);
                txtMontTransit.setDisable(false);
                txtFraisTransport.setDisable(false);
                txtAutreFais.setDisable(false);
                txtMontantTotalPaye.setDisable(false);
                txtFraisTransportPaye.setDisable(false);
                txtAutreFaisPaye.setDisable(false);
                
                
                txtCode.clear();
                txtArticle.clear();
                txtQte.clear();
                txtPrix.clear();
                txtMontant.clear();
                txtDesc.clear();
                labQte.setText("");
                labPrix.setText("");
       

               }
          }
               }else{
           if (valeur.equals(Constantes.ACHAT)){
             
               if ( txtCode.equals("")|| dateOper.getValue()== null|| txtQte.equals("") || txtPrix.equals("")|| txtDossier.equals("")|| txtNumOper.equals("")|| txtArticle.equals("")|| codeCompteCombo.getSelectionModel().getSelectedItem()==null){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                                   
                String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

                
                String codeFour = mapLibelle.get(fourCombo.getSelectionModel().getSelectedItem());

                CompteClient compteFour = compteClientDAO.findDateCodeClientByDetailCompteClient(codeFour);
                
                
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeArticle(txtCode.getText());
                detailOperation.setCodeCompte(compteClient.getLibelle());
                detailOperation.setCodeCompteFour(compteFour.getLibelle());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setNumDossier(txtDossier.getText());
                detailOperation.setArticle(txtArticle.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setEtat(Constantes.ETAT_BROUILLONS);
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
                detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(montantTotalC);
                 
                detailOperationDAO.add(detailOperation);


                setColumnProperties();
                loadDetail();

//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));
                   
                   
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                codeCompteCombo.setDisable(true);
                txtDossier.setDisable(true);
                transitCombo.setDisable(false);
                txtMontTransit.setDisable(false);
                txtFraisTransport.setDisable(false);
                txtAutreFais.setDisable(false);
                txtMontantTotalPaye.setDisable(false);
                txtFraisTransportPaye.setDisable(false);
                txtAutreFaisPaye.setDisable(false);
                
                
                txtCode.clear();
                txtArticle.clear();
                txtQte.clear();
                txtPrix.clear();
                txtMontant.clear();
                txtDesc.clear();
                labQte.setText("");
                labPrix.setText("");
       

               }
                  
             }else if  (valeur.equals(Constantes.VENTE)){ 
                 
            if ( txtCode.equals("")|| dateOper.getValue()== null|| txtQte.equals("") || txtPrix.equals("") || txtMontant.equals("") || txtNumOper.equals("")|| txtArticle.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
            }else{

                
         String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

         CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);
                
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeArticle(txtCode.getText());
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setArticle(txtArticle.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
                detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(montantTotalC);
                
                detailOperationDAO.add(detailOperation);

                setColumnProperties();
                loadDetail();

//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));   
                
                
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtCode.setDisable(true);
                txtQte.setDisable(true);
                txtPrix.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
            }
            
                }else if  (valeur.equals(Constantes.PERTE)){ 
                 
            if ( txtCode.equals("")|| dateOper.getValue()== null|| txtQte.equals("") || txtPrix.equals("") || txtMontant.equals("") || txtNumOper.equals("")|| txtArticle.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
            }else{

                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeArticle(txtCode.getText());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setArticle(txtArticle.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
                detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(montantTotalC);
                
                detailOperationDAO.add(detailOperation);
                
                setColumnProperties();
                loadDetail();
                
                
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));   
                
                
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                txtCode.setDisable(true);
                txtQte.setDisable(true);
                txtPrix.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
            }
             }else if  (valeur.equals(Constantes.INITIAL_STOCK)){
                 
            if ( txtCode.equals("")|| dateOper.getValue()== null|| txtQte.equals("") || txtPrix.equals("") || txtMontant.equals("") || txtNumOper.equals("")|| txtArticle.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{


                
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeArticle(txtCode.getText());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setArticle(txtArticle.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
                detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
                detailOperation.setTypeAction(valeur);
                
                detailOperation.setMontant(montantTotalC);
                
                detailOperationDAO.add(detailOperation);

                setColumnProperties();
                loadDetail();
                
                
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));             
                
                
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                dateOper.setDisable(true);
                txtCode.setDisable(true);
                txtQte.setDisable(true);
                txtPrix.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                txtFraisTransport.setDisable(true);
                txtAutreFais.setDisable(true);
                txtMontantTotalPaye.setDisable(true);
                txtFraisTransportPaye.setDisable(true);
                txtAutreFaisPaye.setDisable(true);
                
                
                
            }
             } else if  (valeur.equals(Constantes.INITIAL_CAISSE)){
                 
            if ( txtCode.equals("")|| dateOper.getValue()== null|| txtMontant.equals("") || txtNumOper.equals("") ){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                  String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                  CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);
                
                detailOperation = new DetailOperation();
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(new BigDecimal(txtMontant.getText()));
                
                detailOperationDAO.add(detailOperation);
      

                setColumnProperties();
                loadDetail();
                
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));                  
                
                
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
                txtAutreFais.setDisable(true);
                txtMontantTotalPaye.setDisable(true);
                txtFraisTransportPaye.setDisable(true);
                txtAutreFaisPaye.setDisable(true);
            }       
                 
             }else if  (valeur.equals(Constantes.DEPENSE)){
            if (dateOper.getValue()==null|| txtMontant.equals("")|| txtNumOper.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                
                   String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                   CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);
                
                   
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(new BigDecimal(txtMontant.getText()));
                
                detailOperationDAO.add(detailOperation);
                
            }
            
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));      
            

                setColumnProperties();
                loadDetail();

                
                fourCombo.setDisable(true);
                 typeOperCombo.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
                txtAutreFais.setDisable(true);
                txtMontantTotalPaye.setDisable(true);
                txtFraisTransportPaye.setDisable(true);
                txtAutreFaisPaye.setDisable(true);
            
             }else if  (valeur.equals(Constantes.REGLEMENT)){
                 
            if (dateOper.getValue()==null|| txtMontant.equals("")|| txtNumOper.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                                
                String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);
                
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(new BigDecimal(txtMontant.getText()));
                
                detailOperationDAO.add(detailOperation);
                 
                
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));   
            

                setColumnProperties();
                loadDetail();

                
                fourCombo.setDisable(true);
                 typeOperCombo.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
            }
            
             }else if  (valeur.equals(Constantes.VIREMENT)){
            if (  dateOper.getValue()==null|| txtMontant.equals("") || txtNumOper.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                               
                String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

                CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);

                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(new BigDecimal(txtMontant.getText()));
                
                detailOperationDAO.add(detailOperation);
                  

                setColumnProperties();
                loadDetail();

//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));             
                
                
                fourCombo.setDisable(true);
                typeOperCombo.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
        
            }
            
             }else if  (valeur.equals(Constantes.VIREMENT_ACHAT)){
         if (  dateOper.getValue()== null|| txtMontant.equals("")|| txtDossier.equals("")|| txtNumOper.equals("") || txtCode.equals("")){
               
                     nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.CHAMP_OBLIGATOIRE);
               
               }else{

                          
            String code = mapLibelle.get(codeCompteCombo.getSelectionModel().getSelectedItem());

            CompteClient compteClient = compteClientDAO.findDateCodeClientByDetailCompteClient(code);
             
                detailOperation = new DetailOperation();
                
                detailOperation.setDateOpration(dateSaisieOper);
                detailOperation.setCodeCompte(compteClient.getCode());
                detailOperation.setDesignation(txtDesc.getText());
                detailOperation.setCodeOperation(txtNumOper.getText());
                detailOperation.setNumDossier(txtDossier.getText());
                detailOperation.setDateCreation(new Date());
                detailOperation.setUtilisateurCreation(nav.getUtilisateur());
                detailOperation.setTypeAction(valeur);
                detailOperation.setMontant(new BigDecimal(txtMontant.getText()));
                
                detailOperationDAO.add(detailOperation);
                 
                setColumnProperties();
                loadDetail();
                
//CALCUL MONTANT TOTAL CLIENT     
                 montantTA = BigDecimal.ZERO;
                BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < tableOpera.getItems().size(); i++) {
                       
                       DetailOperation detailOperation = tableOpera.getItems().get(i);
                       
                        montTotal = montTotal.add(detailOperation.getMontant());
                        montantTA = montTotal;
                   }
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

           
                   
                txtMontantTotal.setText(df.format(montTotal.setScale(2,RoundingMode.FLOOR)));   
                
                
                fourCombo.setDisable(true);
                 typeOperCombo.setDisable(true);
                 txtDossier.setDisable(true);
                codeCompteCombo.setDisable(true);
                dateOper.setDisable(true);
                txtMontant.setDisable(true);
                txtDesc.setDisable(true);
                btnAjouterTable.setDisable(true);
                btnRafrechaire.setDisable(true);
                txtFraisTransport.setDisable(true);
        txtAutreFais.setDisable(true);
        txtMontantTotalPaye.setDisable(true);
        txtFraisTransportPaye.setDisable(true);
        txtAutreFaisPaye.setDisable(true);
                
         }     
         }
             }
                                  }
          }
    }

    @FXML
    private void modifierTable(ActionEvent event) {
        
        
         DetailOperation detailOperation=listeDetailOperation.get(tableOpera.getSelectionModel().getSelectedIndex());
        

        detailOperation.setQuantite(new BigDecimal(txtQte.getText()));
        detailOperation.setPrixUnitair(new BigDecimal(txtPrix.getText()));
        detailOperation.setMontant(montantTotalC);

               listeDetailOperation.set(tableOpera.getSelectionModel().getSelectedIndex(), detailOperation);
             
                 nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.MODIFIER_ENREGISTREMENT);
             
                 
             txtArticle.clear();
             txtCode.clear();
             txtMontant.clear();
             txtPrix.clear();
             txtQte.clear();
             labPrix.setText("");
             labQte.setText("");
             labMont.setText("");
               
    }

    @FXML
    private void supprimerTable(ActionEvent event) {
        
        if(tableOpera.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listeDetailOperation.size()!=0)
            {
                
                listeDetailOperation.remove(tableOpera.getSelectionModel().getSelectedIndex());

             txtArticle.clear();
             txtCode.clear();
             txtMontant.clear();
             txtPrix.clear();
             txtQte.clear();
             labPrix.setText("");
             labQte.setText("");
             labMont.setText("");
                
            }
        
    }
    }
    
    
    @FXML
    private void montTransitOnKeyReleased(KeyEvent event) {
       
         labMontTransit.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

       
     labMontTransit.setText(df.format(new BigDecimal(txtMontTransit.getText()).setScale(2,RoundingMode.DOWN)));

    }

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void calculeFactureMouseClicked(MouseEvent event) {
      
        BigDecimal montantFact =  BigDecimal.ZERO;
        
        montantFact = montantTA.add(new BigDecimal(txtMontTransit.getText())).add(new BigDecimal(txtFraisTransport.getText())).add(new BigDecimal(txtAutreFais.getText()));
 
          DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     txtMontantFacture.setText(df.format(montantFact.setScale(2,RoundingMode.FLOOR)));
     
//######################################################################## Prix Moyen ###################################################################################################################################### 
     
     BigDecimal montantPrixMoyen =  BigDecimal.ZERO;
        
           BigDecimal montTotal = BigDecimal.ZERO;
                
                   for (int i = 0; i < listeDetailOperation.size(); i++) {
                       
                       DetailOperation detailOperation = listeDetailOperation.get(i);
                       
                        montTotal = montTotal.add(detailOperation.getQuantite());
                       
                   }
     
     
        montantPrixMoyen = new BigDecimal(txtMontTransit.getText()).add(new BigDecimal(txtFraisTransport.getText())).add(new BigDecimal(txtAutreFais.getText()));
        
        prixMoyen = montantPrixMoyen.divide(montTotal,2,RoundingMode.FLOOR);
 

    }

    @FXML
    private void montantTotalPayeReleased(KeyEvent event) {
          labMontTotalPaye.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     labMontTotalPaye.setText(df.format(new BigDecimal(txtMontantTotalPaye.getText()).setScale(2,RoundingMode.DOWN)));

    }

    @FXML
    private void fraisTransportReleased(KeyEvent event) {
          labFraisTransport.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     labFraisTransport.setText(df.format(new BigDecimal(txtFraisTransport.getText()).setScale(2,RoundingMode.DOWN)));
     
    }

    @FXML
    private void autreFraisReleased(KeyEvent event) {
          labAutreFrais.setText("");


       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
       dfs.setDecimalSeparator(',');
       dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     labAutreFrais.setText(df.format(new BigDecimal(txtAutreFais.getText()).setScale(2,RoundingMode.DOWN)));
     
    }

    @FXML
    private void fraisTransportPayeReleased(KeyEvent event) {
          lablabFraisTransportPaye.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     lablabFraisTransportPaye.setText(df.format(new BigDecimal(txtFraisTransportPaye.getText()).setScale(2,RoundingMode.DOWN)));
     
    }

    @FXML
    private void autreFraisPayeReleased(KeyEvent event) {
      labAutreFraisPaye.setText("");


         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

     
     labAutreFraisPaye.setText(df.format(new BigDecimal(txtAutreFaisPaye.getText()).setScale(2,RoundingMode.DOWN)));
     
    }



}