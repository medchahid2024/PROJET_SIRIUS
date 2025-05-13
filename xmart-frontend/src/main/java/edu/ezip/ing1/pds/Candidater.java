package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Candidater  {

    public Button motivation;
    public Button CV;
    public Button autres;
    public Label lab;
    public Label lett;
    public Label autre;

    public TextField adresse;
    public TextField nom;
    public TextField email;
    public TextField prenom;
    public Button valider;
    FileChooser fileChooser = new FileChooser();
    private int id;
    private Etudiant etudiant;
    private int idetudiant;
    private final static String networkConfigFile = "network.yaml";


    public Candidater() throws InterruptedException {
    }


    public void autres(MouseEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            autre.setText(file.getName());
        } else {
            autre.setText("Aucun fichier sélectionné");
        }

    }

    public void lettre(MouseEvent mouseEvent) {
        File file1 = fileChooser.showOpenDialog(null);
        if (file1 != null) {
            lett.setText(file1.getName());
        } else {
            lett.setText("Aucun fichier sélectionné");
        }
    }

    public void CV(MouseEvent mouseEvent) {
        File file2 = fileChooser.showOpenDialog(null);
        if (file2 != null) {
            lab.setText(file2.getName());
        } else {
            lab.setText("Aucun fichier sélectionné");
        }
    }




    public void valider(ActionEvent actionEvent) throws InterruptedException, IOException, SQLException {
        String nom1 = nom.getText();
        String prenom1 = prenom.getText();
        String email1 = email.getText();
        String adresse1 = adresse.getText();
        String lab1 = lab.getText();
        String lett1 = lett.getText();
        String autre1 = autre.getText();

        Stagee stagee = new Stagee(idetudiant);
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);
        Stagess stagess = stageService.SelectEtudiantCandidature("SELECT_CANDIDATURES_ETUDIANT", stagee);

        // Vérification que tous les champs sont remplis correctement
        if (nom1 == null || nom1.isEmpty() || prenom1 == null || prenom1.isEmpty() || email1 == null || email1.isEmpty()
                || adresse1 == null || adresse1.isEmpty() || lab1 == null || lab1.isEmpty() || lab1.equals("Aucun fichier sélectionné")
                || lett1 == null || lett1.isEmpty() || lett1.equals("Aucun fichier sélectionné")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement.");
            alert.showAndWait();}
            else if (!lab1.contains(".pdf") && !lett1.contains(".pdf") && !autre1.contains(".pdf") ) {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Erreur");
                alert3.setHeaderText(null);
                alert3.setContentText("Le fichier  doit être un .pdf.");
                alert3.showAndWait();


        }



            else if (!(stagess.getStages().isEmpty())){
                Alert alert8 = new Alert(Alert.AlertType.ERROR);

            alert8.setTitle("Erreur");
            alert8.setHeaderText(null);
            alert8.setContentText("Vous avez déja postulé a cette offre");
             alert8.showAndWait();
//            if (result.get() == ButtonType.OK) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/control_stage.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(fxmlLoader.load());
//                stage.setScene(scene);
//                stage.show();
//                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                currentStage.close();
//
//                                                 }
        }
            else {
            Candidature c = new Candidature(nom1, prenom1, email1, adresse1, lab1, lett1, autre1, this.id,idetudiant);



            System.out.println(" l'offre sélectionnée : " + id);
            System.out.println(" nom sélectionnée : " + nom1);
            System.out.println(" prenom de l'offre sélectionnée : " + prenom1);
            System.out.println("  adresse de l'offre sélectionnée : " + adresse1);
            System.out.println(" cv de l'offre sélectionnée : " + lab1);
            System.out.println(" lettre de l'offre sélectionnée : " + lett1);
            InsertCandidature.sendValue("INSERT_CANDIDATURE", c);

            Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
            aler.setTitle("Validé");
            aler.setHeaderText(null);
            aler.setContentText("Nous avons bien reçu votre candidature");
            aler.showAndWait();
        }
    }
    public void setId(int id) {
        System.out.println("ID de l'offre sélectionnée : " + id);
        this.id = id;
    }
public void setStudent ( Etudiant et) {
        nom.setText(et.getNom());
        prenom.setText(et.getPrenom());
        System.out.println("L'ID de l'etudiant"+et.getId());
        idetudiant = et.getId();


}

}
