package edu.ezip.ing1.pds;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Admin {
    public void mesDemandes(MouseEvent mouseEvent) throws IOException {
        System.out.println("heemlooo");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Offres_stages_admin.fxml"));
        Stage stage = new Stage();

        Scene demande = new Scene(fxmlLoader.load());
        stage.setScene(demande);
        stage.setTitle("Mes offres de stages");
        stage.show();
    }

    public void DemandeConnexion(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DemandeConnexion.fxml"));
        Stage stage = new Stage();

        Scene demande = new Scene(fxmlLoader.load());
        stage.setScene(demande);
        stage.setTitle("Mes demandes");
        stage.show();

    }

    public void liste(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/StudentsList.fxml"));
        Stage stage = new Stage();

        Scene demande = new Scene(fxmlLoader.load());
        stage.setScene(demande);
        stage.setTitle("Ma liste");
        stage.show();
    }

     public void Parti(MouseEvent event) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InterruptedException, SQLException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Calendrier2Admin.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        // Récupérer le contrôleur de la vue Calendrier
        Calendrier2ControllerAdmin calendrier2ControllerAdmin = loader.getController();

        // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
        calendrier2ControllerAdmin.InitialisationCalendrier();
        stage.show();
    }
}
