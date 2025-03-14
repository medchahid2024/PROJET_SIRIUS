package edu.ezip.ing1.pds;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        Stage stage1 = new Stage();

        Scene demande1 = new Scene(fxmlLoader.load());
        stage1.setScene(demande1);
        stage1.setTitle("Mes demandes");
        stage1.show();


    }
}
