package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.stageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlStage {

    @FXML
    private Button postuler;
    @FXML
    private TextField mot;
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

    public void initialize() {
        try {
            loadStageData();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger les offres de stage.");
        }
    }

    private void loadStageData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final stageService stageService = new stageService(networkConfig);

        Stagess stagess = stageService.selectStages();

        if (stagess != null && !stagess.getStages().isEmpty()) {
            stageList = new ArrayList<>(stagess.getStages());
            currentIndex = 0;
            afficherStage(currentIndex);
        } else {
            showAlert("Information", "Aucune offre de stage disponible.");
        }
    }

    private void afficherStage(int index) {
        if (stageList.isEmpty()) {
            titre.setText("Aucune offre disponible");
            domaine.setText("-");
            description.setText("-");
            duree.setText("-");
            return;
        }

        Stagee stage = stageList.get(index);
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

    public int getIdOffreSelectionnee() {
        if (!stageList.isEmpty()) {
            return stageList.get(currentIndex).getId();
        } else {
            showAlert("Erreur", "Aucune offre sélectionnée.");
            return -1;
        }
    }

    @FXML
    public void postuler(ActionEvent actionEvent) throws IOException {

        if (stageList.isEmpty()) {
            showAlert("Erreur", "Aucune offre disponible pour postuler.");
            return;
        }

        int idOffre = getIdOffreSelectionnee();
        if (idOffre == -1) return;


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Candidater.fxml"));
        Stage stage = new Stage();

        Scene offre = new Scene(fxmlLoader.load(), 700, 700);
        stage.setScene(offre);
        stage.setTitle("Candidature");


        // Récupération du contrôleur après le chargement
        Candidater candidaterController = fxmlLoader.getController();
        if (candidaterController != null) {
            candidaterController.setId(idOffre);
        } else {
            showAlert("Erreur", "Impossible d'ouvrir la fenêtre de candidature.");
        }


        stage.show();
    }

    @FXML
    public void rechercher(ActionEvent actionEvent) throws InterruptedException, IOException {
        String rechercher = mot.getText().trim();

        if (!rechercher.isEmpty()) {
            try {
                final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
                final stageService stageService = new stageService(networkConfig);

                Stagess stagess = stageService.selectOffres(rechercher);

                if (stagess != null && !stagess.getStages().isEmpty()) {
                    stageList = new ArrayList<>(stagess.getStages());
                    currentIndex = 0;
                    afficherStage(currentIndex);
                } else {
                    showAlert("Information", "Aucune offre trouvée pour : " + rechercher);
                    titre.setText("Aucune offre trouvée");
                    domaine.setText("-");
                    description.setText("-");
                    duree.setText("-");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Erreur", "Une erreur est survenue lors de la recherche.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
