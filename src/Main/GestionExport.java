/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import function.navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GestionExport extends Application  {

    
      

    navigation nav =new navigation();
    
     

    public void start(Stage stage) throws Exception {
      Utils.HibernateUtil.openSession();
        Parent root = FXMLLoader.load(getClass().getResource(nav.getLoadApp()));
        

       
        Scene scene = new Scene(root);
        String css = GestionExport.class.getResource("/Styles/css/FigureDeStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
        stage.getIcons().add(image);
        stage.setTitle("Gestion Export");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        
//        stage.setFullScreen(true);
        stage.show();
       
  
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            launch(args);
    }
    
}
