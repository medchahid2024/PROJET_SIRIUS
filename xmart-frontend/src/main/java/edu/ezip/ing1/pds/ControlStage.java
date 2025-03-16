package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Candidatures;
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
    public Label nombre;
    public Label Nombrecandidatures;

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
   private List<Candidature> listeCount = new ArrayList<>();

    private int currentIndex = 0;

    final stageService stageService ;

    public ControlStage() throws InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        this.stageService = new stageService(networkConfig);
    }


    public void initialize() {
        try {
            loadStageData();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadStageData() throws IOException, InterruptedException {

        Stagess stagess = stageService.selectStages();


        if (stagess != null) {
            stageList = new ArrayList<>(stagess.getStages());
            currentIndex = 0;
            afficherStage(currentIndex);
        }

    }


    private void afficherStage(int index) throws IOException, InterruptedException {


        Stagee stage = stageList.get(index);


        int idStage = stage.getId();
        Candidatures candidatures = stageService.selectCountCandidature("SELECT_CANDIDATURE", idStage);


            // Récupérer l'ID de la première candidature
            int idCandidature = candidatures.getCandidatures().iterator().next().getId();

            // Afficher l'ID dans le label



        System.out.println(idStage);
        System.out.println(idStage);
        System.out.println(idStage);
        System.out.println(idStage);



        Nombrecandidatures.setText(String.valueOf(idCandidature));
        nombre.setText(String.valueOf(stageList.size()));
        titre.setText(stage.getTitre());
        domaine.setText(stage.getDomaine());
        description.setText(stage.getDescription());
        duree.setText(stage.getDuree());

    }

    @FXML
    public void suivant() throws IOException, InterruptedException {
        if (currentIndex < stageList.size() - 1) {
            currentIndex++;
            afficherStage(currentIndex);
        }
    }

    @FXML
    public void precedent() throws IOException, InterruptedException {
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
        Candidater candidate= new Candidater();
        candidate.setId(idOffre);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Candidater.fxml"));
        Stage stage = new Stage();

        Scene offre = new Scene(fxmlLoader.load(), 700, 700);
        stage.setScene(offre);
        stage.setTitle("Candidature");
        Candidater candidatureController = fxmlLoader.getController();
        candidatureController.setId(idOffre);

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
