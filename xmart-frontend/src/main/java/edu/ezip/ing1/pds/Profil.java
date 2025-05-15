package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.Update;
import edu.ezip.ing1.pds.services.stageService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Profil {
    public TextField nom;
    public TextField prenom;
    
    public TextField email;
    public ImageView photo;
    public Label matricule;
    public Label chemin;
    private Etudiant etudiant;
    FileChooser fileChooser = new FileChooser();


    public void modifier(MouseEvent mouseEvent) throws InterruptedException, SQLException, IOException {

        Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
        aler.setTitle("CANFIRMATION");
        aler.setHeaderText(null);
        aler.setContentText("Vous voulez vraiment modifier l'offre ");
        Optional<ButtonType> result = aler.showAndWait();
        if (result.get() == ButtonType.OK) {
            String chem= chemin.getText();
            Etudiant et =new Etudiant(nom.getText(),prenom.getText(),etudiant.getId(),chem,email.getText());
            Update.updateInformationEtudiant("UPDATE_INFORMATION_ETUDIANT",et);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal2.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(loader.load());
            fenetre.setScene(scene);

            Principal2 controller = loader.getController();
            controller.setEtudiant(et);
            fenetre.show();

            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.close();



        }
        else {
            // Si l'utilisateur clique sur "Annuler", on ne fait rien
            System.out.println("operation annullée");

        }



    }
    public void setEtudiant(Etudiant etudiant) {
        nom.setText(etudiant.getNom());
        prenom.setText(etudiant.getPrenom());
        matricule.setText(etudiant.getMatricule());
        email.setText(etudiant.getEmail());
        System.out.println("Nom du fichier photo : " + etudiant.getPhoto());
        photo.setImage(new Image((getClass().getResource("/photo/" + etudiant.getPhoto()).toExternalForm())));
        this.etudiant = etudiant;


    }

    public void home(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principal2.fxml"));

        Scene offre = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(offre);
   Principal2 controller = fxmlLoader.getController();
    controller.setEtudiant(this.etudiant);

        stage.show();

        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void modifyImage(MouseEvent mouseEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            chemin.setText(file.getName());
        } else {
            chemin.setText("Aucun fichier sélectionné");
        }
    }
}
