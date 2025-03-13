package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Etudiants {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("etudiants")
    private Set<Etudiant> etudiants = new LinkedHashSet<Etudiant>();

    public  Etudiants(){

    }
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public final Etudiants add (final Etudiant etudiant) {


        etudiants.add(etudiant);
        return this;
    }
}
