package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stagee {
    private String titre;
       private String description;
       private String domaine;
       private String duree;
    private int id;
    String niveau;
    private int idOffre;

public Stagee(){

}
    public Stagee( int id){
    this.id = id;

    }
    public Stagee( int id, int idOffre){
        this.id = id;
        this.idOffre = idOffre;

    }


    public Stagee(String titre, String description, String domaine, String duree ,String niveau) {
        this.titre=titre;
        this.description=description;
        this.domaine=domaine;
        this.duree=duree;
        this.niveau=niveau;

    }
    public Stagee(int id,String titre, String description, String domaine, String duree ,String niveau) {
        this.titre=titre;
        this.description=description;
        this.domaine=domaine;
        this.duree=duree;
        this.niveau=niveau;
        this.id = id;

    }
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
    public String getDuree() {
        return duree;
    }
    public  String getNiveau() {return niveau;}


    public String getDomaine() {
        return domaine;
    }

    public int getId() {
        return id;
    }
    public int getIdOffre() {return idOffre;}

    public void setDescription(String description) {
        this.description=description;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNiveau(String niveau) {this.niveau = niveau;}
    public void setIdOffre(int idOffre) {this.idOffre = idOffre;}


    public void setTitre(String titre) {
        this.titre = titre; }


    public final Stagee build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "titre", "description","duree","domaine");
        return this;
    }

    private void setFieldsFromResulset(ResultSet resultSet, final String ... fieldNames) throws NoSuchFieldException, SQLException, IllegalAccessException {

        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }}


    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }
}
