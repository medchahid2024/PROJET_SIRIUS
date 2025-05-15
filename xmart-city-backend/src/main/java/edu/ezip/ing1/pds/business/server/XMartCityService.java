package edu.ezip.ing1.pds.business.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import edu.ezip.ing1.pds.business.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;

public class XMartCityService {

    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    private enum Queries {
        // ---------------------------- PARTIE INSERTION----------------------------------------------------------------------------------
                     //Insertion des offres par l'admin
        INSERT_STAGE("INSERT into offres_stages (titre, description, domaine,niveau_etude,duree,id_admin) values ( ?, ?,?,?,?, 1 )"),

                     // candidature pour une offre de stage en recuperant l'id de l'entreprise
        INSERT_CANDIDATURE("INSERT INTO candidature (nom,prenom,cv,email,adresse, lettre_de_motivation,autre_fichier ,id_offre ,id_etudiant)VALUES  (?,?,?,?,?, ?, ?, ?, ?) "),

                     // demande de creation d'un compte d'un etudiant
        INSERT_ETUDIANT("INSERT INTO etudiant (nom,prenom,matricule,email,mot_de_passe,cnf_mot_de_passe,photo)VALUES  (?,?,?,?,?, ?, ?) "),


        INSERT_ARTICLE("INSERT INTO article (nom_etudiant, titre, description, id_etudiant, prix, type_transaction, ville) VALUES (?,?,?,?,?,?,?)"),
        INSERT_PARTICIPATION("INSERT INTO participations (nom,prenom,email,id_even)VALUES  (?,?,?,?) "),



        //-----------------------------------------------------------------------------------------------------------------------------------


        // ---------------------------- PARTIE Modification----------------------------------------------------------------------------------

                     // Decision de l'admin (accepter une demande)
        UPDATE_ETUDIANT("UPDATE etudiant SET accepte = TRUE WHERE id_etudiant = ?"),

                     //modification d'une demande par l'admin
       UPDATE_OFFRE("UPDATE offres_stages SET titre = ?, description = ?, domaine=? ,niveau_etude=? ,duree=?,id_admin=1 WHERE id_offre = ?"),
        UPDATE_INFORMATION_ETUDIANT("UPDATE etudiant SET nom = ?, prenom = ?, email=? ,photo=?  WHERE id_etudiant = ?"),

        UPDATE_DATE("UPDATE etudiant SET date_ajout= CURRENT_TIMESTAMP WHERE id_etudiant = ?"),

        //-----------------------------------------------------------------------------------------------------------------------------------


        // ---------------------------- PARTIE AFFICHAGE----------------------------------------------------------------------------------

                         //affichage de l'email des etudiant acceptés (contrainte avant la creation de compte il affiche un message d'erreur que le compte existe deja)
       SELECT_CONDITION_CONN ("SELECT * FROM etudiant WHERE email = ?  AND accepte=TRUE"),

        SELECT_ALL_STUDENTS ("SELECT DISTINCT COUNT(c.id_etudiant) AS nombre_candidatures , e.photo AS photo ,e.email AS email, e.date_ajout AS date , e.nom AS nom , e.prenom AS prenom , e.matricule AS mat ,e.id_etudiant AS id , o.titre AS titre , o.duree AS duree , o.id_offre AS id_offre , o.domaine AS domaine " +
                "FROM etudiant e " +
                " LEFT JOIN candidature c ON c.id_etudiant = e.id_etudiant " +
                " LEFT JOIN offres_stages o ON o.id_offre = c.id_offre " +

                " WHERE e.accepte = TRUE GROUP BY id,id_offre, e.nom, e.prenom, date , e.matricule ,e.photo,titre,duree,domaine"),

        SELECT_CANDIDATURES_ETUDIANT("SELECT DISTINCT o.titre, o.domaine, o.duree, c.id_etudiant FROM offres_stages o JOIN candidature c ON o.id_offre=c.id_offre WHERE c.id_etudiant = ? AND c.id_offre= ?"),

                           //connexion avec succés des etudiant acceptés
        SELECT_CONN("SELECT * FROM etudiant WHERE email = ? AND mot_de_passe =? AND accepte=TRUE"),

                            // viualiser les etudiant non acceptés par l'admin avant la decision
        SELECT_ETUDIANT("SELECT  nom,prenom,matricule,email,photo,id_etudiant FROM etudiant WHERE accepte IS NULL ORDER BY id_etudiant DESC"),

                             // l'affichage des offres de stages par ordre decroissant
        SELECT_STAGE("SELECT * FROM offres_stages ORDER BY id_offre DESC"),

                             // pour rechercher un stage par rapport au titre
        SELECT_OFFRE("SELECT titre,description,domaine,niveau_etude FROM offres_stages WHERE titre LIKE ?"),

                             // affichage de nombre de candidatures pour chaque offre
        SELECT_CANDIDATURE("SELECT COUNT(id_offre) FROM candidature WHERE id_offre= ? "),


        SELECT_EVENEMENT("SELECT * FROM evenements"),

        SELECT_ARTICLE("SELECT * FROM article"),
        //-----------------------------------------------------------------------------------------------------------------------------------



        // ---------------------------- PARTIE SUPPRESSION----------------------------------------------------------------------------------

                       // supprimer une offre de stage
        DELETE_OFFRE("DELETE FROM offres_stages WHERE id_offre = ?");




        private final String query;

        private Queries(final String query) {
            this.query = query;
        }
    }

    public static XMartCityService inst = null;
    public static final XMartCityService getInstance()  {
        if(inst == null) {
            inst = new XMartCityService();
        }
        return inst;
    }

    private XMartCityService() {

    }

    public final Response dispatch(final Request request, final Connection connection)
            throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Response response = null;

        final Queries queryEnum = Enum.valueOf(Queries.class, request.getRequestOrder().trim());
        switch(queryEnum) {

            case INSERT_CANDIDATURE:
                response = InsertCandidature(request, connection);
                break;
            case INSERT_STAGE:
                response = InsertStage(request, connection);
                break;
            case INSERT_PARTICIPATION:
                response = InsertParticipation(request, connection);
                break;
            case SELECT_STAGE:
               response=SelectAllstages(request, connection);
                break;
            case INSERT_ETUDIANT:
                             response=InsertEtudiant(request, connection);
               break;
            case SELECT_ETUDIANT:
                             response=SelectEtudiant(request, connection);
                break;
            case SELECT_ALL_STUDENTS:
                response=SelectAllStudents(request, connection);
                break;
            case UPDATE_INFORMATION_ETUDIANT:
                response=updateInformationEtudiant(request, connection);
                break;
            case SELECT_OFFRE:
                response=SelectAlloffres(request, connection);
                break;
            case SELECT_EVENEMENT:
                response=SelectAllevenements(request, connection);

                break;
            case SELECT_CANDIDATURE:
                response=SelectCandidature(request, connection);

                break;
            case SELECT_CANDIDATURES_ETUDIANT:
                response=SelectCandidatureEtudiant(request, connection);

                break;
            case DELETE_OFFRE:
                response=deleteCandidature(request, connection);

                break;
            case UPDATE_ETUDIANT:
                response=updateEtudiant(request, connection);

                break;
            case  UPDATE_OFFRE:
                response=updateOffre(request, connection);
                break;
            case  UPDATE_DATE:
                response=updateDate(request, connection);
                break;

            case SELECT_CONN:
                response=selectConn(request, connection);

                break;
            case SELECT_CONDITION_CONN:
                response=selectConditionConn(request, connection);

                break;

                case INSERT_ARTICLE:
                response = InsertArticle(request, connection);
                break;

            case SELECT_ARTICLE:
                // CORRECTION: appel à la méthode cohérente
                response = SelectAllarticles(request, connection);
                break;
            default:
                break;
        }

        return response;
    }

    public Response  selectConn (Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();

    String email;
    String mdp;
    Etudiant etudiant= objectMapper.readValue(request.getRequestBody(), Etudiant.class);
    email = etudiant.getEmail();
    mdp= etudiant.getMot_de_passe();
        final PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_CONN.query);
        stmt.setString(1, email);
        stmt.setString(2, mdp);

        ResultSet res = stmt.executeQuery();

        Etudiants etudiants = new Etudiants();
        while (res.next()) {
            Etudiant et = new Etudiant();

            et.setId(res.getInt("id_etudiant"));
            et.setNom(res.getString("nom"));
            et.setPrenom(res.getString("prenom"));
            et.setEmail(res.getString("email"));
            et.setMatricule(res.getString("matricule"));
            et.setPhoto(res.getString("photo"));
            et.setMot_de_passe(res.getString("mot_de_passe"));
            etudiants.add(et);
        }

        res.close();
        stmt.close();

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiants));
    }

    public Response  selectConditionConn (Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();

        String email;

        Etudiant etudiant= objectMapper.readValue(request.getRequestBody(), Etudiant.class);
        email = etudiant.getEmail();

        final PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_CONDITION_CONN.query);
        stmt.setString(1, email);


        ResultSet res = stmt.executeQuery();

        Etudiants etudiants = new Etudiants();
        while (res.next()) {
            Etudiant et = new Etudiant();

            et.setEmail(res.getString("email"));

            etudiants.add(et);
        }

        res.close();
        stmt.close();

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiants));
    }



    private Response updateOffre(Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();
       Stagee stage = objectMapper.readValue(request.getRequestBody(), Stagee.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_OFFRE.query);



        stmt.setString(1, stage.getTitre());
        stmt.setString(2, stage.getDescription());
        stmt.setString(3, stage.getDomaine());
        stmt.setString(4, stage.getNiveau());
        stmt.setString(5, stage.getDuree());
        stmt.setInt(6, stage.getId());


        stmt.executeUpdate();


        return new Response(request.getRequestId(), objectMapper.writeValueAsString(stage));


    }
    private Response updateInformationEtudiant(Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();
        Etudiant etudiant = objectMapper.readValue(request.getRequestBody(), Etudiant.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_INFORMATION_ETUDIANT.query);


        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getEmail());
        stmt.setString(4, etudiant.getPhoto());
        stmt.setInt(5, etudiant.getId());




        stmt.executeUpdate();


        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiant));


    }

    private Response updateEtudiant(Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final int etudiantId = objectMapper.readValue(request.getRequestBody(), Integer.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_ETUDIANT.query);
        stmt.setInt(1, etudiantId);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected == 0) {
            return new Response(request.getRequestId(), "Aucun etudiant modifié");
        }
        return new Response(request.getRequestId(), "etudiant modifié avec succès");
    }
    private Response updateDate(Request request, Connection connection) throws IOException, SQLException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final int etudiantId = objectMapper.readValue(request.getRequestBody(), Integer.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_DATE.query);
        stmt.setInt(1, etudiantId);

        stmt.executeUpdate();


            return new Response(request.getRequestId(),  objectMapper.writeValueAsString(Map.of("Update","la date a été modifier avec succès")));
        }



private Response InsertCandidature(final Request request, final Connection connection) throws SQLException, IOException {

    final ObjectMapper objectMapper = new ObjectMapper();
    final Candidature candidature = objectMapper.readValue(request.getRequestBody(), Candidature.class);

    final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_CANDIDATURE.query);

    stmt.setString(1, candidature.getNom());
    stmt.setString(2, candidature.getPrenom());
    stmt.setString(3, candidature.getEmail());
    stmt.setString(4, candidature.getAdresse());
    stmt.setString(5, candidature.getCv());
    stmt.setString(6, candidature.getLettre());
    stmt.setString(7, candidature.getAutres());
    stmt.setInt(8,candidature.getId());
    stmt.setInt(9, candidature.getIdEtudiant());



    stmt.executeUpdate();


    return new Response(request.getRequestId(), objectMapper.writeValueAsString(candidature));
}


    private Response InsertStage(final Request request, final Connection connection) throws SQLException, IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final Stagee stagee = objectMapper.readValue(request.getRequestBody(), Stagee.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_STAGE.query);

        stmt.setString(1, stagee.getTitre());
        stmt.setString(2, stagee.getDescription());
        stmt.setString(3, stagee.getDomaine());
        stmt.setString(4, stagee.getNiveau());
        stmt.setString(5, stagee.getDuree());





        stmt.executeUpdate();


        return new Response(request.getRequestId(), objectMapper.writeValueAsString(stagee));
    }

private Response InsertParticipation(final Request request, final Connection connection) throws SQLException, IOException {

    final ObjectMapper objectMapper = new ObjectMapper();
    final Participation participation = objectMapper.readValue(request.getRequestBody(), Participation.class);

    final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_PARTICIPATION.query);

    stmt.setString(1, participation.getNom());
    stmt.setString(2, participation.getPrenom());
    stmt.setString(3, participation.getEmail());
    stmt.setInt(4,participation.getId());



    stmt.executeUpdate();


    return new Response(request.getRequestId(), objectMapper.writeValueAsString(participation));
}
    private Response deleteCandidature(final Request request, final Connection connection) throws SQLException, IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final int candidatureId = objectMapper.readValue(request.getRequestBody(), Integer.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.DELETE_OFFRE.query);
        stmt.setInt(1, candidatureId);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected == 0) {
            return new Response(request.getRequestId(), "Aucune candidature supprimée");
        }

        return new Response(request.getRequestId(), "Candidature supprimée avec succès");
    }

    private Response InsertEtudiant(final Request request, final Connection connection) throws SQLException, IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final Etudiant etudiant = objectMapper.readValue(request.getRequestBody(), Etudiant.class);

        final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_ETUDIANT.query);


        stmt.setString(1, etudiant.getNom());
        stmt.setString(2, etudiant.getPrenom());
        stmt.setString(3, etudiant.getMatricule());
        stmt.setString(4, etudiant.getEmail());
        stmt.setString(5, etudiant.getMot_de_passe());
        stmt.setString(6, etudiant.getCnf_mot_de_passe());

        stmt.setString(7, etudiant.getPhoto());





        stmt.executeUpdate();


        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiant));
    }

    private Response SelectAllstages(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_STAGE.query);
        Stagess stagess = new Stagess();

        while (res.next()) {
           Stagee stagee = new Stagee();
           stagee.setId(res.getInt(1));
            stagee.setTitre(res.getString(2));
            stagee.setDescription(res.getString(3));
            stagee.setDomaine(res.getString(4));
            stagee.setNiveau(res.getString(5));
            stagee.setDuree(res.getString(6));
            stagess.add(stagee);
        }

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(stagess));

    }

    private Response SelectAllevenements(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_EVENEMENT.query);
        Evenements evenements = new Evenements();

        while (res.next()) {
           Evenement evenement = new Evenement();
           evenement.setId(res.getInt(1));
            evenement.setTitre(res.getString(2));
            evenement.setDescription(res.getString(3));
            evenement.setDomaine(res.getString(4));
            evenement.setHeure(res.getString(5));
            evenement.setAdresse(res.getString(6));
            evenement.setJour(res.getString(7));
            evenements.add(evenement);
        }

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(evenements));

    }
    
    private Response SelectCandidature(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();

        int idOffre;

            idOffre = Integer.parseInt(request.getRequestBody());  // S'assurer que requestBody contient un ID valide




        final PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_CANDIDATURE.query);
        stmt.setInt(1, idOffre);  // Affecte l'ID de l'offre à la requête SQL

        ResultSet res = stmt.executeQuery();

  Candidatures candidatures = new Candidatures();
        while (res.next()) {
            Candidature candidature = new Candidature();
            candidature.setId(res.getInt(1));
            candidatures.add(candidature);
        }

        // Fermeture des ressources
        res.close();
        stmt.close();

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(candidatures));
    }


    private Response SelectEtudiant(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_ETUDIANT.query);
        Etudiants etudiants = new Etudiants();

        while (res.next()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(res.getString(1));
            etudiant.setPrenom(res.getString(2));
            etudiant.setMatricule(res.getString(3));
            etudiant.setEmail(res.getString(4));
            etudiant.setPhoto(res.getString(5));
            etudiant.setId(res.getInt(6));


            etudiants.add(etudiant);
        }

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiants));

    }

    private Response SelectAllStudents(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_ALL_STUDENTS.query);
        Etudiants etudiants = new Etudiants();

        while (res.next()) {
            Etudiant etudiant = new Etudiant();
            Stagee stagee = new Stagee();

            etudiant.setId(res.getInt("nombre_candidatures"));
            etudiant.setNom(res.getString("nom"));
            etudiant.setPrenom(res.getString("prenom"));
            etudiant.setId_offre(res.getInt("id_offre"));
            etudiant.setMatricule(res.getString("mat"));
            etudiant.setdetail(res.getInt("id"));
            etudiant.setPhoto(res.getString("photo"));
            etudiant.setDate(res.getDate("date"));


            etudiant.setTitre(res.getString("titre"));
            etudiant.setDuree(res.getString("duree"));
            etudiant.setDomaine(res.getString("domaine"));
            etudiant.setEmail(res.getString("email"));




            etudiants.add(etudiant);
        }

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(etudiants));

    }
    private Response SelectAlloffres(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        // Récupération de la valeur recherchée
        String rechercher = String.valueOf(request.getSearchKeyword());

        // Préparation de la requête avec paramètre
        try (PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_OFFRE.query)) {
            stmt.setString(1,"%"+rechercher.trim()+"%");

            try (ResultSet res = stmt.executeQuery()) {
                Stagess stagess = new Stagess();

                while (res.next()) {
                    Stagee stagee = new Stagee();
                    stagee.setTitre(res.getString(1));
                    stagee.setDescription(res.getString(2));
                    stagee.setDomaine(res.getString(3));
                    stagee.setDuree(res.getString(4));
                    stagess.add(stagee);
                    System.out.println("DEBUG - Requête envoyée : " + rechercher);


                }
                res.close();
                stmt.close();
                return new Response(request.getRequestId(), objectMapper.writeValueAsString(stagess));
            }
        }
    }
    private Response SelectCandidatureEtudiant(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        final Stagee stageeInput = objectMapper.readValue(request.getRequestBody(), Stagee.class);
        final int etudiantId = stageeInput.getId();
        final int offreId = stageeInput.getIdOffre();

        try (PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_CANDIDATURES_ETUDIANT.query)) {
            stmt.setInt(1,etudiantId);
            stmt.setInt(2,offreId);

            try (ResultSet res = stmt.executeQuery()) {
         Stagess stagess = new Stagess();
                while (res.next()) {
                    Stagee stagee = new Stagee();
                    stagee.setTitre(res.getString("titre"));
                    stagee.setDomaine(res.getString("domaine"));
                    stagee.setDuree(res.getString("duree"));
                    stagess.add(stagee);


                }
                res.close();
                stmt.close();
                return new Response(request.getRequestId(), objectMapper.writeValueAsString(stagess));
            }
        }
    }

    // --- Insert Article ---
    private Response InsertArticle(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Article article = objectMapper.readValue(request.getRequestBody(), Article.class);

        // Requête : INSERT_ARTICLE("INSERT INTO article (nom_etudiant, titre, description, id_etudiant, prix, type_transaction, ville) VALUES (?,?,?,?,?,?,?)")

        final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_ARTICLE.query);
        // CORRECTION: Respecter l'ordre des colonnes
        stmt.setString(1, article.getNom());         // nom_etudiant
        stmt.setString(2, article.getTitre());       // titre
        stmt.setString(3, article.getDescription()); // description
        stmt.setInt   (4, article.getId());  // id_etudiant
        stmt.setInt   (5, article.getPrix());        // prix (si c'est vraiment un int)
        stmt.setString(6, article.getTypeTransaction()); // type_transaction
        stmt.setString(7, article.getVille());       // ville

        stmt.executeUpdate();
        stmt.close();

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(article));
    }

    // --- Sélection de tous les articles ---
    private Response SelectAllarticles(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_ARTICLE.query);

        Articles articles = new Articles();
        while (res.next()) {
            // Attention à l'ordre des colonnes dans "SELECT * FROM article"
            Article article = new Article();
            // Suppose que la table 'article' a les colonnes [id_article, nom_etudiant, titre, description, id_etudiant, prix, type_transaction, ville, ...]
            // Adapte selon le vrai ordre
            int idArticle        = res.getInt("id_article");
            String nomEtudiant   = res.getString("nom_etudiant");
            String titre         = res.getString("titre");
            String description   = res.getString("description");
            int idEtudiant       = res.getInt("id_etudiant");
            int prix             = res.getInt("prix");
            String typeTransaction   = res.getString("type_transaction");
            String ville         = res.getString("ville");

            article.setId(idArticle);
            article.setNom(nomEtudiant);
            article.setTitre(titre);
            article.setDescription(description);
            article.setId(idEtudiant);
            article.setPrix(prix);
            article.setTypeTransaction(typeTransaction);
            article.setVille(ville);

            articles.add(article);
        }
        res.close();
        stmt.close();

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(articles));
    }




}
