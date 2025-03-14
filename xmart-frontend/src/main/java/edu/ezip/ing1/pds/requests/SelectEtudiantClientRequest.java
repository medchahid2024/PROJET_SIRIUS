package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ezip.ing1.pds.business.dto.Etudiants;
import edu.ezip.ing1.pds.business.dto.Stagess;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectEtudiantClientRequest extends ClientRequest<Object, Etudiants> {

    public SelectEtudiantClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Etudiants readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Etudiants etudiants = mapper.readValue(body, Etudiants.class);
        return etudiants;
    }
}
