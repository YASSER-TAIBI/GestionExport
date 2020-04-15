package function;


import Controller.LoginController;
import dao.Entity.Utilisateur;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class navigation {

    private final String loadApp="/View/LoadAppFXML.fxml";
    private final String login="/View/Login.fxml";
    private final String home="/View/Home.fxml";
    
    
    private final String listeUtilisateur="/View/Utilisateur/ListeUtilisateur.fxml";
    private final String ajouterUtilisateur="/View/Utilisateur/AjouterUtilisateur.fxml";
    private final String modifierUtilisateur="/View/Utilisateur/ModifierUtilisateur.fxml";
    
    
    private final String article="/View/Article/ListeArticle.fxml";
    
    private final String operation="/View/Operation/SaisirOperation.fxml";
    
    private final String situationStock="/View/Stock/ConsultationStock.fxml";
    
    private final String situationCaisse="/View/Caisse/ConsultationCaisse.fxml";
  
    private final String situationCaisseGlobal="/View/Caisse/SituationGlobalCaisse.fxml";
    
    private final String situationCaissePeriode="/View/Caisse/ConsultationCaissePeriode.fxml";
 
    private final String gererAuthUtilisateur="/View/Referentiel/GererAuthUtilisateur.fxml";

    private final String compteClient="/View/Referentiel/ListeCompte.fxml";
    
    private final String ConsultationCompteClient="/View/Referentiel/ConsultationCompteClient.fxml";
    
    private final String Reglement="/View/Referentiel/Reglement.fxml";
    
    private final String Journee="/View/Journee/CreationJournee.fxml";
    
    private final String situationStockPeriode="/View/Stock/ConsultationStockPeriode.fxml";

    private final String consultationOperation="/View/Operation/ConsultationOperation.fxml";
    
    private final String viewClient="/View/ViewCustomer.fxml"; 
    
    private final String dashboard="/view/Dashboard.fxml";
    
    private final String database="/view/Database.fxml";

    
    private final String user="/View/User.fxml";

    public Utilisateur utilisateur;



    
    public String getGererAuthUtilisateur() {
        return gererAuthUtilisateur;
    }
     

    public navigation() {
        this.utilisateur = LoginController.utilisateur;
    }

    public String getArticle() {
        return article;
    }


    
    public String getLoadApp() {
        return loadApp;
    }

    public String getSituationCaisse() {
        return situationCaisse;
    }

    public String getJournee() {
        return Journee;
    }

    public String getSituationCaisseGlobal() {
        return situationCaisseGlobal;
    }

    public String getSituationStockPeriode() {
        return situationStockPeriode;
    }

    public String getListeUtilisateur() {
        return listeUtilisateur;
    }

    public String getAjouterUtilisateur() {
        return ajouterUtilisateur;
    }

    public String getModifierUtilisateur() {
        return modifierUtilisateur;
    }

    public String getCompteClient() {
        return compteClient;
    }

    public String getConsultationOperation() {
        return consultationOperation;
    }

    public String getConsultationCompteClient() {
        return ConsultationCompteClient;
    }

    public String getOperation() {
        return operation;
    }
       
 //   public Image applicationIcon = new Image(getClass().getResourceAsStream("/img/icons8_Source_Code_104px_2.png"));
    
    public String getViewClient() {
        return viewClient;
    }
    public String getHome(){
        return home;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getDashboard(){
        return dashboard;
    }

    public String getSituationCaissePeriode() {
        return situationCaissePeriode;
    }

    public String getReglement() {
        return Reglement;
    }
    
    public String getDatabase(){
        return database;
    }

    public String getSituationStock() {
        return situationStock;
    }

    public String getUser(){
        return user;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String generCodeCom( int a, String b){
 
      String code = a+b;
      return code;
  }
    
     public String generCode(String b ,int a){
 
      String code = b+a;
      return code;
  }
    
    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
        
    public void harusAngka(final TextField text){
        text.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!text.getText().matches("[0-9]*")){
                    showAlert(AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    text.setText("");
                    text.requestFocus();
                }
            }
        });
        
    }
            
    public void animationFade(Node e){
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    private void Code() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
