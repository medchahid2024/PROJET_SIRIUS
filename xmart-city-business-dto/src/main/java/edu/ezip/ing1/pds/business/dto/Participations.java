package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Participations {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("participations")
    private Set<Participation> Participations = new LinkedHashSet<Participation>();

    int count;

    public  Participations(){

    }
    public Set<Participation> getParticipations() {
        return Participations;
    }

    public void setParticipations(Set<Participation> Participations) {
        this.Participations = Participations;
    }
    public int getCount (){
        return count;
    }



    public final Participations add (final Participation Participation) {


        Participations.add(Participation);
        return this;
    }
}
