package main.java.edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.edu.ezip.ing1.pds.business.dto.Evenements;
import main.java.edu.ezip.ing1.pds.business.dto.Evenement;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllEvenementsClientRequest extends ClientRequest<Object, Evenements> {

    public SelectAllEvenementsClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Evenements readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Evenements evenements = mapper.readValue(body, Evenements.class);
        return evenements;
    }
}
