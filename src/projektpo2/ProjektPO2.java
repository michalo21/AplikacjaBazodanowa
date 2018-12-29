/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.HibernateUtil;


/**
 *
 * @author Arlen
 */
public class ProjektPO2 extends Application {
    
     
    
    @Override
    public void start(Stage primaryStage) throws IOException {     
        
        Parent root = FXMLLoader.load(getClass().getResource("PanelLogowania.fxml"));
        Scene scene = new Scene(root, 324, 400);
        scene.getStylesheets().add("panel.css");
        primaryStage.setTitle("Logowanie do bazy danych");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HibernateUtil.OpenConnection();
        launch(args);
        HibernateUtil.CloseConnection();
        
    }
    
}
