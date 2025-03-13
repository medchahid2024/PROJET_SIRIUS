package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;

import edu.ezip.ing1.pds.services.stageService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlStage {

    public Button postuler;
    @FXML
    public TextField mot;

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

    public void postuler(ActionEvent actionEvent) throws IOException, InterruptedException {

        Stagee stageSelectionne = stageList.get(currentIndex); // Récupérer le stage affiché
        int idOffre = stageSelectionne.getId();



        Candidater candidater= new Candidater();
        candidater.setId(idOffre);

//        Stagee stageSelectionne = stageList.get(currentIndex); // Récupérer le stage affiché
//        int idOffre = stageSelectionne.getId();
//        Candidater candidater= new Candidater();
//        candidater.setId(idOffre);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Candidater.fxml"));
        Stage stage = new Stage();

        Scene offre = new Scene(fxmlLoader.load(), 700, 700);
        stage.setScene(offre);
        stage.setTitle("Candidature");
//        Candidater candidatureController = fxmlLoader.getController();
//        candidatureController.setId(idOffre);

        stage.show();
    }

    public void rechercher(ActionEvent actionEvent) throws InterruptedException, IOException {
        String rechercher = mot.getText().trim();

        if (!rechercher.isEmpty()) {
            try {
                final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
                final stageService stageService = new stageService(networkConfig);

                Stagess stagess = stageService.selectOffres(rechercher); // Passer le mot-clé

                if (stagess != null && !stagess.getStages().isEmpty()) {
                    stageList = new ArrayList<>(stagess.getStages());
                    currentIndex = 0;
                    afficherStage(currentIndex); // Afficher le premier résultat trouvé
                } else {
                    titre.setText("Aucune offre trouvée pour : " + rechercher);
                    domaine.setText("-");
                    description.setText("-");
                    duree.setText("-");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la recherche : " + e.getMessage());
                titre.setText("Erreur lors de la recherche");
            }
        }
    }

}
