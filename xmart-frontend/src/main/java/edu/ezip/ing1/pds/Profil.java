package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Profil {
    private Etudiant etudiant;

    public void modifier(MouseEvent mouseEvent) {
    }
    public void setEtudiant(Etudiant etudiant) {

    }

    public void home(MouseEvent mouseEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principal2.fxml"));
//
//        Scene offre = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setScene(offre);
//   Principal2 controller = fxmlLoader.getController();
//    controller.setEtudiant(etudiant);
//
//        stage.show();

        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
