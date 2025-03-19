package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.Delete;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Offres_stages_admin {

    @FXML
    public TextField mot;
    public Label nombre;

    @FXML
    private Label titre;
    @FXML
    private Label domaine;
    @FXML
    private Label description;
    @FXML
    private Label duree;
    @FXML
    private Button btnSuivant;
    @FXML
    private Button btnPrecedent;

    private final static String networkConfigFile = "network.yaml";
    private List<Stagee> stageList = new ArrayList<>();
    private int currentIndex = 0;
    private int id;

    public void initialize() {
        try {
            loadStageData();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadStageData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);

        Stagess stagess = stageService.selectStages();

        if (stagess != null) {
            stageList = new ArrayList<>(stagess.getStages());
            currentIndex = 0;
            afficherStage(currentIndex);
        }
    }

    private void afficherStage(int index) {


        Stagee stage = stageList.get(index);
        nombre.setText(String.valueOf(stageList.size()));
        titre.setText(stage.getTitre());
        domaine.setText(stage.getDomaine());
        description.setText(stage.getDescription());
        duree.setText(stage.getDuree());

    }

    @FXML
    public void suivant() {
        if (currentIndex < stageList.size() - 1) {
            currentIndex++;
            afficherStage(currentIndex);
        }
    }

    @FXML
    public void precedent() {
        if (currentIndex > 0) {
            currentIndex--;
            afficherStage(currentIndex);
        }
    }



    public void Ajouter(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InsertOffre.fxml"));
        Stage stage = new Stage();

        Scene offre = new Scene(fxmlLoader.load());
        stage.setScene(offre);
        stage.setTitle("Ajouter");
        stage.show();

    }

    public void Supprimer(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {
        Stagee stageSelectionne = stageList.get(currentIndex); // Récupérer le stage affiché
        int idOffre = stageSelectionne.getId();
        String tit = stageSelectionne.getTitre();
        System.out.println(idOffre);
        Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
        aler.setTitle("WARNING");
        aler.setHeaderText(null);
        aler.setContentText("Vous voulez vraiment supprimer cette offre "+tit);
        Optional<ButtonType> result = aler.showAndWait();
        if (result.get() == ButtonType.OK) {
            Delete.deleteValue("DELETE_OFFRE",idOffre);

        }
        else {
            // Si l'utilisateur clique sur "Annuler", on ne fait rien
            System.out.println("Suppression annulée");
        }

    }

    public void Modifier(ActionEvent actionEvent) {

    }

}
