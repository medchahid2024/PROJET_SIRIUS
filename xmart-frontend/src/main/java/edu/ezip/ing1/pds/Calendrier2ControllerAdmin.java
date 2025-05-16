package edu.ezip.ing1.pds;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.EvenementService;
import edu.ezip.ing1.pds.services.InsertParticipation;
import edu.ezip.ing1.pds.services.ParticipationService;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Etudiant;
import edu.ezip.ing1.pds.business.dto.Evenement;
import edu.ezip.ing1.pds.business.dto.Evenements;
import edu.ezip.ing1.pds.business.dto.Participation;
import edu.ezip.ing1.pds.business.dto.Participations;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;

import edu.ezip.ing1.pds.services.EvenementService;
import edu.ezip.ing1.pds.services.InsertCandidature;
import edu.ezip.ing1.pds.services.InsertParticipation;
import edu.ezip.ing1.pds.services.InsertEvenement;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Calendrier2ControllerAdmin {

    private final static String networkConfigFile = "network.yaml";
    private List<Evenement> evenementList = new ArrayList<>();
    private LocalDate currentDate = LocalDate.now();
    private String[] semaineActuelle = new String[7];
    private String[] joursSemaine = new String[] {"Lu", "Ma", "Me", "Je", "Ve", "Sa", "Di"};
    private int horaire;
    private int[] id_evenements = new int[35];
    private Etudiant etudiant;
    private List<Participation> participationList = new ArrayList<>();
    private String horaireIns;


      public void InitialisationCalendrier() throws IOException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, SQLException {
        loadEvenementData();
        loadParticipationData();
        currentWeek();

        }

    private void loadEvenementData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final EvenementService evenementService = new EvenementService(networkConfig);

        Evenements evenements = evenementService.selectEvenements();

        if (evenements != null) {
            evenementList = new ArrayList<>(evenements.getEvenements());
    }

    }

    private void loadParticipationData() throws IOException, InterruptedException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final ParticipationService participationService = new ParticipationService(networkConfig);

        Participations participations = participationService.selectParticipations();

        if (participations != null) {
            participationList = new ArrayList<>(participations.getParticipations());
    }

    }

    
    private void currentWeek() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        // Obtenir la date actuelle
        LocalDate today = LocalDate.now();

        // Obtenir le numéro de la semaine selon les normes locales (ex: France : ISO-8601)
        WeekFields weekFields = WeekFields.of(Locale.FRANCE);
        int weekNumber = today.get(weekFields.weekOfWeekBasedYear());

        // Trouver le lundi de cette semaine
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Formater la date au format dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Créer le texte final
        String labelText = String.format("S%d - %s", weekNumber, monday.format(formatter));

        updateSemaineActuelle(monday);

        // Affecter le texte au label
        Semaine.setText(labelText);
        reload_evenements();
        afficherEvenements();

    }

    private void reload_evenements() {
    
        for (String jour : joursSemaine) {
        
        for (int i = 1; i <= 5; i++) {
            try {
                // Rendre invisible les labels pour les événements du jour
                Label labelT = (Label) getClass().getDeclaredField(jour + i + "_T").get(this);
                Label labelH = (Label) getClass().getDeclaredField(jour + i + "_H").get(this);
                
                labelT.setVisible(false);
                labelH.setVisible(false);
                
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace(); // Pour le débogage, si un champ est introuvable ou inaccessible
            }
        }
        id_evenements = new int[35];
    }
}


    private void updateSemaineActuelle(LocalDate startDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    for (int i = 0; i < 7; i++) {
        semaineActuelle[i] = startDate.plusDays(i).format(formatter);
    }
}

    private void afficherEvenements() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        
        for (int i = 0; i < 7; i++) {

        for (int j = 0; j < evenementList.size(); j++) {

            
                
             
                Evenement evenement = evenementList.get(j);

                
            
    
                
               

                if (evenement.getJour().equals(semaineActuelle[i])) {

                    

                if (evenement.getHeure().equals("09:00-11:00")) {
            horaire = 1;
        } else if (evenement.getHeure().equals("11:00-13:00")) {
            horaire = 2;
        } else if (evenement.getHeure().equals("13:00-15:00")) {
            horaire = 3;
        } else if (evenement.getHeure().equals("15:00-17:00")) {
            horaire = 4;
        } else if (evenement.getHeure().equals("17:00-19:00")) {
            horaire = 5;
        }

        
        id_evenements[i*5 + horaire-1] = evenement.getId();

                Label label = (Label) getClass().getDeclaredField(joursSemaine[i] + horaire + "_T").get(this);
                Label heure = (Label) getClass().getDeclaredField(joursSemaine[i] + horaire + "_H").get(this);
    
                
                label.setText(evenement.getTitre());
                label.setVisible(true);
                heure.setText(evenement.getHeure());
                heure.setVisible(true);
                }
                
            
        }

    }
    }
     
    public void setEtudiant(Etudiant etudiant) {
        if (etudiant != null) {
            this.etudiant = etudiant;
            System.out.println(etudiant.getEmail());
        }
}
    public void participer(int i) throws IOException, SQLException, InterruptedException {
        
        if (id_evenements[i]!=0) {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ParticipationList.fxml"));
        Stage stage = new Stage();

        Scene demande = new Scene(fxmlLoader.load());
        stage.setScene(demande);
        // Récupérer le contrôleur de la vue Calendrier
        ParticipationList part = fxmlLoader.getController();

        // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
        part.Afficher_Participations(id_evenements[i]);
        stage.setTitle("Ma liste");
        stage.show();
        }
        else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InserEvenA.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        // Récupérer le contrôleur de la vue Calendrier
        InsertEvent inser = loader.getController();
       
        if(i%5==0){horaireIns="09:00-11:00";}
         if(i%5==1){horaireIns="11:00-13:00";}
          if(i%5==2){horaireIns="13:00-15:00";}
           if(i%5==3){horaireIns="15:00-17:00";}
            if(i%5==4){horaireIns="17:00-19:00";}
        // Appeler la méthode InitialisationCalendrier() sur l'instance du contrôleur
        inser.InitialisationEvenements(semaineActuelle[i/5],horaireIns);

        stage.show();

        }
        
    }
    @FXML
    private Button BHome;
    @FXML
    private Label Adresse;

    @FXML
    private ImageView Bar;

    @FXML
    private Label Description;

    @FXML
    private Button Di1;

    @FXML
    private Label Di1_H;

    @FXML
    private Label Di1_T;

    @FXML
    private Button Di2;

    @FXML
    private Label Di2_H;

    @FXML
    private Label Di2_T;

    @FXML
    private Button Di3;

    @FXML
    private Label Di3_H;

    @FXML
    private Label Di3_T;

    @FXML
    private Button Di4;

    @FXML
    private Label Di4_H;

    @FXML
    private Label Di4_T;

    @FXML
    private Button Di5;

    @FXML
    private Label Di5_H;

    @FXML
    private Label Di5_T;

    @FXML
    private Label Heure;

    @FXML
    private ImageView Icon;

    @FXML
    private Button Je1;

    @FXML
    private Label Je1_H;

    @FXML
    private Label Je1_T;

    @FXML
    private Button Je2;

    @FXML
    private Label Je2_H;

    @FXML
    private Label Je2_T;

    @FXML
    private Button Je3;

    @FXML
    private Label Je3_H;

    @FXML
    private Label Je3_T;

    @FXML
    private Button Je4;

    @FXML
    private Label Je4_H;

    @FXML
    private Label Je4_T;

    @FXML
    private Button Je5;

    @FXML
    private Label Je5_H;

    @FXML
    private Label Je5_T;

    @FXML
    private Button Lu1;

    @FXML
    private Label Lu1_H;

    @FXML
    private Label Lu1_T;

    @FXML
    private Button Lu2;

    @FXML
    private Label Lu2_H;

     @FXML
    private Label Instruction;

    @FXML
    private Label Lu2_T;

    @FXML
    private Button Lu3;

    @FXML
    private Label Lu3_H;

    @FXML
    private Label Lu3_T;

    @FXML
    private Button Lu4;

    @FXML
    private Label Lu4_H;

    @FXML
    private Label Lu4_T;

    @FXML
    private Button Lu5;

    @FXML
    private Label Lu5_H;

    @FXML
    private Label Lu5_T;

    @FXML
    private Button Ma1;

    @FXML
    private Label Ma1_H;

    @FXML
    private Label Ma1_T;

    @FXML
    private Button Ma2;

    @FXML
    private Label Ma2_H;

    @FXML
    private Label Ma2_T;

    @FXML
    private Button Ma3;

    @FXML
    private Label Ma3_H;

    @FXML
    private Label Ma3_T;

    @FXML
    private Button Ma4;

    @FXML
    private Label Ma4_H;

    @FXML
    private Label Ma4_T;

    @FXML
    private Button Ma5;

    @FXML
    private Label Ma5_H;

    @FXML
    private Label Ma5_T;

    @FXML
    private Button Me1;

    @FXML
    private Label Me1_H;

    @FXML
    private Label Me1_T;

    @FXML
    private Button Me2;

    @FXML
    private Label Me2_H;

    @FXML
    private Label Me2_T;

    @FXML
    private Button Me3;

    @FXML
    private Label Me3_H;

    @FXML
    private Label Me3_T;

    @FXML
    private Button Me4;

    @FXML
    private Label Me4_H;

    @FXML
    private Label Me4_T;

    @FXML
    private Button Me5;

    @FXML
    private Label Me5_H;

    @FXML
    private Label Me5_T;

    @FXML
    private Button S_Precedente;

    @FXML
    private Button S_Suivante;

    @FXML
    private Button Sa1;

    @FXML
    private Label Sa1_H;

    @FXML
    private Button Sa2;

    @FXML
    private Label Sa2_H;

    @FXML
    private Label Sa2_T;

    @FXML
    private Button Sa3;

    @FXML
    private Label Sa3_H;

    @FXML
    private Label Sa3_T;

    @FXML
    private Button Sa4;

    @FXML
    private Label Sa4_H;

    @FXML
    private Label Sa4_T;

    @FXML
    private Button Sa5;

    @FXML
    private Label Sa5_H;

    @FXML
    private Label Sa5_T;

    @FXML
    private Label Sa1_T;

    @FXML
    private Label Semaine;

    @FXML
    private Label Source;

    @FXML
    private Label Titre;

    @FXML
    private Button Ve1;

    @FXML
    private Label Ve1_H;

    @FXML
    private Label Ve1_T;

    @FXML
    private Button Ve2;

    @FXML
    private Label Ve2_H;

    @FXML
    private Label Ve2_T;

    @FXML
    private Button Ve3;

    @FXML
    private Label Ve3_H;

    @FXML
    private Label Ve3_T;

    @FXML
    private Button Ve4;

    @FXML
    private Label Ve4_H;

    @FXML
    private Label Ve4_T;

    @FXML
    private Button Ve5;

    @FXML
    private Label Ve5_H;

    @FXML
    private Label Ve5_T;
    
    @FXML
    private Button Ajouter;
      
    @FXML
void Ajouter_Evenement(ActionEvent event){
}

   @FXML
void Lu1_Ent(MouseEvent event) {
    if (id_evenements[0] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[0] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Lu1_Exi(MouseEvent event) {
    if (id_evenements[0] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Lu1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(0);
    
}
@FXML
void Lu2_Ent(MouseEvent event) {
    if (id_evenements[1] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[1] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Lu2_Exi(MouseEvent event) {
    if (id_evenements[1] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Lu2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(1);
}

@FXML
void Lu3_Ent(MouseEvent event) {
    if (id_evenements[2] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[2] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Lu3_Exi(MouseEvent event) {
    if (id_evenements[2] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Lu3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(2);
}

@FXML
void Lu4_Ent(MouseEvent event) {
    if (id_evenements[3] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[3] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Lu4_Exi(MouseEvent event) {
    if (id_evenements[3] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Lu4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(3);
}

@FXML
void Lu5_Ent(MouseEvent event) {
    if (id_evenements[4] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[4] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Lu5_Exi(MouseEvent event) {
    if (id_evenements[4] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Lu5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(4);
}
@FXML
void Ma1_Ent(MouseEvent event) {
    if (id_evenements[5] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[5] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ma1_Exi(MouseEvent event) {
    if (id_evenements[5] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ma1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(5);
}
@FXML
void Ma2_Ent(MouseEvent event) {
    if (id_evenements[6] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[6] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ma2_Exi(MouseEvent event) {
    if (id_evenements[6] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ma2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(6);
}
@FXML
void Ma3_Ent(MouseEvent event) {
    if (id_evenements[7] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[7] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ma3_Exi(MouseEvent event) {
    if (id_evenements[7] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ma3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(7);
}
@FXML
void Ma4_Ent(MouseEvent event) {
    if (id_evenements[8] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[8] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ma4_Exi(MouseEvent event) {
    if (id_evenements[8] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ma4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(8);
}
@FXML
void Ma5_Ent(MouseEvent event) {
    if (id_evenements[9] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (int j = 0; j < evenementList.size(); j++) {
            Evenement evenement = evenementList.get(j);
            if (id_evenements[9] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ma5_Exi(MouseEvent event) {
    if (id_evenements[9] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ma5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(9);
}
@FXML
void Me1_Ent(MouseEvent event) {
    if (id_evenements[10] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[10] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Me1_Exi(MouseEvent event) {
    if (id_evenements[10] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Me1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(10);
}

@FXML
void Me2_Ent(MouseEvent event) {
    if (id_evenements[11] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[11] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Me2_Exi(MouseEvent event) {
    if (id_evenements[11] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Me2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(11);
}

@FXML
void Me3_Ent(MouseEvent event) {
    if (id_evenements[12] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[12] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Me3_Exi(MouseEvent event) {
    if (id_evenements[12] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Me3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(12);
}

@FXML
void Me4_Ent(MouseEvent event) {
    if (id_evenements[13] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[13] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Me4_Exi(MouseEvent event) {
    if (id_evenements[13] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Me4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(13);
}

@FXML
void Me5_Ent(MouseEvent event) {
    if (id_evenements[14] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[14] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Me5_Exi(MouseEvent event) {
    if (id_evenements[14] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Me5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(14);
}
@FXML
void Je1_Ent(MouseEvent event) {
    if (id_evenements[15] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[15] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Je1_Exi(MouseEvent event) {
    if (id_evenements[15] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Je1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(15);
}

@FXML
void Je2_Ent(MouseEvent event) {
    if (id_evenements[16] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[16] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Je2_Exi(MouseEvent event) {
    if (id_evenements[16] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Je2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(16);
}

@FXML
void Je3_Ent(MouseEvent event) {
    if (id_evenements[17] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[17] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Je3_Exi(MouseEvent event) {
    if (id_evenements[17] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Je3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(17);
}

@FXML
void Je4_Ent(MouseEvent event) {
    if (id_evenements[18] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[18] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Je4_Exi(MouseEvent event) {
    if (id_evenements[18] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Je4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(18);
}

@FXML
void Je5_Ent(MouseEvent event) {
    if (id_evenements[19] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[19] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Je5_Exi(MouseEvent event) {
    if (id_evenements[19] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Je5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(19);
}
@FXML
void Ve1_Ent(MouseEvent event) {
    if (id_evenements[20] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[20] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ve1_Exi(MouseEvent event) {
    if (id_evenements[20] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ve1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(20);
}
@FXML
void Ve2_Ent(MouseEvent event) {
    if (id_evenements[21] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[21] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ve2_Exi(MouseEvent event) {
    if (id_evenements[21] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ve2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(21);
}

@FXML
void Ve3_Ent(MouseEvent event) {
    if (id_evenements[22] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[22] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ve3_Exi(MouseEvent event) {
    if (id_evenements[22] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ve3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(22);
}

@FXML
void Ve4_Ent(MouseEvent event) {
    if (id_evenements[23] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[23] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ve4_Exi(MouseEvent event) {
    if (id_evenements[23] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ve4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(23);
}

@FXML
void Ve5_Ent(MouseEvent event) {
    if (id_evenements[24] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[24] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Ve5_Exi(MouseEvent event) {
    if (id_evenements[24] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Ve5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(24);
}

@FXML
void Sa1_Ent(MouseEvent event) {
    if (id_evenements[25] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[25] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Sa1_Exi(MouseEvent event) {
    if (id_evenements[25] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Sa1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(25);
}

@FXML
void Sa2_Ent(MouseEvent event) {
    if (id_evenements[26] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[26] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Sa2_Exi(MouseEvent event) {
    if (id_evenements[26] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Sa2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(26);
}

@FXML
void Sa3_Ent(MouseEvent event) {
    if (id_evenements[27] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[27] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Sa3_Exi(MouseEvent event) {
    if (id_evenements[27] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Sa3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(27);
}

@FXML
void Sa4_Ent(MouseEvent event) {
    if (id_evenements[28] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[28] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Sa4_Exi(MouseEvent event) {
    if (id_evenements[28] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Sa4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(28);
}

@FXML
void Sa5_Ent(MouseEvent event) {
    if (id_evenements[29] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[29] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Sa5_Exi(MouseEvent event) {
    if (id_evenements[29] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Sa5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(29);
}

@FXML
void Di1_Ent(MouseEvent event) {
    if (id_evenements[30] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[30] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Di1_Exi(MouseEvent event) {
    if (id_evenements[30] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Di1_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(30);
}

@FXML
void Di2_Ent(MouseEvent event) {
    if (id_evenements[31] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[31] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Di2_Exi(MouseEvent event) {
    if (id_evenements[31] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Di2_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(31);
}

@FXML
void Di3_Ent(MouseEvent event) {
    if (id_evenements[32] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[32] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Di3_Exi(MouseEvent event) {
    if (id_evenements[32] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Di3_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(32);
}

@FXML
void Di4_Ent(MouseEvent event) {
    if (id_evenements[33] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[33] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Di4_Exi(MouseEvent event) {
    if (id_evenements[33] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Di4_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(33);
}

@FXML
void Di5_Ent(MouseEvent event) {
    if (id_evenements[34] != 0) {
        Instruction.setVisible(false);
        Bar.setVisible(true);
        for (Evenement evenement : evenementList) {
            if (id_evenements[34] == evenement.getId()) {
                Titre.setText(evenement.getTitre()); Titre.setVisible(true);
                Heure.setText(evenement.getHeure()); Heure.setVisible(true);
                Source.setText(evenement.getDomaine()); Source.setVisible(true);
                Description.setText(evenement.getDescription()); Description.setVisible(true);
                Adresse.setText(evenement.getAdresse()); Adresse.setVisible(true);
            }
        }
    }
}

@FXML
void Di5_Exi(MouseEvent event) {
    if (id_evenements[34] != 0) {
        Instruction.setVisible(true);
        Bar.setVisible(false);
        Titre.setVisible(false);
        Source.setVisible(false);
        Heure.setVisible(false);
        Description.setVisible(false);
        Adresse.setVisible(false);
    }
}

@FXML
void Di5_P(ActionEvent event) throws IOException, SQLException, InterruptedException {
    participer(34);
}



    @FXML
    void Semaine_Precedente(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    // Reculer d'une semaine
    currentDate = currentDate.minusWeeks(1);

    // Obtenir le numéro de la semaine
    WeekFields weekFields = WeekFields.of(Locale.FRANCE);
    int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());

    // Obtenir le lundi de cette semaine
    LocalDate monday = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

    // Formater la date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Mettre à jour le label
    Semaine.setText(String.format("S%d - %s", weekNumber, monday.format(formatter)));
    updateSemaineActuelle(monday);
    reload_evenements();
    afficherEvenements();
    }

    @FXML
    void Semaine_Suivante(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    // Passer à la semaine suivante
    currentDate = currentDate.plusWeeks(1);

    // Obtenir le numéro de la semaine
    WeekFields weekFields = WeekFields.of(Locale.FRANCE);
    int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());

    // Obtenir le lundi de cette semaine
    LocalDate monday = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

    // Formater la date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Mettre à jour le label
    Semaine.setText(String.format("S%d - %s", weekNumber, monday.format(formatter)));
    updateSemaineActuelle(monday);
    reload_evenements();
    afficherEvenements();
    }

   @FXML
    void BHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        Principal2 controller = loader.getController();
        controller.setEtudiant(etudiant);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}

