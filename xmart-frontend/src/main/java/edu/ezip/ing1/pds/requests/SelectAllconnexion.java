//package edu.ezip.ing1.pds.requests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
////import edu.ezip.ing1.pds.business.dto.Students;
//import edu.ezip.ing1.pds.business.dto.Connexions;
//import edu.ezip.ing1.pds.business.dto.Stagess;
//import edu.ezip.ing1.pds.client.commons.ClientRequest;
//import edu.ezip.ing1.pds.client.commons.NetworkConfig;
//import edu.ezip.ing1.pds.commons.Request;
//
//import java.io.IOException;
//
//public class SelectAllconnexion extends ClientRequest<Object, Connexions> {
//
//    public SelectAllconnexion(
//            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
//            throws IOException {
//        super(networkConfig, myBirthDate, request, info, bytes);
//    }
//
//    @Override
//    public Connexions readResult(String body) throws IOException {
//        final ObjectMapper mapper = new ObjectMapper();
//        final Connexions Co = mapper.readValue(body, Connexions.class);
//        return Co;
//    }
//}
