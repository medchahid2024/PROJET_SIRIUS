package main.java.edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Evenement {
    private String titre;
    private String description;
    private String domaine;
    private String heure;
    private int id;

public Evenement(){

}


    public Evenement(String titre, String description, String domaine, String heure) {
        this.titre=titre;
        this.description=description;
        this.domaine=domaine;
        this.heure=heure;

    }
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
    public String getHeure() {
        return heure;
    }


    public String getDomaine() {
        return domaine;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTitre(String titre) {
        this.titre = titre; }


    public final Evenement build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "titre", "description","heure","domaine");
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
