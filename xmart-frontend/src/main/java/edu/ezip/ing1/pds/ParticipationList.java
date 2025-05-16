package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.business.dto.Participations;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.ParticipationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParticipationList {

    private final static String networkConfigFile = "network.yaml";
    private List<Participation> participationList = new ArrayList<>();
    private List<Participation> participationList2 = new ArrayList<>();

    @FXML
    private TableColumn<Participation, String> Nom;

    @FXML
    private TableColumn<Participation, String> Prenom;

    @FXML
    private TableColumn<Participation, String> Email;

    @FXML
    private TableView<Participation> table;

    @FXML
    public void Afficher_Participations(int k) throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final ParticipationService participationService = new ParticipationService(networkConfig);

        // Chargement de toutes les participations
        Participations participations = participationService.selectParticipations();
        participationList = new ArrayList<>(participations.getParticipations());

        // Filtrage des participations avec id = k
        participationList2.clear(); // important pour éviter l'accumulation si la méthode est appelée plusieurs fois
        for (Participation participation : participationList) {
             System.out.println("id parti = "+participation.getId());
              System.out.println("k = "+k);
            if (participation.getId() == k) {
                participationList2.add(participation);
            }
        }

        // Mise à jour de la table avec participationList2
        ObservableList<Participation> observableList = FXCollections.observableArrayList(participationList2);
        table.setItems(observableList);

        // Configuration des colonnes
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
