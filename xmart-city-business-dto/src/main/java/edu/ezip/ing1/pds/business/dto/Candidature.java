package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidature {
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String cv;
    private String lettre;
    private String autres;
    private int id;
    //private int id;

    public Candidature() {
    }

<<<<<<< HEAD
    public Candidature(String nom, String prenom,String email,String adresse, String cv, String lettre, String autres,int id ) {
=======
    public Candidature(String nom, String prenom,String email,String adresse, String cv, String lettre, String autres , int id) {
>>>>>>> 96ad9ea (utilisation de clé etranger pour la table candidature(id_offre) pour envoyer la candidature à l'entreprise convenable)
        this.nom = nom;
        this.prenom = prenom;
        this.cv = cv;
        this.lettre = lettre;
        this.autres = autres;
        this.email=email;
        this.adresse=adresse;
        this.id = id;

    }

<<<<<<< HEAD
=======


>>>>>>> 96ad9ea (utilisation de clé etranger pour la table candidature(id_offre) pour envoyer la candidature à l'entreprise convenable)
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getCv() { return cv; }
    public String getLettre() { return lettre; }
    public String getAutres() { return autres; }
   public int getId() { return id; }
    public String getEmail() {return email;
    }
    public String getAdresse() {return adresse; }


    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCv(String cv) { this.cv = cv; }
    public void setLettre(String lettre) { this.lettre = lettre; }
    public void setAutres(String autres) { this.autres = autres; }
    public void setId(int id) { this.id = id; }

    public final Candidature build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet, "nom", "prenom", "cv", "lettre", "autres" );
        return this;
    }

    private void setFieldsFromResultSet(ResultSet resultSet, final String... fieldNames)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for (final String fieldName : fieldNames) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }

    public final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement ,final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }


}
