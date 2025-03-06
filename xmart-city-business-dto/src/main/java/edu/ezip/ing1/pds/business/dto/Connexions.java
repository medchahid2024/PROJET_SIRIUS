//package edu.ezip.ing1.pds.business.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//
//
//
//public class Connexions {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonProperty("stages")
//    private static Set<Connexion> Connexions = new LinkedHashSet<Connexion>();
//
//    public Connexions() {
//
//    }
//
//    public Set<Connexion> getConnexions() {
//        return Connexions;
//    }
//
//    public void setConnexions(Set<Connexion> Connexions) {
//        this.Connexions = Connexions;
//    }
//
//    public  final Connexions add(final Connexion connexion) {
//
//
//        Connexions.add(connexion);
//        return this;
//    }
//}