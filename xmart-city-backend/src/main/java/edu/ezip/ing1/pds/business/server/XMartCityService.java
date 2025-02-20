package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import edu.ezip.ing1.pds.business.dto.Candidature;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class XMartCityService {

    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    private enum Queries {

    SELECT_STAGE("SELECT titre, description, domaine,duree  FROM offres_stages") ,
       // INSERT_STAGE("INSERT into offres_stages (titre, description, domaine,duree) values (?, ?, ?,?)");
INSERT_CANDIDATURE("INSERT INTO candidature (nom,prenom,cv, lettre_de_motivation,autres_fichier)VALUES  (?, ?, ?, ?, ?) ");


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

        final Queries queryEnum = Enum.valueOf(Queries.class, request.getRequestOrder());
        switch(queryEnum) {

//            case INSERT_CANDIDATURE:
//                response = InsertCandidature(request, connection);
//                break;
            case SELECT_STAGE:
               response=SelectAllstages(request, connection);
                break;
            default:
                break;
        }

        return response;
    }

//    private Response InsertStage(final Request request, final Connection connection) throws SQLException, IOException {
//
//        final ObjectMapper objectMapper = new ObjectMapper();
//        final Stagee stage = objectMapper.readValue(request.getRequestBody(), Stagee.class);
//
//        final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_STAGE.query);
//        stmt.setString(1, stage.getTitre());
//        stmt.setString(2, stage.getDescription());
//        stmt.setString(3, stage.getDomaine());
//        stmt.setString(4, stage.getDuree());
//        stmt.executeUpdate();
//
//        final Statement stmt2 = connection.createStatement();
//        final ResultSet res = stmt2.executeQuery("SELECT LAST_INSERT_ID()");
//        res.next();
//
//        stage.setId(res.getInt(1));
//
//        return new Response(request.getRequestId(), objectMapper.writeValueAsString(stage));
//}
//private Response InsertCandidature(final Request request, final Connection connection) throws SQLException, IOException {
//
//    final ObjectMapper objectMapper = new ObjectMapper();
//    final Candidature candidature = objectMapper.readValue(request.getRequestBody(), Candidature.class);
//
//    final PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_CANDIDATURE.query);
//    stmt.setString(1, candidature.getNom());
//    stmt.setString(2, candidature.getPrenom());
//    stmt.setString(3, candidature.getCv());
//    stmt.setString(4, candidature.getLettre());
//    stmt.setString(5, candidature.getAutres());
//    stmt.executeUpdate();
//
//    final Statement stmt2 = connection.createStatement();
//    final ResultSet res = stmt2.executeQuery("SELECT LAST_INSERT_ID()");
//    res.next();
//
//    candidature.setId(res.getInt(1));
//
//    return new Response(request.getRequestId(), objectMapper.writeValueAsString(candidature));
//}


    private Response SelectAllstages(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_STAGE.query);
        Stagess stagess = new Stagess();

        while (res.next()) {
           Stagee stagee = new Stagee();
            stagee.setTitre(res.getString(1));
            stagee.setDescription(res.getString(2));
            stagee.setDomaine(res.getString(3));
            stagee.setDuree(res.getString(4));
            stagess.add(stagee);
        }

        return new Response(request.getRequestId(), objectMapper.writeValueAsString(stagess));

    }

}
