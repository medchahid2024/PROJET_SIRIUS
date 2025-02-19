package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.commons.LoggingUtils;
//import edu.ezip.ing1.pds.business.dto.Student;
//import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
//import edu.ezip.ing1.pds.requests.InsertStudentsClientRequest;
import edu.ezip.ing1.pds.requests.InsertAllStagesClientRequest;
import edu.ezip.ing1.pds.requests.SelectAllStagesClientRequest;
//import edu.ezip.ing1.pds.requests.SelectAllStudentsClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class StageService {
    private final static String LoggingLabel = "FrontEnd - StageService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
//    private final static String studentsToBeInserted = "stages-to-be-inserted.yaml";

   final String insertRequestOrder = "INSERT_STAGE";
    final String selectRequestOrder = "SELECT_STAGE";
   private final static String stagesToBeInserted = "students-to-be-inserted.yaml";

    private final NetworkConfig networkConfig;

    public StageService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    public void insertStages() throws InterruptedException, IOException {
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final Stagess guys = ConfigLoader.loadConfig(Stagess.class, stagesToBeInserted);

        int birthdate = 0;
        for(final Stagee guy : guys.getStages()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guy);
            logger.trace("Stage with its JSON face : {}", jsonifiedGuy);
            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(insertRequestOrder);
            request.setRequestContent(jsonifiedGuy);
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            final InsertAllStagesClientRequest clientRequest = new InsertAllStagesClientRequest(
                    networkConfig,
                    birthdate++, request, guy, requestBytes);
            clientRequests.push(clientRequest);
        }

        while (!clientRequests.isEmpty()) {
            final ClientRequest clientRequest = clientRequests.pop();
            clientRequest.join();
            final Stagee guy = (Stagee) clientRequest.getInfo();
            logger.debug("Thread {} complete : {} {} {} --> {}",
                    clientRequest.getThreadName(),
                    guy.getTitre(), guy.getDescription(), guy.getDomaine(),guy.getDuree(),
                    clientRequest.getResult());
        }
    }

    public Stagess selectStages() throws InterruptedException, IOException {
        int birthdate = 0;
        final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(selectRequestOrder);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectAllStagesClientRequest clientRequest = new SelectAllStagesClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        if(!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            return (Stagess) joinedClientRequest.getResult();
        }
        else {
            logger.error("No stages found");
            return null;
        }
    }

}

