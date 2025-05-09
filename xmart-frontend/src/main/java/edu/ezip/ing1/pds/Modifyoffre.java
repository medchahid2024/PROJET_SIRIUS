package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.Update;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Modifyoffre {
    public TextField duree;
    public Button modifier;
    public TextArea description;
    public TextField domaine;
    public TextField niveau;
    public TextField titre;
    int idStagee;
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    private List<Stagee> stageList = new ArrayList<>();

    public Modifyoffre() throws InterruptedException {
    }

    public void setInformation(Stagee stage) {

    duree.setText(stage.getDuree());
        idStagee= stage.getId();

    description.setText(stage.getDescription());
    domaine.setText(stage.getDomaine());
    niveau.setText(stage.getNiveau());
    titre.setText(stage.getTitre());
    niveau.setText(stage.getNiveau());
        System.out.println(stage.getDomaine());
        System.out.println(stage.getDuree());
        System.out.println(stage.getNiveau());
        System.out.println(stage.getTitre());
        System.out.println(idStagee);



    }

    public void Modifier(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {

        System.out.println("id: " + idStagee);


        Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
        aler.setTitle("CANFIRMATION");
        aler.setHeaderText(null);
        aler.setContentText("Vous voulez vraiment modifier l'offre ");
        Optional<ButtonType> result = aler.showAndWait();
        if (result.get() == ButtonType.OK) {
            String durr = duree.getText();
            String tit = titre.getText();
            String des = description.getText();
            String niv= niveau.getText();
            String dom = domaine.getText();

    Stagee stage = new Stagee(idStagee,tit, des,  dom, durr , niv);

            Update.updatestage("UPDATE_OFFRE",stage);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Offres_stages_admin.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(loader.load());
            fenetre.setScene(scene);
            fenetre.show();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();



        }
        else {
            // Si l'utilisateur clique sur "Annuler", on ne fait rien
            System.out.println("operation annullée");

        }

    }
}
