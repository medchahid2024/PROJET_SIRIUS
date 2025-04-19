package edu.ezip.ing1.pds.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Deposer;
import edu.ezip.ing1.pds.business.dto.Stagee;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class InsertDeposerClientRequest extends ClientRequest<Deposer, String> {

    public InsertDeposerClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Deposer info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> stageIdMap = mapper.readValue(body, Map.class);
        final String result  = stageIdMap.get("student_id");
        return result;
    }
}

