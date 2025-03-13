package edu.ezip.ing1.pds.services;
import edu.ezip.ing1.pds.requests.DeleteStageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class Delete {
    private final static String LoggingLabel = "D e l e t e ";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    public static void deleteValue(String requestOrder, Integer id) throws IOException, SQLException, InterruptedException {

        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        final ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Client to be deleted : {}", id);
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        request.setRequestContent(id.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

        final DeleteStageRequest deleteStage = new DeleteStageRequest (
                networkConfig,
                birthdate++, request, id, requestBytes);
        deleteStage.join();
    }

}
